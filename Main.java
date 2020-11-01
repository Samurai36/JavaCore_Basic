package Viktor;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char HUMAN_DOT = 'x';
    private static final char AI_DOT = 'o';
    private static final char EMPTY_DOT = '_';
    private static final Random RANDOM = new Random();

    public static final Scanner SCANNER = new Scanner(System.in);

    public static int fieldSizeX;
    public static int fieldSizeY;
    public static char[][] field;

    public static void main(String[] args) {

        initMap();

        while (true) {
            turnHuman();
            drawMap();
            if (checkWin(HUMAN_DOT)) {
                System.out.println("Вы выиграли!!!");
                break;
            }
            if (checkFullMap()) {
                System.out.println("Ничья");
                break;
            }
            System.out.println();
            turnAI();
            drawMap();
            if (checkWin(AI_DOT)) {
                System.out.println("Киборги победили");
                break;
            }
            if (checkFullMap()) {
                System.out.println("Ничья");
                break;
            }
            System.out.println();
        }
    }

    static void initMap (){

        do {
            System.out.println("Выберите размеры поля 3х3 (набрав 3) или 5х5 (набрав 5)");
            fieldSizeX = SCANNER.nextInt();
        }
        while (fieldSizeX != 3 && fieldSizeX!=5);
        fieldSizeY = fieldSizeX;
            field = new char[fieldSizeY][fieldSizeX];
            for (int x = 0; x < fieldSizeY; x++) {
                for (int y = 0; y < fieldSizeX; y++)
                    field[x][y] = EMPTY_DOT;
            }
        drawMap();

    }

    static void drawMap (){

        for (int x = 0; x < fieldSizeY; x++) {
            for (int y = 0; y < fieldSizeX; y++){
                System.out.print(field[x][y] + "|");
            }

            System.out.println();
        }

    }

    static void turnHuman(){
        int x;
        int y;
        do {
            System.out.println("Введите координаты через пробел или через enter: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!checkValidCell(y, x) || !checkEmptyCell(y, x));
        field[y][x] = HUMAN_DOT;
    }

    static void turnAI(){
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!checkEmptyCell(y, x));

        field[y][x] = AI_DOT;
    }

    static boolean checkEmptyCell(int y, int x){
        return field[y][x] == EMPTY_DOT;
    }

    static boolean checkValidCell(int y, int x){
        return (y >= 0 && y < fieldSizeY && x >= 0 && x < fieldSizeX);
    }

    static boolean checkFullMap(){
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
    static boolean checkWin(char c){
        int diag = 0;
        int diagReverse = 0;
        boolean resulte = false;

        for (int i = 0; i < fieldSizeX; i++) {

            for (int j = 0; j < fieldSizeY; j++) {

                if (field[i][j] == c) {
                    diag++;
                }
            }

            if (fieldSizeX == 3 && diag == 3 || fieldSizeX == 5 && diag == 4) {
                resulte = true;
                break;
            } else {
                diag = 0;
            }

        }

        if (resulte) {
            return resulte;
        }

        for (int i = 0; i < fieldSizeX; i++) {

            for (int j = 0; j < fieldSizeY; j++) {
                if (field[j][i] == c) {
                    diag++;
                }
            }

            if (fieldSizeX == 3 && diag == 3 || fieldSizeX == 5 && diag == 4) {
                resulte = true;
                break;
            } else {
                diag = 0;
            }

        }

        if (resulte) {
            return resulte;
        }

        for (int i = 0; i < fieldSizeX; i++) {

            if (field[i][i] == c) {
                diag++;
            }
            if (field[i][fieldSizeX - i - 1] == c) {
                diagReverse++;
            }

        }

        if (fieldSizeX == 3 && diag == 3 || fieldSizeX == 3 && diagReverse == 3 || fieldSizeX == 5 && diag == 4 || fieldSizeX == 5 && diagReverse == 4) {
            resulte = true;
        }

        return resulte;
    }
}
