package clueGame;

import java.util.Set;

public class ComputerPlayer extends Player {
	
	private char lastVisitedRoom;
	
	public ComputerPlayer() {
		super();
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null; 
	}
	
	public void makeAccusation() {
		
	}
	
	public void createSuggestion() {
		
	}
	
	public void setLastVisited(char c) {
		this.lastVisitedRoom = c;
	}
}
