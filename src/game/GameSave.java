package game;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class GameSave {
    public String highestScoreName;
    public int highestScore;

    public GameSave() {}

    public GameSave(String highestScoreName, int highestScore) {
        this.highestScoreName = highestScoreName;
        this.highestScore = highestScore;
    }

    public byte[] byteSerialize() {
        // Precisa de refatoração pra uma classe que só lê buffers
        byte[] nameBytes = this.highestScoreName.getBytes(StandardCharsets.UTF_8);
        
        int sizeOfScore = 4;
        int sizeOfScoreNameLenght = 4;
        int sizeOfScoreName = nameBytes.length;
        int totalBufferSize = sizeOfScore + sizeOfScoreNameLenght + sizeOfScoreName;
        ByteBuffer byteBuffer = ByteBuffer.allocate(totalBufferSize);

        byteBuffer.putInt(highestScore);
        byteBuffer.putInt(nameBytes.length);
        byteBuffer.put(nameBytes);

        return byteBuffer.array();
    }

    public static GameSave byteDeserialize(byte[] buffer) {
        // Precisa de refatoração pra uma classe que só lê buffers
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer); // Automaticaamente big endian

        int score = byteBuffer.getInt();
        int nameLen = byteBuffer.getInt();
        byte[] nameBuff = new byte[nameLen];
        byteBuffer.get(nameBuff, 0, nameBuff.length);

        String name = new String(nameBuff, StandardCharsets.UTF_8);

        return new GameSave(name, score);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Score: %s", this.highestScoreName, this.highestScore);
    }
}
