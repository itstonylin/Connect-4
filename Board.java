import java.util.ArrayList;

public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	private char p1Symbol;
	private char p2Symbol;
	private int numOfMoves = 0;
	private ArrayList<Integer> choices = new ArrayList<Integer>();
	private char[][] moves = new char[NUM_OF_ROW][NUM_OF_COLUMNS * 2 + 1]; // dimensions of board is [6][15]

	public Board() {
		reset();
	}

	// resets last move, used in AIPlayer
	public void resetLastMove() {
		numOfMoves--;
		int position = choices.get(choices.size() - 1);
		for (int i = 0; i <= 5; i++) {
			if (moves[i][position + position - 1] != ' ' && i == 5) {
				moves[i][position + position - 1] = '_';
				return;
			} else if (moves[i][position + position - 1] != ' ') {
				moves[i][position + position - 1] = ' ';
				return;
			}
		}
	}

	// checks vertically if theres space to add a move, and also determines the
	// board pieces
	public boolean addMoveToBoard(int x, char symbol) {
		if (numOfMoves == 0) {
			p1Symbol = symbol;
		} else if (numOfMoves == 1) {
			p2Symbol = symbol;

		}
		numOfMoves++;
		for (int i = 5; i >= 0; i--) {
			if (moves[i][x + x - 1] == '_') {
				moves[i][x + x - 1] = symbol;
				choices.add(x);
				return true;
			} else if (moves[i][x + x - 1] == ' ') {
				moves[i][x + x - 1] = symbol;
				choices.add(x);
				return true;
			}
		}
		return false;
	}

	// checks if the top row is full
	public boolean isOverFlow(int x) {
		if (moves[0][x + x - 1] != ' ') {
			return true;
		}
		return false;
	}

	// loops through the board and prints it
	public void printBoard() {
		for (int i = 0; i < this.moves.length; i++) {
			for (int j = 0; j < this.moves[i].length; j++) {
				System.out.print(this.moves[i][j]);
			}
			System.out.println("");
		}
	}

	// checks for horizontal, vertical, and diagonal wins
	public boolean containsWin() {

		// horizontal check
		int hMiddle = 7;
		for (int i = 5; i >= 0; i--) {
			if (moves[i][hMiddle] != '_' && moves[i][hMiddle] != ' ') {
				for (int j = 1; j <= hMiddle; j = j + 2) {
					if (moves[i][j] == moves[i][j + 2] && moves[i][j] == moves[i][j + 4]
							&& moves[i][j] == moves[i][j + 6]) {
						return true;
					}
				}
			}
		}

		// vertical check
		int vMiddle = 2;
		for (int i = 1; i < moves[0].length; i = i + 2) {
			if (moves[vMiddle][i] != ' ') {
				for (int j = 5; j > vMiddle; j--) {
					if (moves[j][i] == moves[j - 1][i] && moves[j][i] == moves[j - 2][i]
							&& moves[j][i] == moves[j - 3][i]) {
						return true;
					}
				}
			}
		}
		
		// diagonal check (up right)
		//      	  /
		//    	   / /
		// 		/ / /
		for (int i = 0; i <= 2; i++) {
			for (int j = 1; j <= i + 2; j = j + 2) {
				if (moves[i + 3][j] != ' ' && moves[i + 3][j] == moves[i + 2][j + 2]
						&& moves[i + 3][j] == moves[i + 1][j + 4] && moves[i + 3][j] == moves[i][j + 6]) {
					return true;
				}
			}
		}

		// 		  / 
		// 		 / /
		// 		/ / / 
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (moves[5 - j][7 - i * 2] != ' ' && moves[5 - j][7 - i * 2] == moves[4 - j][9 - i * 2]
						&& moves[5 - j][7 - i * 2] == moves[3 - j][11 - i * 2]
						&& moves[5 - j][7 - i * 2] == moves[2 - j][13 - i * 2]) {
					return true;
				}
			}
		}

		// // diagonal check (down right)
		// 		  \
		// 		 \ \
		// 		\ \ \
		for (int i = 0; i <= 2; i++) {
			for (int j = 1; j <= i + 2; j = j + 2) {
				if (moves[2 - i][j] != ' ' && moves[2 - i][j] == moves[3 - i][j + 2]
						&& moves[2 - i][j] == moves[4 - i][j + 4] && moves[2 - i][j] == moves[5 - i][j + 6]) {
					return true;
				}
			}
		}

		// 		\
		// 		 \ \
		// 		  \ \ \
		for (int i = 0; i <= 2; i++) {
			for (int j = 1; j <= i + 1; j++) {
				if (moves[j - 1][7 - i * 2] != ' ' && moves[j - 1][7 - i * 2] == moves[j][9 - i * 2]
						&& moves[j - 1][7 - i * 2] == moves[j + 1][11 - i * 2]
						&& moves[j - 1][7 - i * 2] == moves[j + 2][13 - i * 2]) {
					return true;
				}
			}
		}
		return false;
	}

	// checks for a tie, if all the top positions are filled then it must be a tie
	public boolean isTie() {
		for (int i = 1; i < moves[0].length; i = i + 2) {
			if (!isOverFlow((i + 1) / 2)) {
				return false;
			}
		}
		return true;
	}

	// resets the board to an empty board
	public void reset() {
		for (int i = 0; i < this.moves.length; i++) {
			for (int j = 0; j < this.moves[i].length; j++) {
				if (i == this.moves.length - 1 && j % 2 == 1) {
					this.moves[i][j] = '_';
				} else if (j % 2 == 0) {
					this.moves[i][j] = '|';
				} else {
					this.moves[i][j] = ' ';
				}
			}
		}
	}

	// get num of columns
	public char getNumOfColumns() {
		return NUM_OF_COLUMNS;
	}

	// get num of rows
	public char getNumOfRows() {
		return NUM_OF_ROW;
	}

	// get p1 symbol
	public char getP1Symbol() {
		return p1Symbol;
	}

	// get p2 symbol
	public char getP2Symbol() {
		return p2Symbol;
	}
}
