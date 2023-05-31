package game;

import java.util.ArrayList;
import java.util.HashMap;

import lib.Display2D;
import lib.Input;
import lib.Logic;
import lib.Vec2;

public class Game extends Thread {
    private Display2D gameDisplay;
    private Logic<GameState> gameLogic;
    private HashMap<GameEvent, ArrayList<GameEventListener>> events;

    public Game(Input input) {
        Vec2 arenaBounds = new Vec2(16, 16);
        this.gameLogic = new GameLogic(arenaBounds, input);
        // GameDisplayInformation screenInfo = new GameDisplayInformation(arenaBounds.copy(), new Vec2(50, 50), new Vec2(), new Vec2(10, 10));
        GameDisplayInformation screenInfo = GameDisplay.getWhatFitsOnTheScreen(arenaBounds, new Vec2(12, 12), true);
        this.gameDisplay = new GameDisplay(screenInfo);
        this.events = new HashMap<GameEvent, ArrayList<GameEventListener>>();
    }

    @Override
    public void run() {
        try {
            this.gameDisplay.init();
        } catch (Exception ex) {
            System.out.println("Failed to initialize Display2d");
        }

        GameState gameState = this.gameLogic.getGameState();
        boolean shouldLoop = true;

        do {
            shouldLoop = this.gameLogic.tick();

            this.gameDisplay.clearBuffer(GameColorPalette.VOID);

            for (Vec2 pos : gameState.snake.getEntireBody()) {
                this.gameDisplay.setBufferColor(pos, GameColorPalette.SNAKE);
            }
            this.gameDisplay.setBufferColor(gameState.snake.getHeadPos(), GameColorPalette.SNAKE_HEAD);

            this.gameDisplay.setBufferColor(gameState.fruit.getPos(), GameColorPalette.FRUIT);

            this.gameDisplay.render();
        } while (shouldLoop);

        callEvents(GameEvent.Ended);
        gameDisplay.destroy();
    }

    public void addEventListener(GameEvent event, GameEventListener listener) {
        ArrayList<GameEventListener> listeners = this.events.get(event);
        if (listeners == null) {
            listeners = new ArrayList<GameEventListener>();
            this.events.put(event, listeners);
        }

        listeners.add(listener);
    }

    private void callEvents(GameEvent event) {
        ArrayList<GameEventListener> listeners = this.events.get(event);
        if (listeners == null)
            return;

        GameState currentGameState = this.gameLogic.getGameState();
        for (GameEventListener listener : listeners) {
            listener.onEvent(currentGameState);
        }
    }
}