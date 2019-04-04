/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 */
package clueGame;

public class BoardCell {
	public int row;

	public int column;
	
	public String type;

	public BoardCell(int row, int column, String room) {
		super();
		this.row = row;
		this.column = column;
		this.type = room;
	}
	
	public BoardCell() {
		
	}
	
	/*
	 * Function to determine if a specific cell is a door.
	 * Returns true if the length is 2 and the second character is not 'N'
	 * Else it returns false
	 */
	public boolean isDoorway() {
		if(type.length() == 2 && type.charAt(1) != 'N') {
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
			if(type.charAt(1) == 'R') {
				return DoorDirection.RIGHT;
				
			}
			else if(type.charAt(1) == 'L') {
				return DoorDirection.LEFT;
			}
			else if(type.charAt(1) == 'D') {
				return DoorDirection.DOWN;
			}
			else if(type.charAt(1) == 'U') {
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