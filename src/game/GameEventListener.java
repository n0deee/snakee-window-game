package game;

import java.util.EventListener;

public interface GameEventListener extends EventListener {
    public abstract void onEvent(GameState state);
}
