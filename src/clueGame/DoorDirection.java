/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 */
package clueGame;

public enum DoorDirection {
	UP("Up"), DOWN("Down"), LEFT("Left"), RIGHT("Right"), NONE("None");
	String message;
	DoorDirection(String message){
		this.message = message;
	}
}

