package windows;

import javax.swing.*;

import lib.Vec2;

import java.awt.*;
import java.awt.event.WindowEvent;

public class PixelWindow extends JFrame {
    private Color pixelColor;
    private JPanel colorPanel;
    private boolean hideOnBlack = false;

    public PixelWindow(Vec2 initalPosition, Vec2 size) {
        this(new Color(0, 0, 0), initalPosition, size);
    }

    public PixelWindow(Color initalColor, Vec2 initalPosition, Vec2 size) {
        this.setSize(new Dimension(size.x, size.y));
        this.setLocation(initalPosition.x, initalPosition.y);
        pixelColor = initalColor;
    }

    public void setHideOnBlack(boolean value) {
        this.hideOnBlack = value;
    }

    public boolean getHideOnBlack() {
        return this.hideOnBlack;
    }

    public void initalize() {

        colorPanel = new JPanel();
        add(colorPanel);

        setColor(this.pixelColor);

        setAlwaysOnTop(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setType(Window.Type.UTILITY);
        setMinimumSize(this.getSize());
        setVisible(true);
    }

    public void setColor(Color color) {
        this.pixelColor = color;
        colorPanel.setBackground(this.pixelColor);

        if (hideOnBlack) {
            boolean visibility = !this.pixelColor.equals(Color.BLACK);
            this.setVisible(visibility);
        }
    }

    public void close() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public Color getColor() {
        return this.pixelColor;
    }
}
