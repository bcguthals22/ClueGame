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


public class Board {
	private static final int MAX_BOARD_SIZE = 100; 
	private int numRows;
	private int numColumns; 

	public BoardCell[][] board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE]; 

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

			loadBoardConfig();


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
			
			if(card != "Card" && card != "Other") {
				throw new BadConfigFormatException("Not a valid type: " + card);
			}

			roomName = roomName.substring(1); 

			char c = character.charAt(0); 

			legend.put(c, roomName);
		}

	}
	
	/*
	 * Function for loading in the Board Configuration File 
	 * Reads in the file and splits it into an array at commas
	 * Sets the number of cols based on size of row.
	 * Iterates through until there is no next line in the file 
	 * Makes a board cell of the current col and row and puts it into the board
	 * Updates the number of rows in the board
	 */

	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(boardConfigFile);
		Scanner in = new Scanner(reader); 
		
		int rows = 0;
		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String[] row = line.split(",");
			if(rows == 0) {
				numColumns = row.length; 
			}
			else if(numColumns != row.length) {
				throw new BadConfigFormatException("Rows do not have the same number of columns.") ;
			}
			
			for(int i = 0; i < numColumns; i++) {
				String cha = row[i];
				Character c = cha.charAt(0);
				
				String room = legend.get(c);
				
				if(room == null) {
					throw new BadConfigFormatException("Room type not defined " + cha);
				}
				
				board[rows][i] = new BoardCell(rows, i, cha); 
			}
			
			rows = rows + 1; 
			
		}
		
		numRows = rows; 
		
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
	
	/*
	 * Returns the legend
	 */
	public  java.util.Map<Character, String> getLegend() {
		
		return legend;
		
	}
	
	
	/*
	 * Returns number of rows in board
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/*
	 * Returns number of cols in board
	 */
	public int getNumColumns() {
		return numColumns;
	}
	/*
	 * Returns the cell given a row and col 
	 */
	public BoardCell getCellAt(int row, int col) {
		return board[row][col]; 
	}




}
