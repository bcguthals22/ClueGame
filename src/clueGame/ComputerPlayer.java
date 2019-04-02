package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	ArrayList<Card> seenCards;
	
	private char lastVisitedRoom;
	
	private Solution suggestion;
	
	public ComputerPlayer() {
		seenCards = new ArrayList();
		suggestion = new Solution();
	}
	
	public ComputerPlayer(String name, int row, int col, Color color) {
		super();
		this.setPlayerName(name);
		this.setRow(row);
		this.setColumn(col);
		this.setColor(color);
		seenCards = new ArrayList();
		suggestion = new Solution();
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null; 
	}
	
	public void makeAccusation() {
		
	}
	
	public void createSuggestion(String room) {
		suggestion.room = room;
		
		ArrayList<Card> people= new ArrayList();
		
		ArrayList<Card> weapons = new ArrayList();
			
		for(Card c : Board.getInstance().deck) {
			boolean notSeen = false;
			int counter = 0;
			String deckCardName = c.getCardName();
			for(Card card : seenCards) {
				String seenCardName = card.getCardName();
				
				if(seenCardName.contains(deckCardName)) {
					continue;
				}
				if(c.type.equals(CardType.ROOM)) {
					continue;
				}
				counter++;
			}
			if(counter == seenCards.size()) {
				if(c.type.equals(CardType.PERSON)) {
					people.add(c);
				}
				else if(c.type.equals(CardType.WEAPON)) {
					weapons.add(c);
				}
			}
		}
		
		Random rand = new Random();
		
		int random = rand.nextInt(people.size());
		suggestion.person = people.get(random).getCardName();
		random = rand.nextInt(weapons.size());
		suggestion.weapon = weapons.get(random).getCardName();
		
	}
	
	//Function to allow for proper testing
	public void setLastVisited(char c) {
		this.lastVisitedRoom = c;
	}
	
	public void updateSeenCards(Card card) {
		seenCards.add(card);
	}
	
	public Solution getSuggestion() {
		
		return null;
	}
	
	public String getSuggestedRoom() {
		return suggestion.room;
	}
	
	public String getSuggestedWeapon() {
		return suggestion.weapon;
	}
	
	public String getSuggestedPerson() {
		return suggestion.person;
	}
}
