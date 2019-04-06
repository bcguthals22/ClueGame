/*
 * Authors: Nicholas Wenzel and Brennen Guthals
 */

package clueGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame{
	private Board board;
	
	private DetectiveNotes detNotes = new DetectiveNotes();
	
	public ClueGame() {
		setup();
		
		
	}

	private void setup() {
		board = Board.getInstance();
		board.setConfigFiles("ClueMap.csv", "ClueMapLegend.txt");		
		board.initialize();
		
		GUI();
		
	}

	private void GUI() {
		setTitle("Clue Game!");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1000,1000);
		
		add(this.board);
		
		makeMenu();
		
	}
	
	private void makeMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuBar.add(createFileMenu());
		
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(makeDectNotes());
		menu.add(makeFileExit());
		return menu;
	}

	private JMenuItem makeDectNotes() {
		JMenuItem dect = new JMenuItem("Show Detective Notes");

		dect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClueGame.this.detNotes.setVisible(true);
			}
		});
		return dect;
	}

		private JMenuItem makeFileExit() {
			JMenuItem exit = new JMenuItem("Exit");

			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			return exit;
		}

		public static void main(String[] args) {
			ClueGame clueGame = new ClueGame();

			clueGame.setVisible(true);
		}
	}
