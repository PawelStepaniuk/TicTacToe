package TicTacToe;

import java.util.Scanner;

public class TicTacEngine {

    boolean Xwins;
    boolean Owins;
    String inputData;
    char[][] ticTacArray;

    public TicTacEngine(String inputData) {
        this.Xwins = false;
        this.Owins = false;
        this.inputData = inputData;
        this.ticTacArray = fillTheArray(inputData);

    }

    public char[][] fillTheArray(String inputData){
        char[] xo = inputData.toCharArray();
        char[][] tmp = new char[3][3];

        for (int i = 0; i < 9; i++) {
            tmp[i/3][i%3] = xo[i];
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

    public boolean isImpossible() {
        long x = inputData.chars().filter(e -> e == 'X').count();
        long y = inputData.chars().filter(e -> e == 'O').count();
        return Math.abs(x - y) > 1;
    }

    public void analyzeResults() {
        checkRows();
        checkColumns();
        checkDiagonals();
    }

    public void checkRows() {
        char winnerResult;

        for (int i = 0; i < 3; i += 1) {
            winnerResult = ticTacArray[i][0];
            if (winnerResult == ticTacArray[i][1] && winnerResult == ticTacArray[i][2]) {
                setWinner(winnerResult);
            }
        }
    }

    public void checkColumns() {
        char winnerResult;

        for (int i = 0; i < 3; i++) {
            winnerResult = ticTacArray[0][i];
            if (winnerResult == ticTacArray[1][i] && winnerResult == ticTacArray[2][i]) {
                setWinner(winnerResult);
            }
        }
    }

    public void checkDiagonals() {
        char topLeftCorner = ticTacArray[0][0];
        char topRightCorner = ticTacArray[0][2];

        if (topLeftCorner == '_' && topRightCorner == '_') {
            return;
        }
        if (topLeftCorner == ticTacArray[1][1] && topLeftCorner == ticTacArray[2][2]) {
            setWinner(topLeftCorner);
        }
        if (topRightCorner == ticTacArray[1][1] && topRightCorner == ticTacArray[2][0]) {
            setWinner(topRightCorner);
        }
    }

    public void setWinner(char winnerResult){
        if (winnerResult == 'X') {
            Xwins = true;
        } else {
            Owins = true;
        }
    }

    public String printWinner() {
        if (!Xwins && !Owins && inputData.chars().filter(e -> e == '_').count() == 0) {
            return "Draw";
        }
        if (Xwins && Owins) {
            return "Impossible";
        }
        if (Xwins) {
            return "X wins";
        }
        if (Owins) {
            return "O wins";
        }
        return "Game not finished";
    }

    public void updateBoard() {

        Scanner scanner = new Scanner(System.in);

        int first;
        int second;

        while (true) {
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

            if (ticTacArray[3 - second][first - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one.");
                continue;
            }

            ticTacArray[3 - second][first - 1] = 'X';
            printField();
            break;
        }
        scanner.close();
    }
}