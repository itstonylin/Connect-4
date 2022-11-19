import java.util.Random;

public class AIPlayer extends Player {

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    // AI tries to win first, if they cannot, they will try to
    // block the other player, if there are no moves
    // to block, then it will make a random move
    public void makeMove(Board board) {

        Random random = new Random();
        char otherPlayerSymbol = symbol == board.getP1Symbol() ? board.getP2Symbol() : board.getP1Symbol();

        // AI tries to make a winning move
        for (int i = 1; i <= board.getNumOfColumns(); i++) {
            if (!board.isOverFlow(i)) {
                board.addMoveToBoard(i, symbol);
                if (!board.containsWin()) {
                    board.resetLastMove();
                } else {
                    return;
                }
            }
        }

        // AI tries to block human from moving
        for (int i = 1; i <= board.getNumOfColumns(); i++) {
            if (!board.isOverFlow(i)) {
                board.addMoveToBoard(i, otherPlayerSymbol);
                if (!board.containsWin()) {
                    board.resetLastMove();
                } else {
                    board.resetLastMove();
                    board.addMoveToBoard(i, symbol);
                    return;
                }
            }
        }

        // AI makes a random move
        int aiMove = random.nextInt(board.getNumOfColumns()) + 1;
        while (board.isOverFlow(aiMove)) {
            aiMove = random.nextInt(board.getNumOfColumns()) + 1;
        }
        board.addMoveToBoard(aiMove, symbol);
    }
}
