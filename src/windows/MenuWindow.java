package windows;

import javax.swing.*;

import game.CachedGameSaveManager;
import game.Game;
import game.GameEvent;
import game.GameEventListener;
import game.GameSave;
import game.GameSaveManager;
import game.GameState;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame {
    protected JPanel mainPanel, playPanel;
    protected JLabel highestScoreLabel, yourNameLabel;
    protected JTextArea nameArea;
    protected JButton playButton;
    protected MenuWindow self;

    protected GameSaveManager gameSaveManager;
    protected InputWindow inputWindow;
    protected static final GameSave DEFAULT_GAME_SAVE = new GameSave("??", -1);

    public MenuWindow(String gameSavePath) {
        self = this;
        this.gameSaveManager = new CachedGameSaveManager(gameSavePath);
    }

    public void initalize() {
        
        GameSave save;
        try {
            save = this.gameSaveManager.load();
        } catch (Exception ex) {
            save = DEFAULT_GAME_SAVE;
        }

        /*-------------------*/
        yourNameLabel = new JLabel("Seu nome:");

        /*-------------------*/
        yourNameLabel = new JLabel("Seu nome:");

        /*-------------------*/
        nameArea = new JTextArea("Guest");

        /*-------------------*/
        playButton = new JButton("Play");
        playButton.addActionListener(onButtonPlayClicked);

        /*-------------------*/
        highestScoreLabel = new JLabel("Maior Pontuação: XXXX");
        updateHighestScoreLabel(save.highestScoreName, save.highestScore);

        /*-------------------*/
        playPanel = new JPanel();
        playPanel.setLayout(new GridLayout(3, 1, 5, 5));
        playPanel.add(yourNameLabel);
        playPanel.add(nameArea);
        playPanel.add(playButton);

        /*-------------------*/
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(highestScoreLabel, BorderLayout.NORTH);
        mainPanel.add(playPanel, BorderLayout.CENTER);

        /*-------------------*/
        add(mainPanel);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    protected void updateHighestScoreLabel(String name, int score) {
        this.highestScoreLabel.setText(String.format("Maior Pontuação: %s (%s pontos)", name, score));
    }

    ActionListener onButtonPlayClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);

            inputWindow = new InputWindow();
            inputWindow.initalize();    
            Game game = new Game(inputWindow);
            game.addEventListener(GameEvent.Ended, onGameEnded);
            game.start();

        }
    };

    GameEventListener onGameEnded = new GameEventListener() {

        @Override
        public void onEvent(GameState state) {
            GameSave newSave = new GameSave(nameArea.getText(), state.score);

            inputWindow.close();
            JOptionPane.showMessageDialog(null, String.format("Sua pontuação: %s", newSave.highestScore), "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
            
            GameSave oldSave;
            try {
                oldSave = gameSaveManager.load();
            } catch (Exception ex) {
                oldSave = DEFAULT_GAME_SAVE;
                // System.out.println("Failed to process save");
                // ex.printStackTrace();
            }

            if (newSave.highestScore > oldSave.highestScore) {
                try {
                    gameSaveManager.save(newSave);
                } catch (Exception ex) {
                    System.out.println("Failed to save save");
                    ex.printStackTrace();
                }    
                updateHighestScoreLabel(newSave.highestScoreName, newSave.highestScore);
            }

            setVisible(true);
        }
    };
}
