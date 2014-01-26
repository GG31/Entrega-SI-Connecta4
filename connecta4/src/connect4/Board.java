package connect4;

/**
 * The board where play the players.
 * 
 */
public class Board {
	private char[][] tiles;

	private int columns;
	private int height;

	public Board(int columns, int height) {
		this.columns = columns;
		this.height = height;

		tiles = new char[columns][height];
		for (int i = 0; i < columns; i++)
			for (int j = 0; j < height; j++)
				tiles[i][j] = ' ';
	}

	public Board(Board other) {
		columns = other.columns();
		height = other.height();

		tiles = new char[columns][height];
		for (int i = 0; i < columns; i++)
			for (int j = 0; j < height; j++)
				tiles[i][j] = other.get(i, j);
	}

	public int columns() {
		return columns;
	}

	public int height() {
		return height;
	}

	public boolean isFinal() {
		return false;
	}

	public boolean isFull(int column) {
		return getTopPosition(column) >= height;
	}

	public void annotate(int column, char token) {
		tiles[column][getTopPosition(column)] = token;
	}

	public char get(int column, int height) {
		return tiles[column][height];
	}

	public String toString() {
		String board = "";

		for (int j = height - 1; j >= 0; j--) {
			board += "|";
			for (int i = 0; i < columns; i++)
				board += " " + tiles[i][j] + " |";
			board += "\n";
		}

		return board;
	}

	public int getTopPosition(int column) {
		int i = 0;

		while (i < height && tiles[column][i] != ' ')
			i++;

		return i;
	}

	public int getColumns() {
		return columns;
	}

	public int getHeight() {
		return height;
	}

}
