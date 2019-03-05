package clueGame;

import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

public class Board {
	private static final int MAX_BOARD_SIZE = 100; 
	private int numRows;
	private int numColumns; 
	
	public BoardCell[][] board; 
	
	public Map<Character, String> legend;
	
	public Map<BoardCell, Set<BoardCell>> adjMatrix; 
	
	public Set<BoardCell> targets; 
	
	public String boardConfigFile;
	
	public String roomConfigFile;
	
	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance1() {
		return theInstance;
	}
	
	
	public static Board getInstance() {
		
		return null; 
	}
	
	
	public void initialize() {
		
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void loadBoardConfig() {
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}
	
	public void setConfigFiles(String file1, String file2) {
		
	}
	
	public Map<Character, String> getLegend() {
		return null;
	}
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public BoardCell getCellAt(int row, int col) {
		return null; 
	}

	
	
	
}
