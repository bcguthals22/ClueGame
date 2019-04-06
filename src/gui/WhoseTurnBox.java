/*
 * Authors Nicholas Wenzel and Breenen Guthals
 */

package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class WhoseTurnBox extends JPanel {
	
	private JTextField playerTurn;
	

	
	
	public WhoseTurnBox() {
		JLabel label = new JLabel("Whose turn?");
		
		Border border = BorderFactory.createLineBorder(Color.black);
		
		playerTurn = new JTextField(20);
		
		playerTurn.setEditable(false);
		
		playerTurn.setBorder(border);
		
		add(label);
		add(playerTurn);
	}
}
