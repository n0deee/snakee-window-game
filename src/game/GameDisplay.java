package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import errors.ExceptionAlreadyInitialized;
import lib.BidimensionalArrayList;
import lib.Display2D;
import lib.Vec2;
import windows.PixelWindow;

public class GameDisplay implements Display2D {

    private boolean initalized;
    private BidimensionalArrayList<Color> buffer;
    private BidimensionalArrayList<PixelWindow> pixels;
    GameDisplayInformation screenInfo;

    public GameDisplay(GameDisplayInformation info) {
        // TODO: Handle negative values
        this.buffer = new BidimensionalArrayList<Color>(info.pixelCount.copy());
        this.pixels = new BidimensionalArrayList<PixelWindow>(info.pixelCount.copy());
        this.screenInfo = info;
        this.initalized = false;
    }

    public void init() throws ExceptionAlreadyInitialized {
        if (this.initalized)
            throw new ExceptionAlreadyInitialized();

        for (int x = 0; x < pixels.size().x; x++) {
            for (int y = 0; y < pixels.size().y; y++) {
                Vec2 index = new Vec2(x, y);
                Vec2 pixelPos = this.screenInfo.pixelSize.mul(index).sum(this.screenInfo.pixelPadding.mul(index))
                        .sum(this.screenInfo.positionOffset);

                PixelWindow newPixel = new PixelWindow(GameColorPalette.VOID, pixelPos, this.screenInfo.pixelSize);
                // newPixel.setHideOnBlack(true);
                newPixel.initalize();
                this.pixels.set(index, newPixel);
            }
        }

        this.initalized = true;
    }

    public void destroy() {
        this.initalized = false;
        for (int x = 0; x < pixels.size().x; x++) {
            for (int y = 0; y < pixels.size().y; y++) {
                Vec2 index = new Vec2(x, y);
                PixelWindow pixelWindow = this.pixels.get(index);

                pixelWindow.close();
            }
        }
    }

    @Override
    public Vec2 getBufferSize() {
        return this.buffer.size();
    }

    @Override
    public void setBufferColor(Vec2 pos, Color color) {
        this.buffer.set(pos.copy(), color);
    }

    @Override
    public Color getBufferColor(Vec2 pos) {
        return this.buffer.get(pos); // TODO: Check if thsi can be modified externally
    }

    @Override
    public void render() {
        if (!initalized)
            return;

        for (int x = 0; x < pixels.size().x; x++) {
            for (int y = 0; y < pixels.size().y; y++) {
                Vec2 index = new Vec2(x, y);

                Color pixelColor = this.buffer.get(index);
                Color pixelColorCopy = new Color(pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(),
                        pixelColor.getAlpha());
                PixelWindow pixelWindow = this.pixels.get(index);
                pixelWindow.setColor(pixelColorCopy);
            }
        }
    }

    @Override
    public void clearBuffer(Color withColor) {
        for (int x = 0; x < pixels.size().x; x++) {
            for (int y = 0; y < pixels.size().y; y++) {

                Vec2 index = new Vec2(x, y);
                Color pixelColorCopy = new Color(withColor.getRed(), withColor.getGreen(), withColor.getBlue(),
                        withColor.getAlpha());
                this.buffer.set(index, pixelColorCopy);
            }
        }
    }

    public static GameDisplayInformation getWhatFitsOnTheScreen(Vec2 pixelsCount, Vec2 pixelsPadding,
            boolean keepProportions) {
        Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
        Vec2 screenSize = new Vec2((int) screenDimensions.getWidth(), (int) screenDimensions.getHeight());

        Vec2 totalPixelSize = screenSize.div(pixelsCount);

        if (keepProportions)
            totalPixelSize = new Vec2(totalPixelSize.minSide());
        
        Vec2 pixelSize = totalPixelSize.sub(pixelsPadding);

        Vec2 extraSpace = screenSize.sub(totalPixelSize.mul(pixelsCount)).sum(pixelsPadding.div(2));
        Vec2 pixelOffset = new Vec2(0, 0);

        if (extraSpace.x != 0)
            pixelOffset.x = extraSpace.x / 2;
        if (extraSpace.y != 0)
            pixelOffset.y = extraSpace.y / 2;


        return new GameDisplayInformation(pixelsCount, pixelSize, pixelOffset, pixelsPadding);
    }
}
