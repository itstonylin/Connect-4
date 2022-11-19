import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    // gets user's move (1-7) and if the given move's column is full, will keep
    // reprompting until given a move whose column is available
    public void makeMove(Board board) {
        Scanner getInput = new Scanner(System.in);
        System.out.println(this.name + " please enter a move (1 - 7, from left to right)");
        int input = getInput.nextInt();
        while (board.isOverFlow(input)) {
            System.out.println("Column is full, please enter a different move");
            input = getInput.nextInt();
        }
        board.addMoveToBoard(input, symbol);
    }
}
