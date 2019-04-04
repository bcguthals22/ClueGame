/*
 * Brennan Guthals & Nicolas Wenzel
 * 3/21/2019
 * CSCI306 ClueGame
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {

	private String playerName;

	private int row;

	private int column; 

	private Color color; 
	
	private ArrayList<Card> cards = new ArrayList();

	public PlayerType type;

	public ArrayList<Card> hand = new ArrayList<Card>();
	
	public ArrayList<Card> seenCards;

	public Player() {
		
	}
	
	public Player(String name, int row, int col, Color color) {
		this.playerName = name;
		this.row = row;
		this.column = col;
		this.color = color;
	}

	public Card disproveSuggestion(Solution suggestion) {
		int numMatching = 0;
		ArrayList<Card> matchingCards = new ArrayList();
		for(Card c : hand) {
			if(c.getCardName().equals(suggestion.person)) {
				matchingCards.add(c);
			}
			else if(c.getCardName().equals(suggestion.room)) {
				matchingCards.add(c);
			}
			else if(c.getCardName().equals(suggestion.weapon)) {
				matchingCards.add(c);
			}
		}
		if(matchingCards.size() == 1) {
			return matchingCards.get(0);
		}
		else if(matchingCards.size() > 1) {
			Random rand = new Random();
			int index = rand.nextInt(matchingCards.size());
			return matchingCards.get(index);
		}
		return null;
	}

	
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void updateSeenCards(Card card) {
		seenCards.add(card);
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
	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setColor(Color color) {
		this.color = color;
	}



}