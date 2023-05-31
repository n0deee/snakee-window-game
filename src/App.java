import windows.*;

// O jogo é iniciado no MenuWindow. Isso é estranho, e precisa de refatoração
public class App {
    public static final String SAVE_PATH = "snakesave.bin";
    
    public static void main(String[] args) throws Exception {
        Motd.readAndPrintMotd();
        
        // InputWindow inputWindow = new InputWindow();
        // inputWindow.initalize();
        MenuWindow menuWindow = new MenuWindow(SAVE_PATH);
        menuWindow.initalize();
    }
}