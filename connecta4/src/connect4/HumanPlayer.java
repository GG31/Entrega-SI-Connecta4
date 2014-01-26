//

package connect4;

import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer(char token, Board board) {
		super(token, board);
	}

	/**
	 * Ask and return a column to play.
	 */
	public int selectMovement() {
		System.out.println("Current board");

		System.out.print("column? ");
		int column = getColumnFromUser(0, this.board.height());
		while (this.board.isFull(column)) {
			System.out.println("Column " + column + " is full...");
			System.out.print("column? ");
			column = getColumnFromUser(0, this.board.height());
		}

		return column;
	}

	private int getColumnFromUser(int min, int max) {
		Scanner input = new Scanner(System.in);

		int value = Integer.parseInt(input.next());
		while (value < min || max < value) {
			System.out.println("Column must be a value between " + min
					+ " and " + max);
			System.out.print("column? ");
			value = Integer.parseInt(input.next());
			
		}

		return value;
	}
}