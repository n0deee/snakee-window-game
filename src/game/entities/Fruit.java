package game.entities;

import java.util.Random;

import lib.Vec2;

public class Fruit extends AbstractEntity {
    private Random randomizer;
    private Vec2 position;
    
    public Fruit(Vec2 initalPos) {
        this.randomizer = new Random();
        this.position = initalPos.copy();
    }

    public Fruit() {
        this(new Vec2());
    }

    public void randomizePos(Vec2 max) {
        this.position = Vec2.createRandomPositivePos(this.randomizer, max);
    }

    public Vec2 getPos() {
        return this.position;
    }

    public void destroy() {
        this.position = null;
    }
}
