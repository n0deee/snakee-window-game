package lib;

public interface Logic<T> {
    public boolean tick();
    public T getGameState();
}
