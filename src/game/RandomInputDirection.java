package game;

import java.util.Random;

import errors.RuntimeExceptionInvalidDirection;
import lib.Input;
import lib.Vec2;

public class RandomInputDirection implements Input {
    private int keepInput;
    private final int keepInputMin;
    private Vec2 keepInputMax;
    private Random randomizer;
    private Direction currentDirection;

    public RandomInputDirection(Vec2 keepInputMax) {
        this.randomizer = new Random();
        this.keepInputMax = keepInputMax;
        this.keepInput = 0;
        this.keepInputMin = 1;
    }

    public Direction tickInput() {
        if (keepInput > 0) {
            keepInput--;
            return this.currentDirection;
        }

        this.currentDirection = makeADecision();
        int xOrYBounds = this.currentDirection == Direction.Left || this.currentDirection == Direction.Right ? keepInputMax.x : keepInputMax.y;
        this.keepInput = this.randomizer.nextInt(keepInputMin, xOrYBounds);
        return this.currentDirection;
    }

    public void setKeepInputMax(Vec2 value) {
        this.keepInputMax = value;
    }

    private Direction makeADecision() {
        boolean validInput = false;
        Direction newDirection;
        do {
            switch (this.randomizer.nextInt(1, 4 + 1)) {
                case 1:
                    newDirection = Direction.Up;
                    break;
                case 2:
                    newDirection = Direction.Down;
                    break;
                case 3:
                    newDirection = Direction.Left;
                    break;
                case 4:
                    newDirection = Direction.Right;
                    break;
                default:
                    throw new RuntimeExceptionInvalidDirection("Invalid direction decision!");
            }
            validInput = newDirection.getOppositeDirection() != currentDirection && newDirection != currentDirection;
        } while (!validInput);
        return newDirection;
    }

}
