package game;

import java.io.IOException;

public class CachedGameSaveManager extends GameSaveManager {
    private boolean isCacheValid;
    private GameSave cachedGameSave;

    public CachedGameSaveManager(String savePath) {
        super(savePath);
        this.cachedGameSave = null;
        this.isCacheValid = false;
    }

    @Override
    public GameSave load() throws IOException {
        if (this.isCacheValid)
            return this.cachedGameSave;

        this.cachedGameSave = super.load();
        this.isCacheValid = true;
        return this.cachedGameSave;
    }

    @Override
    public void save(GameSave save) throws IOException {
        super.save(save);
        this.cachedGameSave = save;
        this.isCacheValid = true;
    }

    @Override
    public void setPath(String newPath) {
        super.setPath(newPath);
        this.isCacheValid = false;
    }
}
