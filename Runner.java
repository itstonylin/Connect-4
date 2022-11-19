public class Runner {
    public static void main(String[] args) {

        Board board = new Board();
        ConnectFour game = new ConnectFour(board);

        // ai v ai
        game.setPlayer1(new AIPlayer('A', board, "AI1"));
        game.setPlayer2(new AIPlayer('I', board, "AI2"));

        // player v player 
        // game.setPlayer1(new HumanPlayer('T', board, "Tony"));
        // game.setPlayer2(new HumanPlayer('E', board, "Eric"));

        // player v ai
        // game.setPlayer1(new HumanPlayer('T', board, "Tony"));
        // game.setPlayer2(new AIPlayer('A', board, "AI"));

        game.playGame();
    }
}
