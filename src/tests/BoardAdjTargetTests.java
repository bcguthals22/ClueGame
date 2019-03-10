package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTests {
	private static Board board;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	@Test
	public void testWalkway() {
		Set<BoardCell> adj = new HashSet<BoardCell>(); 
		
		//Testing walkway with only walkway cells around it
		adj = board.getAdjList(11, 4);
		//Should have a size of 4
		assertEquals(4, adj.size());
		
		
		//Testing left had side of walkway should have 2 adjs being (10, 1) and (11, 0)
		adj = board.getAdjList(10, 0);
		assertTrue(adj.contains(board.getCellAt(10, 1)));
		assertTrue(adj.contains(board.getCellAt(11, 0)));
		assertEquals(2, adj.size());
		
		
		//Testing the right hand side of the walkway, should have 3 adjs being (6,21) (7,20) (8,21)
		adj = board.getAdjList(7, 21);
		assertTrue(adj.contains(board.getCellAt(6, 21)));
		assertTrue(adj.contains(board.getCellAt(7, 20)));
		assertTrue(adj.contains(board.getCellAt(8, 21)));
		assertEquals(3, adj.size());
		
		
		
	}
	
	
	@Test
	public void testInRoom() {
		Set<BoardCell> adj = new HashSet<BoardCell>();
		
		//Testing in top of room, should have no adj
		adj = board.getAdjList(0, 1);
		assertEquals(0, adj.size());
		
		//Testing in middle of the room, should have no adj
		adj = board.getAdjList(12, 19);
		assertEquals(0, adj.size());
		
		
		//Testing at right hand side of room, should have no adj
		adj = board.getAdjList(18, 21);
		assertEquals(0, adj.size());
		
		
	}

}
