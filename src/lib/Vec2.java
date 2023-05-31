package lib;

import java.util.Random;

import errors.RuntimeExceptionInvalidDirection;
import game.Direction;

public class Vec2 {
    public int x;
    public int y;

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2() {
        this(0, 0);
    }

    public Vec2(int xy) {
        this(xy, xy);
    }

    @Override
    public String toString() {
        return String.format("{ x: %s, y: %s }", this.x, this.y);
    }

    public Vec2 copy() {
        return new Vec2(this.x, this.y);
    }

    public boolean equals(Vec2 otherVec) {
        return this.x == otherVec.x && this.y == otherVec.y;
    }

    public Vec2 div(int value) {
        return this.div(new Vec2(value, value));
    }

    public Vec2 div(Vec2 value) {
        return new Vec2(this.x / value.x, this.y / value.y);
    }

    public Vec2 sum(Vec2 value) {
        return new Vec2(this.x + value.x, this.y + value.y);
    }

    public Vec2 sub(Vec2 value) {
        return new Vec2(this.x - value.x, this.y - value.y);
    }

    public Vec2 mul(Vec2 value) {
        return new Vec2(this.x * value.x, this.y * value.y);
    }

    public int minSide() {
        return this.x > this.y ? this.y : this.x;
    }

    // Attention: passing negative bounds results in a untested behaviour
    public Vec2 warp(Vec2 positiveBounds) {
        int newX = this.x >= 0 ? this.x % positiveBounds.x : positiveBounds.x - Math.abs(this.x % -positiveBounds.x);
        int newY = this.y >= 0 ? this.y % positiveBounds.y : positiveBounds.y - Math.abs(this.y % -positiveBounds.y);
        return new Vec2(newX, newY);
    }

    public static Vec2 createFromDirection(Direction direction) {
        switch (direction) {
            case Up:
                return new Vec2(0, -1);
            case Down:
                return new Vec2(0, 1);
            case Left:
                return new Vec2(-1, 0);
            case Right:
                return new Vec2(1, 0);
            default:
                throw new RuntimeExceptionInvalidDirection();       
        }
    }

    public static Vec2 createRandomPositivePos(Random randomizer, Vec2 max) {
        return new Vec2(randomizer.nextInt(0, max.x), randomizer.nextInt(0, max.y));
    }

}