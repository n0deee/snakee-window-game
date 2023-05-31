package game;

import java.util.List;

import game.entities.Fruit;
import game.entities.Snake;
import lib.Input;
import lib.Logic;
import lib.Vec2;

public class GameLogic implements Logic<GameState> {
    private GameState gameState;
    private Vec2 initArenaBounds;
    private int growPerFruit;
    private int initalSnakeSize;

    // ! TEMPORARY
    Input input;

    public GameLogic(Vec2 arenaBounds, Input input) {
        this.gameState = new GameState(arenaBounds);
        this.input = input;
        this.initArenaBounds = arenaBounds.copy();
        this.growPerFruit = 1;
        this.initalSnakeSize = 2;
    }

    public boolean tick() {
        try {
            Thread.sleep(gameState.gameDelayPerTick);
        } catch (Exception e) {
        }

        switch (gameState.state) {
            case Initalizing:
                return TickStateInitalizing();
            case Playing:
                return TickStatePlaying();
            case Dead:
                return TickStateDead();
            case End:
                return TickStateEnd();
        }

        return false;
    }

    private boolean TickStateInitalizing() {
        this.gameState.score = 0;
        this.gameState.arenaBounds = this.initArenaBounds.copy();
        this.gameState.snake = new Snake(this.gameState.arenaBounds.div(2), this.initalSnakeSize);
        this.gameState.fruit = new Fruit();
        this.gameState.fruit.randomizePos(this.gameState.arenaBounds);

        this.gameState.state = State.Playing;
        this.gameState.gameDelayPerTick = 200;
        return true;
    }

    private boolean TickStatePlaying() {
        // Updating entities
        Snake snake = this.gameState.snake;
        Fruit fruit = this.gameState.fruit;

        Vec2 inputDirection = Vec2.createFromDirection(this.input.tickInput());
        Vec2 newHeadPosition = snake.getHeadPos().sum(inputDirection).warp(this.gameState.arenaBounds);
        snake.bodyStep(newHeadPosition);

        boolean collidedWithBody = this.isColliding(
                snake.getEntireBody().subList(1, snake.getEntireBody().size()), snake.getHeadPos());

        // Detects collision with fruits
        if (this.isColliding(snake.getHeadPos(), fruit.getPos())) {
            this.gameState.score += 1;
            fruit.randomizePos(gameState.arenaBounds);
            snake.grow(growPerFruit);
        }

        // Detects collision with own body
        if (collidedWithBody) {
            this.gameState.state = State.Dead;
        }
        return true;
    }

    private boolean TickStateDead() {
        this.gameState.gameDelayPerTick = 0;
        this.gameState.state = State.End;
        return true;
    }

    private boolean TickStateEnd() {
        return false;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    private boolean isColliding(Vec2 pos1, Vec2 pos2) {
        return pos1.equals(pos2);
    }

    private boolean isColliding(List<Vec2> positions1, Vec2 pos2) {
        for (Vec2 pos : positions1) {
            if (this.isColliding(pos, pos2)) {
                return true;
            }
        }

        return false;
    }

    // TODO: public getEntities...
}
