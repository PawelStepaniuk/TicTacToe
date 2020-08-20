package TicTacToe;

import java.util.Scanner;

public class TicTacEngine {

    boolean Xwins;
    boolean Owins;
    String inputData;
    char[][] ticTacArray;
    boolean flag;
    boolean over;

    public TicTacEngine(String inputData) {
        this.Xwins = false;
        this.Owins = false;
        this.inputData = inputData;
        this.ticTacArray = fillTheArray(inputData);

    }

    public char[][] fillTheArray(String inputData) {
        char[] xo = inputData.toCharArray();
        char[][] tmp = new char[3][3];

        for (int i = 0; i < 9; i++) {
            tmp[i / 3][i % 3] = xo[i];
        }
        return tmp;
    }

    public void printField() {
        System.out.println("---------");
        for (int i = 0; i < 3; i += 1) {
            System.out.println("|" + " " + ticTacArray[i][0] + " " +
                    ticTacArray[i][1] + " " + ticTacArray[i][2] + " " + "|");
        }
        System.out.println("---------");
    }



    boolean win(char value) {

        if (ticTacArray[0][0] == value && ticTacArray[0][1] == value && ticTacArray[0][2] == value) {
            return true;
        }
        if (ticTacArray[1][0] == value && ticTacArray[1][1] == value && ticTacArray[1][2] == value) {
            return true;
        }
        if (ticTacArray[2][0] == value && ticTacArray[2][1] == value && ticTacArray[2][2] == value) {
            return true;
        }

        if (ticTacArray[0][0] == value && ticTacArray[1][0] == value && ticTacArray[2][0] == value) {
            return true;
        }
        if (ticTacArray[0][1] == value && ticTacArray[1][1] == value && ticTacArray[2][1] == value) {
            return true;
        }
        if (ticTacArray[0][2] == value && ticTacArray[1][2] == value && ticTacArray[2][2] == value) {
            return true;
        }
        if (ticTacArray[0][0] == value && ticTacArray[1][1] == value && ticTacArray[2][2] == value) {
            return true;
        }
        if (ticTacArray[0][2] == value && ticTacArray[1][1] == value && ticTacArray[2][0] == value) {
            return true;
        }

        return false;
    }

    String score() {
        if ((win('X')) && (win('O'))) {
            return ("draw");

        } else if (win('O')) {
            over = true;
            return ("O wins");
        } else if (win('X')) {
            over = true;
            return ("X wins");
        } else if (draw()) {
            over = true;
            return ("Draw");
        } else if (gameNotFinished()) {
            return ("Game not finished");
        } else return ("Impossible");
    }

    boolean draw() {
        for (char[] chars : ticTacArray) {
            for (int j = 0; j < ticTacArray.length; j++) {
                if (chars[j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    boolean gameNotFinished() {
        int o = 0;
        int x = 0;
        for (char[] chars : ticTacArray) {
            for (int j = 0; j < ticTacArray.length; j++) {
                if (chars[j] == 'X') {
                    x++;
                } else if (chars[j] == 'O') {
                    o++;
                }
            }
        }
        return o == x;
    }

    public void updateBoard(char value) {

        Scanner scanner = new Scanner(System.in);

        int first;
        int second;

        while (!flag) {
            String userInputLine = scanner.nextLine();

            try {
                System.out.println("Enter the coordinates");

                String[] pieces = userInputLine.split(" ");
                first = Integer.parseInt(pieces[0]);
                second = Integer.parseInt(pieces[1]);

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (first > 3 || second > 3 || first < 1 || second < 1) {
                System.out.println("Out of bounds!");
                continue;
            }

            if (ticTacArray[3 - second][first - 1] == '_' || ticTacArray[3 - second][first - 1] == ' ') {
                ticTacArray[3 - second][first - 1] = value;
                flag = true;
                printField();


            } else {
                System.out.println((ticTacArray[3 - second][first - 1] != '_') + "        " + (ticTacArray[3 - second][first - 1] != ' '));
                System.out.println("This cell is occupied! Choose another one.");
            }

        }
        flag = false;
    }

    public void game() {
        printField();
        char valueX = 'X';
        char valueO = 'O';
        boolean mark = true;

        do {
            if (mark){
                updateBoard(valueX);
                mark = false;
            }else {
                updateBoard(valueO);
                mark = true;
            }
            score();
        } while (!over);

        System.out.println(score());
    }
}