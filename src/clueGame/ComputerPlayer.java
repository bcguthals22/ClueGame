/*
 * Authors Nicholas Wenzel and Brennen Guthals
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import gui.ControlGUI;

public class ComputerPlayer extends Player {

	private char lastVisitedRoom;

	private Solution suggestion;

	private String currentRoom;
	
	public ComputerPlayer() {
		seenCards = new ArrayList();
		suggestion = new Solution();
	}
	/*
	 * Constructor for newComputer players with name, color and starting location
	 */
	public ComputerPlayer(String name, int row, int col, Color color) {
		super();
		this.setPlayerName(name);
		this.setRow(row);
		this.setColumn(col);
		this.setColor(color);
		seenCards = new ArrayList();
		suggestion = new Solution();
	}
	
	/*
	 * Function for picking the next location of the computer players
	 */
	public BoardCell pickLocation(Set<BoardCell> targets) {
		Random rand = new Random();
		
		BoardCell pick = new BoardCell();
		
		int counter = 0;
		
		int index = rand.nextInt(targets.size());
		for(BoardCell cell : targets) {
			//See if room, and if it was last visited, if not set as last visited and pick room
			if(cell.isDoorway() && (cell.getInitial().charAt(0) != lastVisitedRoom)) {
				lastVisitedRoom = cell.getInitial().charAt(0);
				return cell;
			}

			if(index == counter) {
				pick = cell;
			}
			counter++;

		}


		return pick; 
	}

	public void makeAccusation(Solution accusation) {
		
	}
	/*
	 * Function that handles creating suggestions for computer players. Takes in a string as a room
	 * and makes a guess based on the seen cards in its hand and from previous suggestings 
	 */
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
	/*
	 * (non-Javadoc)
	 * @see clueGame.Player#move(clueGame.Board)
	 * 
	 * Function for moving the computer players. Uses the pickLocation function and the current targets list 
	 * and sets the new location to one of these possible targets. 
	 */
	@Override
	public void move(Board board) {
		finished = true;
		
		Set<BoardCell> targets = Board.getInstance().targets;
		
		BoardCell newLoc = pickLocation(targets);
		
		setRow(newLoc.row);
		setColumn(newLoc.column);
		
		lastVisitedRoom = newLoc.getInitial().charAt(0); 
		
		if(newLoc.isDoorway()) {
			String initial = newLoc.getInitial();
			switch(initial) {
			case "C":
				currentRoom = "Cloak Room";
				break;
			case "G":
				currentRoom = "Gallery";
				break;
			case "A":
				currentRoom = "Archery Range";
				break;
			case "D":
				currentRoom = "Drawing Room";
				break;
			case "I":
				currentRoom = "Wine Cellar";
				break;
			case "S":
				currentRoom = "Stables";
				break;
			case "R":
				currentRoom = "Billiard Room";
				break;
			case "K":
				currentRoom = "Kitchen";
				break;
			case "B":
				currentRoom = "Ballroom";
				break;
			case "P":
				currentRoom = "Parking Garage";
				break;
			case "L":
				currentRoom = "Swimming Pool";
				break;
			}
			createSuggestion(currentRoom);
			ControlGUI.guessField.setText(suggestion.person + " " + suggestion.room + " " + suggestion.weapon);
			
			Card card = Board.getInstance().handleSuggestion(suggestion, this);
			
			ControlGUI.guessResField.setText(card.getCardName());
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see clueGame.Player#finishTurn(clueGame.BoardCell)
	 * 
	 * In computer player this function does nothing becuase the movement is autonomouse
	 */
	@Override
	public void finishTurn(BoardCell cell) {
		
	}

}
