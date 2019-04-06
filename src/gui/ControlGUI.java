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
		

		setSize(new Dimension(700, 500));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Control GUI");
		
		JPanel panel = new JPanel();
		
		panel = addControlButtons();
		add(panel);
		panel = whoseTurnBox();
		add(panel, BorderLayout.WEST);
		panel = dieRollBox();
		add(panel, BorderLayout.SOUTH);
		

		
		
		
	}
	
	public JPanel addControlButtons() {
		
		JPanel panel = new JPanel();
		
		JButton nextPlayer = new JButton("Next Player");
		JButton makeAccu = new JButton("Make Accusation");
		
		
		nextPlayer.setPreferredSize(new Dimension(150, 100));
		makeAccu.setPreferredSize(new Dimension(150, 100));
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

		panel.add(rollNum);
		
		return panel; 
	}




	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();

		gui.setVisible(true);

	}


}
