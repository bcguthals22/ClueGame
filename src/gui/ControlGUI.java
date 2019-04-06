/*
 * Authors: Nicholas Wenzel and Brennen Guthals
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlGUI extends JFrame {

	public ControlGUI() {
		

		setSize(new Dimension(700, 500));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Control GUI");
		
		JPanel panel = new JPanel();
		
		panel = addControlButtons();
		
		add(panel);
		

		
		
		
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




	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();

		gui.setVisible(true);

	}


}
