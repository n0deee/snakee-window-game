package lib;

import java.awt.Color;

import errors.ExceptionAlreadyInitialized;

public interface Display2D {
    public void init() throws ExceptionAlreadyInitialized;
    public Vec2 getBufferSize();
    public void setBufferColor(Vec2 pos, Color color);
    public Color getBufferColor(Vec2 pos);
    public void render();
    public void clearBuffer(Color withColor);
    public void destroy();
}
