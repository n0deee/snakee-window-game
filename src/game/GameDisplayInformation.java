package game;

import lib.Vec2;

public class GameDisplayInformation {
    public Vec2 pixelCount;
    public Vec2 pixelSize;
    public Vec2 positionOffset;
    public Vec2 pixelPadding;

    public GameDisplayInformation(Vec2 pixelCount, Vec2 pixelSize, Vec2 positionOffset, Vec2 pixelPadding) {
        this.pixelCount = pixelCount;
        this.pixelSize = pixelSize;
        this.positionOffset = positionOffset;
        this.pixelPadding = pixelPadding;
    }
}
