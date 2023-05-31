package windows;

import javax.swing.*;

import game.Direction;
import lib.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class InputWindow extends JFrame implements KeyListener, Input {

    private Direction lastTickDirection;
    private Direction currentDirection;

    public InputWindow() {
        currentDirection = Direction.Right;

        this.setFocusable(true);
        this.addKeyListener(this);
        this.setSize(new Dimension(200, 200));
        // this.setLocation();
    }

    public void initalize() {

        // setAlwaysOnTop(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // setUndecorated(true);
        setResizable(false);
        // setType(Window.Type.UTILITY);
        // setMinimumSize(this.getSize());
        setVisible(true);
    }

    public void close() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(e);

        Direction newDirection = Direction.Right;

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            newDirection = Direction.Left;

        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            newDirection = Direction.Right;

        else if (e.getKeyCode() == KeyEvent.VK_UP)
            newDirection = Direction.Up;

        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            newDirection = Direction.Down;

        boolean validMovement = newDirection != this.lastTickDirection.getOppositeDirection();

        if (validMovement)
            this.currentDirection = newDirection;

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public Direction tickInput() {
        lastTickDirection = this.currentDirection;
        return this.currentDirection;
    }
}
