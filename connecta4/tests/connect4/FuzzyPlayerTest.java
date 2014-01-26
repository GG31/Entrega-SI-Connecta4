package connect4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FuzzyPlayerTest {
	Board board;
	FuzzyPlayer fuzzyplayer;

	@Before
	public void setUp() {
		board = new Board(7, 6);
		fuzzyplayer = new FuzzyPlayer('X', board, 'O');
	}

	@After
	public void tearDown() {
		board = null;
		fuzzyplayer = null;
	}

	@Test
	public void testCountLineMineDiag() {
		board.annotate(3, 'X');
		assertTrue(fuzzyplayer.countLineMineDiag(board, 'X', 3) == 4);
	}

	@Test
	public void testCountLineMineDiag2() {
		board.annotate(3, 'X');
		board.annotate(3, 'X');
		assertTrue(fuzzyplayer.countLineMineDiag(board, 'X', 3) == 6);
	}

	@Test
	public void testCountLineMineDiag3() {
		board.annotate(3, 'X');
		board.annotate(3, 'X');
		board.annotate(4, 'O');
		board.annotate(4, 'O');
		assertTrue(fuzzyplayer.countLineMineDiag(board, 'X', 3) == 4);
	}

	@Test
	public void testCountLineMineDiag4() {
		board.annotate(3, 'X');
		board.annotate(3, 'X');
		board.annotate(5, 'O');
		board.annotate(4, 'O');
		assertTrue(fuzzyplayer.countLineMineDiag(board, 'X', 3) == 5);
	}

	@Test
	public void testCountLineMineDiag5() {
		board.annotate(3, 'X');
		board.annotate(3, 'X');
		board.annotate(5, 'O');
		board.annotate(4, 'O');
		assertTrue(fuzzyplayer.countLineMineDiag(board, 'X', 6) == 1);
	}

	@Test
	public void testCountSumLine() {
		assertTrue(fuzzyplayer.countSumLine(board, 'X', 2) == 1);
	}

	@Test
	public void testCountSumLine1() {
		board.annotate(2, 'X');
		assertTrue(fuzzyplayer.countSumLine(board, 'X', 2) == 2);
	}

	@Test
	public void testCountSumLine2() {
		board.annotate(2, 'X');
		board.annotate(2, 'X');
		assertTrue(fuzzyplayer.countSumLine(board, 'X', 2) == 3);
	}

	@Test
	public void testCountSumLine3() {
		board.annotate(2, 'X');
		board.annotate(3, 'X');
		assertTrue(fuzzyplayer.countSumLine(board, 'X', 2) == 4);
	}

	@Test
	public void testCountSumLine3bis() {
		board.annotate(2, 'X');
		board.annotate(3, 'X');
		board.annotate(1, 'X');
		board.annotate(1, 'X');
		board.annotate(1, 'X');
		System.out.println(board.toString());
		System.out.println(fuzzyplayer.countSumLine(board, 'X', 2));
		assertTrue(fuzzyplayer.countSumLine(board, 'X', 2) == 9);
	}

	@Test
	public void testCountSumLine4() {
		board.annotate(0, 'X');
		assertTrue(fuzzyplayer.countSumLine(board, 'X', 0) == 2);
	}

	@Test
	public void testCountDirection() {
		board.annotate(0, 'X');
		board.annotate(1, 'X');
		assertTrue(fuzzyplayer.countDirection(board, 'X', 0) == 2);
	}

	@Test
	public void testCountDirection1() {
		board.annotate(0, 'X');
		board.annotate(1, '0');
		assertTrue(fuzzyplayer.countDirection(board, 'X', 0) == 1);
	}

	@Test
	public void testCountDirection2() {
		board.annotate(0, 'X');
		board.annotate(1, 'X');
		board.annotate(2, 'X');
		board.annotate(2, 'X');
		board.annotate(2, 'X');
		assertTrue(fuzzyplayer.countDirection(board, 'X', 1) == 4);
	}

	@Test
	public void testAddWeight() {
		assertTrue(fuzzyplayer.addWeight(7, 0) == 0);
		assertTrue(fuzzyplayer.addWeight(4, 1) == 15);
		assertTrue(fuzzyplayer.addWeight(7, 2) == 15);
		assertTrue(fuzzyplayer.addWeight(12, 3) == 15);
		assertTrue(fuzzyplayer.addWeight(16, 1) == 15);

		assertTrue(fuzzyplayer.addWeight(2, 1) == 5);
		assertTrue(fuzzyplayer.addWeight(3, 1) == 10);
		assertTrue(fuzzyplayer.addWeight(1, 1) == 0);

		assertTrue(fuzzyplayer.addWeight(2, 2) == 2.5);
		assertTrue(fuzzyplayer.addWeight(3, 2) == 5);
		assertTrue(fuzzyplayer.addWeight(1, 2) == 0);
		assertTrue(fuzzyplayer.addWeight(6, 2) == 12.5);
		assertTrue(fuzzyplayer.addWeight(7, 2) == 15);

		assertTrue(fuzzyplayer.addWeight(2, 3) == 1.3636364f);
		assertTrue(fuzzyplayer.addWeight(3, 3) == 2.7272725f);
		assertTrue(fuzzyplayer.addWeight(6, 3) == 6.8181815f);
		assertTrue(fuzzyplayer.addWeight(9, 3) == 10.909091f);
		assertTrue(fuzzyplayer.addWeight(12, 3) == 15);

		assertTrue(fuzzyplayer.addWeight(2, 4) == 1);
		assertTrue(fuzzyplayer.addWeight(5, 4) == 4);
		assertTrue(fuzzyplayer.addWeight(10, 4) == 9);
		assertTrue(fuzzyplayer.addWeight(15, 4) == 14);
		assertTrue(fuzzyplayer.addWeight(16, 4) == 15);
	}

	@Test
	public void testCountLineMineHorizontal() {
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 0) == 1);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 1) == 2);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 2) == 3);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 4) == 3);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 3) == 4);
	}

	@Test
	public void testCountLineMineHorizontal1() {
		board.annotate(2, 'O');
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 0) == 0);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 1) == 0);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 2) == 3);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 4) == 1);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 3) == 1);
	}

	@Test
	public void testCountLineMineHorizontal2() {
		board.annotate(2, 'X');
		board.annotate(2, 'O');
		board.annotate(3, 'X');
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 0) == 1);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 1) == 2);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 2) == 3);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 4) == 3);
		assertTrue(fuzzyplayer.countLineMineHorizontal(board, 'X', 3) == 1);
	}

	@Test
	public void testCountLineMineVertical() {
		assertTrue(fuzzyplayer.countLineMineVertical(board, 'X', 2) == 1);
		board.annotate(3, 'X');
		board.annotate(3, 'X');
		board.annotate(3, 'X');
		assertTrue(fuzzyplayer.countLineMineVertical(board, 'X', 3) == 4);
	}
	
	@Test
	public void testSelectMovement(){
		board.annotate(1, 'O');
		System.out.println("MVT "+fuzzyplayer.selectMovement());
	}
}
