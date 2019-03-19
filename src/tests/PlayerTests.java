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
import clueGame.Player;

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

	@Test
	public void testPlayer1() {
		Player player = new Player();
		assertEquals("name",player.getPlayerName());
		assertEquals(Color.red,player.getColor());
		assertEquals(0,player.getColumn());
		assertEquals(0,player.getRow());
	}
	
	@Test
	public void testPlayer3() {
		Player player = new Player();
		assertEquals("name",player.getPlayerName());
		assertEquals(Color.red,player.getColor());
		assertEquals(0,player.getColumn());
		assertEquals(0,player.getRow());
		
		
	}
	
	@Test
	public void testPlayer6() {
		Player player = new Player();
		assertEquals("name",player.getPlayerName());
		assertEquals(Color.red,player.getColor());
		assertEquals(0,player.getColumn());
		assertEquals(0,player.getRow());
		
		
	}
	
	@Test
	public void testDeckCards() {
		
	}
	
	@Test
	public void dealCard() {
		
	}

}
