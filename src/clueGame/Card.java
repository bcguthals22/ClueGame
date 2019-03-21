package clueGame;

public class Card {
	private String cardName;
	
	public CardType type;
	public boolean equals() {
		return false; 
	}
	
	public Card(String name, CardType type){
		this.cardName = name;
		this.type = type;
	}
}
