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

public class ControlGUI extends JFrame {

	public ControlGUI() {
		

		setSize(new Dimension(900, 500));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Control GUI");
		
		setLayout(new GridLayout(2,0, 30, 0));
		
		JPanel panel = new JPanel();
		
		JPanel topPanel = new JPanel();
		
		JPanel bottomPanel = new JPanel();
		
		
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
		
		add(topPanel);
		add(bottomPanel); 
		
		pack();

		

		
		
		
	}
	
	public JPanel addControlButtons() {
		
		JPanel panel = new JPanel();
		
		JButton nextPlayer = new JButton("Next Player");
		JButton makeAccu = new JButton("Make Accusation");
		
		
		nextPlayer.setPreferredSize(new Dimension(250, 100));
		makeAccu.setPreferredSize(new Dimension(250, 100));
		panel.add(nextPlayer);
		panel.add(makeAccu);
		
		return panel;
	}
	
	
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
