package TicTacToe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        TicTacEngine ticTac = new TicTacEngine(string);
        ticTac.printField();
        ticTac.updateBoard();

    }
}