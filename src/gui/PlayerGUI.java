/*
 * Nicholas Wenzel and Brennen Guthals 
 */

package gui;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;

public class PlayerGUI extends JPanel {
	
	private ArrayList<Card> cards;
	
	
	private Board board;
	
	
	public PlayerGUI(Board boardFromClueGame) {
		
		board = boardFromClueGame;
		
		setLayout(new GridLayout(6,1));
		
		setBorder(new TitledBorder(new EtchedBorder(), "My Cards"));
		
		cards = board.players.get(0).hand;
		
		
		makePanels("People", CardType.PERSON);
		
		
		makePanels("Weapons", CardType.WEAPON);
		
		
		makePanels("Rooms", CardType.ROOM);
		
		
		
	}
	
	
	private void makePanels(String label, CardType type) {
		
		JPanel panel = new JPanel();
		
		
		panel.setLayout(new GridLayout(0, 1));
		
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), label));
		
		for (Card card : cards) {
			if (card.getCardType() == type) {
				JTextField field = new JTextField(card.getCardName());
				
				field.setEditable(false);
				
				
				panel.add(field);
				
			}
		}
		
		add(panel);
		
	}
}
	

