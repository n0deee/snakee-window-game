package game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GameSaveManager {
    private String path;

    public GameSaveManager(String savePath) {
        this.path = savePath;
    }

    public GameSave load() throws IOException {
        byte[] saveBuffer = Files.readAllBytes(Paths.get(this.path));
        return GameSave.byteDeserialize(saveBuffer);
    }

    public void save(GameSave save) throws IOException {
        byte[] saveBuffer = save.byteSerialize();
        Files.write(Paths.get(this.path), saveBuffer, StandardOpenOption.CREATE);
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String newPath) {
        this.path = newPath;
    }
}
