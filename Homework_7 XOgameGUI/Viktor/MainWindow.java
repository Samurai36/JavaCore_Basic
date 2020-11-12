package Viktor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_POSX = 600;
    private static final int WINDOW_POSY = 200;

    private Setting settingWindow;
    private GameMap gameMap;

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setTitle("X/0 - game");

        settingWindow = new Setting(this);
        gameMap = new GameMap();

        JButton btnStart = new JButton("Create new game");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingWindow.setVisible(true);
            }
        });

        JButton btnExitGame = new JButton("Exit game");
        btnExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1,2));
        panelBottom.add(btnStart);
        panelBottom.add(btnExitGame);

        add(panelBottom, BorderLayout.SOUTH);

        setResizable(false);
        setVisible(true);
    }

    void startNewGame(int mode, int fieldLenghtX, int fieldLenghtY, int winLenght){
        gameMap.startNewGame(mode, fieldLenghtX, fieldLenghtY, winLenght);
    }

}
