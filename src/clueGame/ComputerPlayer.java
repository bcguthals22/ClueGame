package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {

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

	@Override
	public void move(Board board) {
		notFinished = false;
	}
	@Override
	public void finishTurn(BoardCell cell) {
		
	}

}
