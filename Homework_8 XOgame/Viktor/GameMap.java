package Viktor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

//* Рачертить панель GameMap на поле для игры MainWindow, согласно fieldSize

public class GameMap extends JPanel {
    private static final Random RANDOM = new Random();

    private int stateGameMode;
    public static final int MODE_HVA = 0;
    public static final int MODE_HVH = 1;

    private static final int DOT_EMPTY = 0;
    private static final int DOT_HUMAN = 1;
    private static final int DOT_HUMAN2 = 3;
    private static final int DOT_AI = 2;
    private static final int DOT_PADDING = 5;

    private int stateGameOver;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final int STATE_WIN_HUMAN2 = 3;

    private static final String MSG_WIN_HUMAN = "Победил игрок";
    private static final String MSG_WIN_HUMAN2 = "Победил игрок 2";
    private static final String MSG_WIN_AI = "Победил компьютер";
    private static final String MSG_DRAW = "Ничья";

    private boolean isGameOver;
    private boolean initializedMap;

    private int[][] field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;

    private int cellWidth;
    private int cellHeight;

    private boolean nextTurn = false;
    private int human1 = 1;
    private int human2 = 0;

    GameMap() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        initializedMap = false;
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.fieldSizeY = fieldSizeY;
        this.fieldSizeX = fieldSizeX;
        this.winLength = winLength;
        field = new int[fieldSizeY][fieldSizeX];
        isGameOver = false;
        initializedMap = true;
        repaint();
        stateGameMode = mode;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void setGameOver(int gameOverState) {
        isGameOver = true;
        stateGameOver = gameOverState;
        repaint();
    }

    void update(MouseEvent e) {

        if (!initializedMap) {
            return;
        }

        if (isGameOver) {
            return;
        }
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) {
            return;
        }

        switch (stateGameMode) {
            case MODE_HVA: {

                field[cellY][cellX] = DOT_HUMAN;
                if (checkWin(DOT_HUMAN)) {
                    setGameOver(STATE_WIN_HUMAN);
                    return;
                }

                if (isFullMap()) {
                    setGameOver(STATE_DRAW);
                    return;
                }
                aiTurn();
                repaint();
                if (checkWin(DOT_AI)) {
                    setGameOver(STATE_WIN_AI);
                    return;
                }
                if (isFullMap()) {
                    setGameOver(STATE_DRAW);
                    return;
                }
                break;
            }
            case MODE_HVH: {
                if (human1 == 1) {
                    nextTurn = true;
                    human2 = 0;
                    {

                        field[cellY][cellX] = DOT_HUMAN;
                        if (checkWin(DOT_HUMAN)) {
                            setGameOver(STATE_WIN_HUMAN);
                            return;
                        }

                        if (isFullMap()) {
                            setGameOver(STATE_DRAW);
                            return;
                        }
                    }
                }
                repaint();

                if (human2 == 1) {
                    nextTurn = false;
                    human1 = 0;
                    {
                        field[cellY][cellX] = DOT_HUMAN2;

                        if (checkWin(DOT_HUMAN2)) {
                            setGameOver(STATE_WIN_HUMAN2);
                            return;
                        }

                        if (isFullMap()) {
                            setGameOver(STATE_DRAW);
                            return;
                        }
                    }
                }
                repaint();
                break;

            }
            default:
                throw new RuntimeException("Unexpected game mode state:" + stateGameMode);
        }
        if (nextTurn) {
            human1 = 0;
            human2 = 1;
        }
        else {
            human1 = 1;
            human2 = 0;
        }

    }

    private void render(Graphics g) {
        if (!initializedMap) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y)) {
                    continue;
                }
                if (field[y][x] == DOT_HUMAN) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == DOT_HUMAN2) {
                    g.setColor(Color.GREEN);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == DOT_AI) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException(String.format("Can't repaint cell field[%d][%d]: %d", y, x, field[y][x]));
                }
            }
        }
        if (isGameOver) {
            showMessageGameOver(g);
        }
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 275, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times New Roman", Font.BOLD, 48));
        switch (stateGameOver) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2);
                break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 70, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 140, getHeight() / 2);
                break;
            case STATE_WIN_HUMAN2:
                g.drawString(MSG_WIN_HUMAN2, 140, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Unexpected game over state:" + stateGameOver);
        }

    }

    public void aiTurn() {
        if (turnAIWinCell()) {
            return;
        }
        if (turnHumanWinCell()) {
            return;
        }
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = DOT_AI;
    }

    private boolean turnAIWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = DOT_AI;
                    if (checkWin(DOT_AI)) {
                        return true;
                    }
                    field[i][j] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = DOT_HUMAN;
                    if (checkWin(DOT_HUMAN)) {
                        field[i][j] = DOT_AI;
                        return true;
                    }
                    field[i][j] = DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private boolean checkWin(int labelPlayer) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (checkLine(i, j, 1, 0, winLength, labelPlayer)) {
                    return true;
                }
                if (checkLine(i, j, 1, 1, winLength, labelPlayer)) {
                    return true;
                }
                if (checkLine(i, j, 0, 1, winLength, labelPlayer)) {
                    return true;
                }
                if (checkLine(i, j, 1, -1, winLength, labelPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int labelPlayer) {
        final int farX = x + (len - 1) * vx;
        final int farY = y + (len - 1) * vy;
        if (!isValidCell(farX, farY)) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (field[y + i * vy][x + i * vx] != labelPlayer) {
                return false;
            }
        }
        return true;
    }

    public boolean isFullMap() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    public boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

}
