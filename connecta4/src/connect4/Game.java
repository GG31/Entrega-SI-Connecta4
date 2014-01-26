//

package connect4;

/**
 * The game has a board and 2 players which each can choose a column to play.
 * 
 * @author zz
 * 
 */
public class Game {
	public final int COLUMNS = 7;
	public final int HEIGHT = 6;

	private Board board;
	private int shift;
	private Player[] players;

	public Game() {
		board = new Board(COLUMNS, HEIGHT);
		shift = 0;
		this.players = new Player[2];
		players[0] = new HumanPlayer('X', board);
		players[1] = new FuzzyPlayer('O', board, 'X');
	}

	public void play() {
		while (!board.isFinal()) {
			System.out.println(board);
			board.annotate(players[shift].selectMovement(),
					players[shift].token());
			shift = (shift + 1) % 2;
		}

		System.out.println("-- final board --");
		System.out.println(board);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();

		System.exit(0);
	}
}
