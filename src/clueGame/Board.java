/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 */
package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

import experiment.BoardCell;

public class Board {
	private static final int MAX_BOARD_SIZE = 100; 
	private int numRows;
	private int numColumns; 

	public BoardCell[][] board; 

	public HashMap<Character, String> legend = new HashMap<Character, String>();

	public Map<BoardCell, Set<BoardCell>> adjMatrix;

	public Set<BoardCell> targets; 

	public String boardConfigFile;

	public String roomConfigFile;

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}


	public void initialize() {
		try {
			loadRoomConfig();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (BadConfigFormatException e) {
			e.getMessage();		}

	}
	
	/*
	 * Function for returning the legend of the board given the roomConfigFile(Legend)
	 * File reader reads in the file stopping at commas and new lines, and puts the 
	 * Character and String from the legend file into the legend map. 
	 */

	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {

		FileReader reader = new FileReader(roomConfigFile);
		Scanner in = new Scanner(reader); 
		in.useDelimiter(",|\\n");
		while(in.hasNextLine()) {
			String character = in.next();
			String roomName = in.next();
			String card = in.next();

			roomName = roomName.substring(1); 

			char c = character.charAt(0); 

			legend.put(c, roomName);
		}



		}

	public void loadBoardConfig() {

	}

	public void calcAdjacencies() {

	}

	public void calcTargets(BoardCell cell, int pathLength) {

	}
	/*
	 * Function for taking in two file names and assinging them to the board Configuration file and the
	 * room configuration file. 
	 */
	public void setConfigFiles(String board, String room) {
		boardConfigFile = board;
		
		roomConfigFile = room;

	}
	

	public  java.util.Map<Character, String> getLegend() {
		
		return legend;
		
	}
	
	
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public clueGame.BoardCell getCellAt(int row, int col) {
		return null; 
	}




}
