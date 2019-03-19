/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 * 
 */
package clueGame;

import java.io.FileNotFoundException;


import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.*;




public class Board {
	private static final int MAX_BOARD_SIZE = 100; 
	private int numRows;
	private int numColumns; 

	public BoardCell[][] board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE]; 

	public HashMap<Character, String> legend = new HashMap<Character, String>();

	public HashMap<BoardCell, Set<BoardCell>> adjMatrix;

	public Set<BoardCell> targets; 

	public Set<BoardCell> visited;

	public String boardConfigFile;

	public String roomConfigFile;

	// Variable used for singleton pattern
	private static Board theInstance = new Board();
	// Constructor is private to ensure only one can be created
	private Board() {
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();

	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void resetVariables() {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
	}
	public void initialize() {
		try {
			loadRoomConfig();

			loadBoardConfig();


		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (BadConfigFormatException e) {
			e.getMessage();		}

		calcAdjacencies(); 
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
			card = card.substring(1); 

			if((!card.contains("Card") && (!card.contains("Other")))) {
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
	public Map<Character, String> getLegend() {
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

	public void calcTargets(int i, int j, int numSteps) {
		BoardCell startCell = getCellAt(i,j);
		targets = new HashSet();
		visited = new HashSet();
		visited.add(startCell);

		findTargets(i, j, numSteps);
	}

	public void findTargets(int i, int j, int numSteps) {
		BoardCell startCell = getCellAt(i,j);		
		Set<BoardCell> list = (Set)adjMatrix.get(startCell);
		for (BoardCell adjCell : list)
		{
			if (!visited.contains(adjCell))
			{
				visited.add(adjCell);

				if (adjCell.isDoorway()) {
					targets.add(adjCell);
				}
				else if (numSteps == 1) {
					targets.add(adjCell);
				}
				else {
					findTargets(adjCell.row,adjCell.column, numSteps - 1);

				}
				visited.remove(adjCell);
			}
		}
	}


	public Set<BoardCell> getTargets() {
		return targets;
	}
	//Iterates through each boardcell in boardcell and calls addAdj for each cell 
	public void calcAdjacencies() {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns;  col++) {
				addAdj(row,col);
			}
		}
	}
	//Makes adjacency matrix for cell passed in.
	public void addAdj(int row, int col) {
		Set<BoardCell> adj = new HashSet();
		BoardCell cell = getCellAt(row,col);

		if(cell.isWalkway()) {
			//Check if door is facing the right direction
			caseWalkway(row-1,col,adj,DoorDirection.DOWN);
			caseWalkway(row+1,col,adj,DoorDirection.UP);
			caseWalkway(row,col-1,adj,DoorDirection.RIGHT);
			caseWalkway(row,col+1,adj,DoorDirection.LEFT);
		}
		else if(cell.isDoorway()) {
			DoorDirection direction = cell.getDoorDirection();
			caseDoor(row,col,adj,direction);
		}

		adjMatrix.put(cell, adj);
	}
	//Used in case of walkway
	public void caseWalkway(int row, int col, Set<BoardCell> nextTo, DoorDirection direction) {
		if ((row < 0) || (col < 0) || (row >= numRows) || (col >= numColumns)) {
			return;
		}
		BoardCell cell = board[row][col];

		if (cell.isWalkway()) {
			nextTo.add(cell);
		}
		else if (cell.isDoorway())
		{
			DoorDirection dir = cell.getDoorDirection();
			if (dir == direction) {
				nextTo.add(cell);
			}
		}
	}
	//Used in case of door
	public void caseDoor(int row, int col, Set<BoardCell> nextTo, DoorDirection direction) {
		if ((direction == DoorDirection.DOWN) && (row + 1 < numRows) && (board[(row + 1)][col].isWalkway())) {
			nextTo.add(board[(row + 1)][col]);
		}
		else if ((direction == DoorDirection.UP) && (row - 1 >= 0) && (board[(row - 1)][col].isWalkway())) {
			nextTo.add(board[(row - 1)][col]);
		}
		else if ((direction == DoorDirection.LEFT) && (col - 1 >= 0) && (board[row][(col - 1)].isWalkway())) {
			nextTo.add(board[row][(col - 1)]);
		}
		else if ((direction == DoorDirection.RIGHT) && (col + 1 < numColumns) && (board[row][(col + 1)].isWalkway())) {
			nextTo.add(board[row][(col + 1)]);
		}
	}
	public Set<BoardCell> getAdjList(int i, int j) {
		Set<BoardCell> list = new HashSet();
		BoardCell cell = getCellAt(i,j);
		list = adjMatrix.get(cell);
		return list;
	}


}

