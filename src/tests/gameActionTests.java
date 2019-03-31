/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 */
package tests;

import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.HumanPlayer;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;


public class gameActionTests {
	
	public static Board board;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		board = board.getInstance();
		board.setConfigFiles("ClueMap.csv", "ClueMapLegend.txt");		
		board.initialize();
		
	}
	
	@Test
	public void testTargetRandom() {
		ComputerPlayer player = new ComputerPlayer();
		//Test location with no rooms
		board.calcTargets(16, 17, 2);
		//Three locations that should be picked
		//Row 16 Col 19
		boolean location1 = false;
		//Row 16 Col 15
		boolean location2 = false;
		//Row 15 Col 16
		boolean location3 = false;
		
		//Test a lot of times
		for(int i = 0; i < 100; i++) {
			//Have computer pick a target
			BoardCell selectedTarget = player.pickLocation(board.getTargets());
			if(selectedTarget.equals(board.getCellAt(16, 19))) {
				location1 = true;
			}
			else if(selectedTarget.equals(board.getCellAt(16, 16))) {
				location2 = true;
			}
			else if(selectedTarget.equals(board.getCellAt(15,16))) {
				location3 = true;
			}
			else {
				fail("Invalid target selected");
			}
		}
		assertTrue(location1);
		assertTrue(location2);
		assertTrue(location3);
	}
	
	@Test
	public void testTargetRoom() {
		ComputerPlayer player = new ComputerPlayer();
		//Test location next to room
		board.calcTargets(1, 15, 2);
		//Desired location
		BoardCell desiredLocation = board.getCellAt(0, 16);
		for(int i = 0; i < 100; i++) {
			BoardCell selectedTarget = player.pickLocation(board.getTargets());
			assertEquals(selectedTarget,desiredLocation);
			player.setLastVisited(' ');
		}
		
		
	}
	
	@Test
	public void testMakeAccustation() {
		
	}
	
	@Test
	public void testCreateSuggestion() {
		
	}
	
	@Test
	public void testDisproveSuggestion() {
		
	}
	
	@Test
	public void testHandleSuggestion() {
		
	}
}
