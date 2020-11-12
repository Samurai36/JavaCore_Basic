package Viktor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;

public class Setting extends JFrame {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 500;

    private static final int MIN_WIN_LENGHT = 3;
    private static final int MIN_FIELD_LENGHT = 3;
    private static final int MAX_FIELD_LENGHT = 10;
    private static final String FIELD_LENGHT_TEXT = " Field lenght is: ";
    private static final String WIN_LENGHT_TEXT = " Win lenght is: ";

    private MainWindow mainWindow;

    private JRadioButton humanVsAI;
    private JRadioButton humanVsHuman;
    private JSlider slideWinLen;
    private JSlider slideFieldSize;

    Setting(MainWindow mainWindow){

        this.mainWindow = mainWindow;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        Rectangle gameWindowBounds = mainWindow.getBounds();
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDTH/2;
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGHT/2;
        setLocation(posX, posY);
        setResizable(false);
        setTitle("Settings new game");
        setLayout(new GridLayout(10, 1));

        addGameModeSetup();
        addFieldMapControl();

        JButton btnPlay = new JButton("Play new game");
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPlayGameClick();
            }
        });

        add(btnPlay);
        setVisible(false);

    }

    private void addGameModeSetup(){
        add(new Label(" Choose game mode: "));
        humanVsAI = new JRadioButton(" Human vs AI", true);
        humanVsHuman = new JRadioButton(" Human vs human");
        ButtonGroup gameMode = new ButtonGroup();
        gameMode.add(humanVsAI);
        gameMode.add(humanVsHuman);
        add(humanVsAI);
        add(humanVsHuman);

    }

    private void addFieldMapControl() {
        JLabel lbFieldSize = new JLabel(FIELD_LENGHT_TEXT + MIN_FIELD_LENGHT);
        JLabel lbWinLength = new JLabel(WIN_LENGHT_TEXT + MIN_WIN_LENGHT);

        slideFieldSize = new JSlider(MIN_FIELD_LENGHT, MAX_FIELD_LENGHT, MIN_WIN_LENGHT);
        slideFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = slideFieldSize.getValue();
                lbFieldSize.setText(FIELD_LENGHT_TEXT + currentValue);
                slideWinLen.setMaximum(currentValue);
            }
        });

        slideWinLen = new JSlider(MIN_WIN_LENGHT, MIN_WIN_LENGHT, MIN_WIN_LENGHT);
        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGHT_TEXT + slideWinLen.getValue());
            }
        });

        Dictionary<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(new Integer(3), new JLabel("<html><font color=green size=2>3"));
        labels.put(new Integer(4), new JLabel("<html><font color=green size=2>4"));
        labels.put(new Integer(5), new JLabel("<html><font color=green size=2>5"));
        labels.put(new Integer(6), new JLabel("<html><font color=green size=2>6"));
        labels.put(new Integer(7), new JLabel("<html><font color=green size=2>7"));
        labels.put(new Integer(8), new JLabel("<html><font color=green size=2>8"));
        labels.put(new Integer(9), new JLabel("<html><font color=green size=2>9"));
        labels.put(new Integer(10), new JLabel("<html><font color=green size=2>10"));


        slideFieldSize.setLabelTable(labels);
        slideFieldSize.setPaintLabels(true);
        slideFieldSize.setPaintTicks(true);

        slideWinLen.setLabelTable(labels);
        slideWinLen.setPaintLabels(true);
        slideWinLen.setPaintTicks(true);


        add(new JLabel(" Choose field map length"));
        add(lbFieldSize);
        add(slideFieldSize);
        add(new JLabel(" Choose win length"));
        add(lbWinLength);
        add(slideWinLen);
    }

    private void btnPlayGameClick() {
        int gameMode;

        if (humanVsAI.isSelected()) {
            gameMode = GameMap.MODE_HVA;
            setVisible(false);
        } else if (humanVsHuman.isSelected()) {
            gameMode = GameMap.MODE_HVH;
            setVisible(false);
        } else {
            throw new RuntimeException("Unexpected game mode!");
        }

        int fieldSize = slideFieldSize.getValue();
        int winLength = slideWinLen.getValue();

        mainWindow.startNewGame(gameMode, fieldSize, fieldSize, winLength);

    }

}
