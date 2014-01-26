package connect4;

import fuzzification.Process;

/**
 * This player is intelligent, he chooses the columns himself thanks to the
 * fuzzy logic.S
 * 
 */
public class FuzzyPlayer extends Player {
	private final int A = 2;
	private final int B = 5;
	private final int C = 8;
	private final int D = 13;

	private char opponent;
	private Process process;

	public FuzzyPlayer(char token, Board board, char opponent) {
		super(token, board);
		this.opponent = opponent;
		this.process = new Process(A, B, C, D);
	}

	/**
	 * Choose a column to play.
	 */
	public int selectMovement() {
		float[][] entries = new float[2][7];
		for (int i = 0; i < entries[0].length; i++) {
			entries[0][i] = countLine(this.board, this.token, i);
			entries[1][i] = sum(board, this.token, i);
		}
		this.process.apply(entries);
		return this.process.chooseColumn();
	}

	/**
	 * Count the lines'number where it's possible to do a line.
	 * 
	 * @param board
	 * @param token
	 * @param column
	 * @return
	 */
	private int countLine(Board board, char token, int column) {
		return countLineMineDiag(board, token, column)
				+ countLineMineHorizontal(board, token, column)
				+ countLineMineVertical(board, token, column);
	}

	/**
	 * Count the number of token in line if we put the token in the column, and
	 * count the number of possible directions. Then do a combination to do a
	 * unique number.
	 * 
	 * @param board
	 * @param token
	 * @param column
	 * @return
	 */
	private float sum(Board board, char token, int column) {
		return addWeight(countSumLine(board, token, column),
				countDirection(board, token, column));
	}

	/**
	 * Count the number of line which is possible to realize on diagonal putting
	 * the token at the column column
	 */
	public int countLineMineDiag(Board board, char token, int column) {
		int line = board.getTopPosition(column);
		int countToken = 0;
		int countLine = 0;
		// Diagonal right
		for (int i = 0; i < 4; i++) {
			countToken = 0;
			for (int j = 0; j < 4 && column + i - j >= 0
					&& column + i - j < board.getColumns() && line + i - j >= 0
					&& line + i - j < board.getHeight(); j++) {
				if (board.get(column + i - j, line + i - j) == token
						|| board.get(column + i - j, line + i - j) == ' ') {
					countToken++;
					if (countToken == 4) {
						countLine++;
					}
				}
			}
		}
		// Diagonal left
		for (int i = 0; i < 4; i++) {
			countToken = 0;
			for (int j = 0; j < 4 && column - i + j >= 0
					&& column - i + j < board.getColumns() && line + i - j >= 0
					&& line + i - j < board.getHeight(); j++) {
				int c = column - i + j;
				int l = line + i - j;
				if (board.get(column - i + j, line + i - j) == token
						|| board.get(column - i + j, line + i - j) == ' ') {
					countToken++;
					if (countToken == 4) {
						countLine++;
					}
				}
			}
		}
		return countLine;
	}

	/**
	 * Count the oportunities'horizontal
	 * 
	 * @param board
	 * @param token
	 * @param column
	 * @return
	 */
	public int countLineMineHorizontal(Board board, char token, int column) {
		int count = 0;
		int line = board.getTopPosition(column);
		for (int i = column - 3; i <= column; i++) {
			if (isHorizontalCandidate(token, board, i, line)) {
				count++;
			}
		}

		return count;
	}

	private boolean isHorizontalCandidate(char token, Board board, int column,
			int line) {
		if (column < 0 || column + 4 > board.columns()) {
			return false;
		} else {
			int i = 0;
			while (i < 4 && board.get(column + i, line) != opponent) {
				i++;
			}

			return i >= 4;
		}
	}

	/**
	 * Count the oportunities'vertical
	 * 
	 * @param board
	 * @param token
	 * @param column
	 * @return
	 */
	public int countLineMineVertical(Board board, char token, int column) {
		int line = board.getTopPosition(column);
		int count = 0;
		while (line >= 0 && board.get(column, line) != opponent) {
			if (line + 3 <= board.getHeight())
				count++;
			line--;
		}
		return count;
	}

	/**
	 * Count the number of token which are doing a line with the possible next
	 * token at the column column
	 * 
	 * @param board
	 * @param token
	 * @param column
	 * @return
	 */
	public int countSumLine(Board board, char token, int column) {
		int[][] increment = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 },
				{ 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
		int j = board.getTopPosition(column);
		int count = 0;
		for (int i = 0; i < 4; i++) {
			boolean check = false;
			if (verifyIn(board, column + increment[i][0], j + increment[i][1])) {
				while (board.get(column + increment[i][0], j + increment[i][1]) == token) {
					count++;
					check = true;
					increment[i][1] = (increment[i][1] > 0) ? increment[i][1] + 1
							: (increment[i][1] < 0) ? increment[i][1] - 1 : 0;
					increment[i][0] = (increment[i][0] > 0) ? increment[i][0] + 1
							: (increment[i][0] < 0) ? increment[i][0] - 1 : 0;
					if (!verifyIn(board, column + increment[i][0], j
							+ increment[i][1]))
						break;

				}
			}
			if (verifyIn(board, column + increment[i + 4][0], j
					+ increment[i + 4][1])) {
				while (board.get(column + increment[i + 4][0], j
						+ increment[i + 4][1]) == token) {
					count++;
					check = true;
					increment[i + 4][1] = (increment[i + 4][1] > 0) ? increment[i + 4][1] + 1
							: (increment[i + 4][1] < 0) ? increment[i + 4][1] - 1
									: 0;
					increment[i + 4][0] = (increment[i + 4][0] > 0) ? increment[i + 4][0] + 1
							: (increment[i + 4][0] < 0) ? increment[i + 4][0] - 1
									: 0;
					if (!verifyIn(board, column + increment[i + 4][0], j
							+ increment[i + 4][1]))
						break;

				}
			}
			if (check) {
				count++;
			}
		}
		return (count == 0) ? 1 : count;
	}

	/**
	 * Count the number of direction where there are tokens.
	 * 
	 * @param board
	 * @param token
	 * @param column
	 * @return
	 */
	public int countDirection(Board board, char token, int column) {
		int[][] direction = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 },
				{ 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 } };
		int j = board.getTopPosition(column);
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (verifyIn(board, column + direction[i][0], j + direction[i][1])
					&& verifyIn(board, column + direction[i + 4][0], j
							+ direction[i + 4][1])) {
				if (board.get(column + direction[i][0], j + direction[i][1]) == token
						|| board.get(column + direction[i + 4][0], j
								+ direction[i + 4][1]) == token) {
					count++;
				}
			} else if (verifyIn(board, column + direction[i][0], j
					+ direction[i][1])) {
				if (board.get(column + direction[i][0], j + direction[i][1]) == token) {
					count++;
				}
			} else if (verifyIn(board, column + direction[i + 4][0], j
					+ direction[i + 4][1])) {
				if (board.get(column + direction[i + 4][0], j
						+ direction[i + 4][1]) == token) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Verify if the coordinate column/height is in the board.
	 * 
	 * @param board
	 * @param column
	 * @param height
	 * @return boolean
	 */
	private boolean verifyIn(Board board, int column, int height) {
		if (column >= board.getColumns() || column < 0
				|| height >= board.getHeight() || height < 0)
			return false;
		return true;
	}

	/**
	 * Make a combination between the sum and the direction's number
	 * 
	 * @param numberSum
	 * @param numberDirection
	 * @return
	 */
	public float addWeight(int numberSum, int numberDirection) {
		switch (numberDirection) {
		case 1:
			if (numberSum >= 4)
				return 15;
			else
				return (float) 15 / (4 - 1) * numberSum - (float) 15 / (4 - 1);
		case 2:
			if (numberSum >= 7)
				return 15;
			else
				return (float) 15 / (7 - 1) * numberSum - (float) 15 / (7 - 1);
		case 3:
			if (numberSum >= 12)
				return 15;
			else
				return (float) 15 / (12 - 1) * numberSum - (float) 15
						/ (12 - 1);
		case 4:
			if (numberSum >= 16)
				return 15;
			else
				return (float) 15 / (16 - 1) * numberSum - (float) 15
						/ (16 - 1);
		default:
			return 0;
		}
	}

}
