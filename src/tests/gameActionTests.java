/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 */
package tests;

import static org.junit.Assert.*;


import java.awt.Color;

import org.junit.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.HumanPlayer;
import clueGame.Solution;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;


public class gameActionTests {
	
	public static Board board;

	//Room cards
	public static Card cloakRoomCard = new Card("Cloak Room", CardType.ROOM);
	Card archeryRoomCard = new Card("Archery Room", CardType.ROOM);
	Card kitchenCard = new Card("Kitchen", CardType.ROOM);
	Card stablesCard = new Card("Stables", CardType.ROOM);
	Card ballroomCard = new Card("Ballroom", CardType.ROOM);
	//People cards
	Card amosCard = new Card("Amos Lee", CardType.PERSON);
	Card garrusCard = new Card("Garrus Vakarian", CardType.PERSON);
	Card jimCard = new Card("Jim Holden", CardType.PERSON);
	Card taliCard = new Card("Tali Zorah", CardType.PERSON);
	Card malCard = new Card("Mal Reynolds", CardType.PERSON);
	Card johnCard = new Card("John Galt", CardType.PERSON);
	//Weapon cards
	Card revolverCard = new Card("Revolver", CardType.WEAPON);
	Card wrenchCard = new Card("Wrench", CardType.WEAPON);
	Card ropeCard = new Card("Rope", CardType.WEAPON);
	Card knifeCard = new Card("Knife", CardType.WEAPON);
	Card candleCard = new Card("Candle Stick", CardType.WEAPON);
	Card leadCard = new Card("Lead Pipe", CardType.WEAPON);
	
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
			//Computer player picks a target
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
				fail("Target not valid");
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
		//Desired location that doesn't change
		BoardCell desiredLocation = board.getCellAt(0, 16);
		for(int i = 0; i < 100; i++) {
			//Computer player selects target each loop
			BoardCell selectedTarget = player.pickLocation(board.getTargets());
			assertEquals(selectedTarget,desiredLocation);
			//Reset last visited
			player.setLastVisited(' ');
		}
	}
	
	@Test
	public void testTargetRoomLastVisited() {
		ComputerPlayer player = new ComputerPlayer();
		//Test location that has access to a room
		board.calcTargets(16, 18, 2);
		//Locations that can be picked
		//Row 16 Col 20
		boolean location1 = false;
		//Row 15 Col 17
		boolean location2 = false;
		//Row 16 Col 16
		boolean location3 = false;
		
		for(int i = 0; i < 100; i++) {
			//Computer player selects target each loop
			BoardCell selectedTarget = player.pickLocation(board.getTargets());
			
			if(selectedTarget.equals(board.getCellAt(16, 20))) {
				location1 = true;
			}
			else if(selectedTarget.equals(board.getCellAt(15, 17))) {
				location2 = true;
			}
			else if(selectedTarget.equals(board.getCellAt(16, 16))) {
				location3 = true;
			}
			else {
				fail("Target not valid");
			}
		}
		assertTrue(location1);
		assertTrue(location2);
		assertTrue(location3);
	}
	
	@Test
	public void testMakeAccustation() {
		//Set sample solution
		board.setAnswer(new Solution("Garrus Vakarian","Gallery","Wrench"));
		//Tests correct accusation
		assertTrue(board.checkAccusation(new Solution("Garrus Vakarian","Gallery","Wrench")));
		//Tests false accusations
		assertTrue(board.checkAccusation(new Solution("Jim Holden","Gallery","Wrench")));
		assertTrue(board.checkAccusation(new Solution("Garrus Vakarian","Stables","Wrench")));
		assertTrue(board.checkAccusation(new Solution("Garrus Vakarian","Gallery","Revolver")));
	}
	
	@Test
	public void testCreateSuggestion() {
		ComputerPlayer player = new ComputerPlayer("Jim Holden", 0, 0, Color.black);
		
		//Add all person cards to seen except the anticipated suggested
		player.updateSeenCards(garrusCard);
		player.updateSeenCards(jimCard);
		player.updateSeenCards(amosCard);
		player.updateSeenCards(malCard);
		player.updateSeenCards(johnCard);
		
		//Add all weapon cards to seen except the anticipated suggested
		player.updateSeenCards(wrenchCard);
		player.updateSeenCards(ropeCard);
		player.updateSeenCards(knifeCard);
		player.updateSeenCards(leadCard);
		player.updateSeenCards(candleCard);
		
		
		player.createSuggestion("Archery Room");
		
		assertEquals("Tali Zorah", player.getSuggestedPerson());
		assertEquals("Archery Room", player.getSuggestedRoom());
		assertEquals("Revolver", player.getSuggestedWeapon());
		
	}
	
	@Test
	public void testCreateSuggestionMultiple() {
		ComputerPlayer player = new ComputerPlayer("Jim Holden", 0, 0, Color.black);
		
		
		//Add all person cards to seen except 2 anticipated suggestions
		player.updateSeenCards(garrusCard);
		player.updateSeenCards(jimCard);
		
		//Add all weapon cards to seen except 2 anticipated suggestions
		player.updateSeenCards(wrenchCard);
		player.updateSeenCards(ropeCard);

		player.createSuggestion("Ballroom");
		boolean guessAmos = false;
		boolean guessTali = false;
		boolean guessKnife = false;
		boolean guessRevolver = false;
		
		//Run a lot of times to allow for random picks
		for(int i = 0; i < 100; i++) {
			if(player.getSuggestedPerson().contentEquals("Amos Lee")) {
				guessAmos = true;
			}
			else if(player.getSuggestedPerson().contentEquals("Tali Zorah")) {
				guessTali = true;
			}
			if(player.getSuggestedWeapon().equals("Knife")) {
				guessKnife = true;
			}
			else if(player.getSuggestedWeapon().equals("Revolver")) {
				guessRevolver = true;
			}
			//Make sure that guessed room is current room
			assertEquals("Ballroom",player.getSuggestedRoom());
		}
		
		//Make sure that each was picked at least once
		assertTrue(guessAmos);
		assertTrue(guessTali);
		assertTrue(guessKnife);
		assertTrue(guessRevolver);

	}
	
	@Test
	public void testDisproveSuggestionOneMatch() {
		ComputerPlayer player = new ComputerPlayer("Amos Lee", 1, 1, Color.red);
		
		Solution suggestion = new Solution("Jim Holden", "Ballroom", "Wrench");

		//Test no match
		assertEquals(null, player.disproveSuggestion(suggestion));

		//Add cards to hand
		player.hand.add(revolverCard);
		player.hand.add(amosCard);
		player.hand.add(kitchenCard);
		player.hand.add(knifeCard);
		player.hand.add(archeryRoomCard);
		player.hand.add(jimCard);
		
		//Test one match
		assertEquals(jimCard,player.disproveSuggestion(suggestion));
	}
	
	@Test
	public void testDisproveSuggestionsMultMatch() {
		ComputerPlayer player = new ComputerPlayer("Amos Lee", 1, 1, Color.red);
		
		Solution suggestion = new Solution("Jim Holden", "Ballroom", "Wrench");
		
		//Add cards to hand
		player.hand.add(revolverCard);
		player.hand.add(amosCard);
		player.hand.add(kitchenCard);
		player.hand.add(knifeCard);
		player.hand.add(wrenchCard);
		player.hand.add(ballroomCard);
		player.hand.add(jimCard);
		
		boolean returnJim = false;
		boolean returnBallroom = false;
		boolean returnWrench = false;
		
		for(int i = 0; i < 100; i++) {
			Card returnFromDisprove = player.disproveSuggestion(suggestion);
			
			if(returnFromDisprove.equals("Jim Holden")) {
				returnJim = true;
			}
			else if(returnFromDisprove.equals("Ballroom")) {
				returnBallroom = true;
			}
			else if(returnFromDisprove.equals("Wrench")) {
				returnWrench = true;
			}
		}
		
		assertTrue(returnJim);
		assertTrue(returnBallroom);
		assertTrue(returnWrench);
	}
	
	@Test
	public void testHandleSuggestion() {
		
	}
}
