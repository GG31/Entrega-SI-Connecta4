package connect4;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testToString(){
		Board board = new Board(7, 6);
		System.out.println(board.toString());
		board.annotate(2, 'X');
		board.annotate(1, 'O');
		System.out.println(board.toString());
	}
}
