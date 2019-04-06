/*
 * Authors: Nicholas Wenzel and Brennen Guthals
 */

package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DieRoll extends JPanel{
	private JTextField rollNum;
	
	public DieRoll() {
		JLabel label = new JLabel("Roll");
		
		rollNum = new JTextField(5);
		
		rollNum.setEditable(false);
		
		setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		
		add(rollNum);
	}

}
