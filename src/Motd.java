import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Motd {
    public static final String MOTD_PATH = "motd.txt";

    public static boolean readAndPrintMotd() {
        try {
            String motd = Files.readString(Paths.get(MOTD_PATH));
            System.out.println(motd);
            return true;
        } catch (IOException ex) {
            System.out.println("No motd");
            return false;
        }
    }
}
