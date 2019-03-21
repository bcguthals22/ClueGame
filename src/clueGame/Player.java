/*
 * Brennan Guthals & Nicolas Wenzel
 * 3/21/2019
 * CSCI306 ClueGame
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private String playerName;

	private int row;

	private int column; 

	private Color color; 

	public PlayerType type;

	public ArrayList<Card> hand;


	public Card disproveSuggestion(Solution suggestion) {

		return null; 
	}

	public String getPlayerName() {
		return playerName;
	}


	public int getRow() {
		return row;
	}


	public int getColumn() {
		return column;
	}


	public Color getColor() {
		return color;
	}



}