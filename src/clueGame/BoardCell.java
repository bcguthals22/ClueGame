/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 */
package clueGame;

import java.awt.Color;
import java.awt.Graphics2D;

public class BoardCell {
	public int row;

	public int column;
	
	public String type;
	
	private int x;
	
	private int y;
	
	public static int BOARD_CELL_SIZE = 30;

	public BoardCell(int row, int column, String room) {
		super();
		this.row = row;
		this.column = column;
		this.type = room;
		
		this.y = row * 30;
		this.x = column * 30;
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
	
	public void draw(Graphics2D g) {
		
		 if(isWalkway()) {
			g.setColor(Color.YELLOW);
			g.fillRect(this.x, this.y, BOARD_CELL_SIZE, BOARD_CELL_SIZE);
			g.setColor(Color.BLACK);
			g.drawRect(this.x, this.y, BOARD_CELL_SIZE, BOARD_CELL_SIZE);
		}
		
		
		else {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(this.x, this.y, BOARD_CELL_SIZE, BOARD_CELL_SIZE);
			
		}
		
		
	}



}