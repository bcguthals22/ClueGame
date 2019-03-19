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
	
	
	public Board getInstance() {
		
		return null; 
	}
	
	
	public void initialize() {
		//test
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void loadBoardConfig() {
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}
	
}
