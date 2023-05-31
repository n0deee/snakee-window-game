package game;

import game.entities.Fruit;
import game.entities.Snake;
import lib.Vec2;

public class GameState {
    public int score;
    public Vec2 arenaBounds; // TODO: Protect or wrap this
    public Snake snake;
    public Fruit fruit;
    public State state;
    public Direction headDirection;
    public int gameDelayPerTick;

    public GameState(Vec2 arenaBounds) {
        this.score = 0;
        this.arenaBounds = arenaBounds.copy();
        this.state = State.Initalizing;
        this.headDirection = Direction.Right;
        this.gameDelayPerTick = 0;
    }
}
