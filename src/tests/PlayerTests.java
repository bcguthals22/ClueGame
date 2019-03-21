package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;
import clueGame.PlayerType;

public class PlayerTests {
	private static Board board;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueMap.csv", "ClueMapLegend.txt","Player.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
		//Load player file
	}

	//Test to see if player is loaded correctly
	//In this test, see if Human player is loaded correctly
	@Test
	public void testPlayer1() {
		Player player = new Player();
		assertEquals("Steve",player.getPlayerName());
		assertEquals(Color.blue,player.getColor());
		assertEquals(0,player.getColumn());
		assertEquals(0,player.getRow());
		assertEquals(player.type,PlayerType.HUMAN);
	}
	
	//Test to see if player is loaded correctly
		//In this test, see if Computer player is loaded correctly
	@Test
	public void testPlayer3() {
		Player player = new Player();
		assertEquals("Jeph",player.getPlayerName());
		assertEquals(Color.red,player.getColor());
		assertEquals(5,player.getColumn());
		assertEquals(7,player.getRow());
		assertEquals(player.type,PlayerType.COMPUTER);
	}
	
	//Test to see if player is loaded correctly
		//In this test, see if Computer player is loaded correctly
	@Test
	public void testPlayer6() {
		Player player = new Player();
		assertEquals("Ralf",player.getPlayerName());
		assertEquals(Color.black,player.getColor());
		assertEquals(2,player.getColumn());
		assertEquals(8,player.getRow());
		assertEquals(player.type,PlayerType.COMPUTER);
	}
	
	@Test
	public void testCreateCards() {
		assertEquals(board.deck.size(), 24);
		int countWeapon = 0;
		int countPerson = 0;
		int countRoom = 0;
		for(Card i: board.deck) {
			if(i.type == CardType.WEAPON) {
				countWeapon++;
			}
			else if(i.type == CardType.PERSON) {
				countPerson++;
			}
			else if(i.type == CardType.ROOM) {
				countRoom++;
			}
		}
		assertEquals(countWeapon, 6);
		assertEquals(countRoom, 12);
		assertEquals(countPerson, 6);
		Card personCard = new Card("Steve",CardType.PERSON);
		assertTrue(board.deck.contains(personCard));
		Card weaponCard = new Card("Pipe",CardType.WEAPON);
		assertTrue(board.deck.contains(weaponCard));
		Card roomCard = new Card("Ballroom",CardType.ROOM);
		assertTrue(board.deck.contains(roomCard));
	}
	
	@Test
	public void testDealCards() {
		
	}

}
