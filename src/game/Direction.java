package game;

import errors.RuntimeExceptionInvalidDirection;

public enum Direction {
    Up,
    Down,
    Left,
    Right;

    public Direction getOppositeDirection() {
        switch (this) {
            case Up:
                return Down;
            case Down:
                return Up;
            case Left:
                return Right;
            case Right:
                return Left;
            default:
                throw new RuntimeExceptionInvalidDirection();       
        }
    }
}
