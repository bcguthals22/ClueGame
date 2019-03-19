/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 */
package clueGame;

public class BoardCell {
	public int row;

	public int column;
	
	private String type;
	
	private char firstLetter;

	public BoardCell(int row, int column, String room) {
		super();
		this.row = row;
		this.column = column;
		this.type = room;
		firstLetter = type.charAt(1);
	}
	
	/*
	 * Function to determine if a specific cell is a door.
	 * Returns true if the length is 2 and the second character is not 'N'
	 * Else it returns false
	 */
	public boolean isDoorway() {
		if(type.length() == 2 && firstLetter != 'N') {
			return true;
		}
		else {
			return false; 
		}
	}
	
	public boolean isWalkway() {
		String x = this.getInitial();
		if(getInitial().equals("W")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Function for returning the direction of the door. 
	 * If the cell is a door, then the direction is determined by the second letter.
	 * Else NONE is returned.
	 */
	public DoorDirection getDoorDirection() {
		if(isDoorway()) {
			if(firstLetter == 'R') {
				return DoorDirection.RIGHT;
				
			}
			else if(firstLetter == 'L') {
				return DoorDirection.LEFT;
			}
			else if(firstLetter == 'D') {
				return DoorDirection.DOWN;
			}
			else if(firstLetter == 'U') {
				return DoorDirection.UP;
			}
			else {
				return DoorDirection.NONE;
			}
		}
		
		return DoorDirection.NONE;
	}

	public String getInitial() {
		return type.substring(0,1); 
	}



}