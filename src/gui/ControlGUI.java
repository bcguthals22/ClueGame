/*
 * Authors: Nicholas Wenzel and Brennen Guthals
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JPanel {

	public ControlGUI() {
		

		setSize(new Dimension(900, 500));
		
		//Terminates program on close
		//Reducing vertical spaceing 
		setLayout(new GridLayout(2,0, 30, 0));
		
		JPanel panel = new JPanel();
		
		JPanel topPanel = new JPanel();
		
		JPanel bottomPanel = new JPanel();
		
		/*
		 * Adding each induviual panel to either the top or bottom panel
		 * Then adding them to the frame 
		 */
		
		panel = whoseTurnBox();
		topPanel.add(panel);
		panel = addControlButtons();
		topPanel.add(panel);
		panel = dieRollBox();
		bottomPanel.add(panel);
		panel = guessBox();
		bottomPanel.add(panel); 
		panel = guessResBox();
		bottomPanel.add(panel); 
		
		
		JPanel totalPanel = new JPanel();
		
		totalPanel.setLayout(new GridLayout(2,0,40,0));
		
		totalPanel.add(topPanel);
		totalPanel.add(bottomPanel);
		add(totalPanel);
	}
	/*
	 * Adding the buttons for the next player and make accusation
	 */
	public JPanel addControlButtons() {
		
		JPanel panel = new JPanel();
		
		JButton nextPlayer = new JButton("Next Player");
		JButton makeAccu = new JButton("Make Accusation");
		
		//Setting their sizes 
		nextPlayer.setPreferredSize(new Dimension(250, 100));
		makeAccu.setPreferredSize(new Dimension(250, 100));
		panel.add(nextPlayer);
		panel.add(makeAccu);
		
		return panel;
	}
	
	/*
	 * Adding whose turn box. Lets player know whose turn it is 
	 */
	public JPanel whoseTurnBox() {
		
		JPanel panel = new JPanel();
		
		JTextField playerTurn;
		JLabel label = new JLabel("Whose turn?");
		
		Border border = BorderFactory.createLineBorder(Color.black);
		
		playerTurn = new JTextField(20);
		
		playerTurn.setEditable(false);
		
		playerTurn.setBorder(border);
		
		panel.add(label);
		panel.add(playerTurn);
		
		return panel; 
		
	}
	/*\
	 * Added die roll box has a text feild to display dice roll and a etched border
	 */
	public JPanel dieRollBox() {
		JPanel panel = new JPanel();

		JTextField rollNum;
		JLabel label = new JLabel("Roll");

		rollNum = new JTextField(5);

		rollNum.setEditable(false);

		panel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		
		panel.add(label);
		
		panel.add(rollNum);
		
		return panel; 
	}
	
	/*
	 * Guess box with text field fro displaying the guess and etched border
	 */
	public JPanel guessBox() {
		JPanel panel = new JPanel();
		
		JTextField personGuess;
		
		JLabel label = new JLabel("Guess");
		
		personGuess = new JTextField(20);
		
		personGuess.setEditable(false);
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		
		panel.add(label);
		
		panel.add(personGuess);
		
		
		return panel;
		
	}
	/*
	 * Guess result box with a uneditable text field that will tell the player the result of the guess. 
	 */
	public JPanel guessResBox() {
		JPanel panel = new JPanel();
		
		JTextField response;
		
		JLabel label = new JLabel("Response");
		
		response = new JTextField(10);
		
		response.setEditable(false);
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		
		panel.add(label);
		panel.add(response);
		
		return panel; 
	}




	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();

		gui.setVisible(true);

	}


}
