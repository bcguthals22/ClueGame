/*
 * Brennan Guthals & Nicholas Wenzel
 * 3/6/2019
 * ClueGame
 */
package clueGame;

import java.awt.Color;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import java.util.*;



public class Board {
	private static final int MAX_BOARD_SIZE = 100; 
	private int numRows;
	private int numColumns; 

	public BoardCell[][] board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE]; 

	public HashMap<Character, String> legend = new HashMap<Character, String>();

	public HashMap<BoardCell, Set<BoardCell>> adjMatrix;

	public Set<BoardCell> targets; 

	public Set<BoardCell> visited;
	
	public ArrayList<Card> deck = new ArrayList<Card>();
		
	public ArrayList<Player> players = new ArrayList<Player>();

	public String boardConfigFile;

	public String roomConfigFile;
	
	private static Solution answer;

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();

	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void resetVariables() {
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
	}
	public void initialize() {
		try {
			loadRoomConfig();

			loadBoardConfig();
			
			loadPeopleConfig("Player1.txt");
			
			loadPeopleConfig("Player2.txt"); 
			
			loadPeopleConfig("Player3.txt");
			
			loadPeopleConfig("Player4.txt");
			
			loadPeopleConfig("Player5.txt");
			
			loadPeopleConfig("Player6.txt");
			
			loadWeaponConfig();
			


		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (BadConfigFormatException e) {
			e.getMessage();		}

		calcAdjacencies(); 
	}

	/*
	 * Function for returning the legend of the board given the roomConfigFile(Legend)
	 * File reader reads in the file stopping at commas and new lines, and puts the 
	 * Character and String from the legend file into the legend map. 
	 */

	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException {

		FileReader reader = new FileReader(roomConfigFile);
		Scanner in = new Scanner(reader); 
		in.useDelimiter(",|\\n");
		while(in.hasNextLine()) {
			String character = in.next();
			String roomName = in.next();
			String card = in.next();
			card = card.substring(1); 

			if((!card.contains("Card") && (!card.contains("Other")))) {
				throw new BadConfigFormatException("Not a valid type: " + card);
			}
			
			roomName = roomName.substring(1); 
			
			if(card.contains("Card")) {
				Card newCard = new Card(roomName, CardType.ROOM);
				deck.add(newCard);
			}
			char c = character.charAt(0); 

			legend.put(c, roomName);
		}

	}

	/*
	 * Function for loading in the Board Configuration File 
	 * Reads in the file and splits it into an array at commas
	 * Sets the number of cols based on size of row.
	 * Iterates through until there is no next line in the file 
	 * Makes a board cell of the current col and row and puts it into the board
	 * Updates the number of rows in the board
	 */

	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(boardConfigFile);
		Scanner in = new Scanner(reader); 

		int rows = 0;

		while(in.hasNextLine()) {
			String line = in.nextLine();
			String[] row = line.split(",");
			if(rows == 0) {
				numColumns = row.length; 
			}
			else if(numColumns != row.length) {
				throw new BadConfigFormatException("Rows do not have the same number of columns.") ;
			}

			for(int i = 0; i < numColumns; i++) {
				String cha = row[i];
				Character c = cha.charAt(0);

				String room = legend.get(c);

				if(room == null) {
					throw new BadConfigFormatException("Room type not defined " + cha);
				}

				board[rows][i] = new BoardCell(rows, i, cha); 
			}

			rows = rows + 1; 

		}

		numRows = rows; 

	}
	
	/*
	 * Functions for loading the files for the people in the game 
	 */
	public void loadPeopleConfig(String player) throws FileNotFoundException {
		FileReader reader = new FileReader(player);
		Scanner in = new Scanner(reader);
		
		String PlayerName;
		
		PlayerName = in.nextLine();
		
		String startingLoc = in.nextLine(); 
		
		String[] line = startingLoc.split(" ");
		
		
		
		String playerColor = in.nextLine();
		
		Color play_Color = convertColor(playerColor);
		
		String pType = in.nextLine();
		
		if(pType.contains("Human")) {
			HumanPlayer human = new HumanPlayer();
			
			human.setPlayerName(PlayerName);
			human.setColor(play_Color);
			human.setRow(Integer.parseInt(line[0]));
			human.setColumn(Integer.parseInt(line[1]));
			human.setType(PlayerType.HUMAN);
		
			players.add(human);
		}
		
		else {
			ComputerPlayer comp = new ComputerPlayer();
			comp.setPlayerName(PlayerName);
			comp.setColor(play_Color);
			comp.setRow(Integer.parseInt(line[0]));
			comp.setColumn(Integer.parseInt(line[1]));
			comp.setType(PlayerType.COMPUTER);
			
			players.add(comp); 
			
		}
		
		Card card = new Card(PlayerName, CardType.PERSON);
		
		deck.add(card);
		
		
	}
	
	
	/*
	 * Functions for loading the weapons config files 
	 */
	public void loadWeaponConfig() throws FileNotFoundException {
		FileReader reader = new FileReader("Weapons.txt"); 
		Scanner in = new Scanner(reader); 
		
		while(in.hasNextLine()) {
			String weapon = in.nextLine();
			
			Card card = new Card(weapon, CardType.WEAPON);
			
			deck.add(card); 
		}
	}

	/*
	 * Function for taking in two file names and assinging them to the board Configuration file and the
	 * room configuration file. 
	 */
	public void setConfigFiles(String board, String room) {
		boardConfigFile = board;

		roomConfigFile = room;

	}

	/*
	 * Returns the legend
	 */
	public Map<Character, String> getLegend() {
		return legend;

	}


	/*
	 * Returns number of rows in board
	 */
	public int getNumRows() {
		return numRows;
	}

	/*
	 * Returns number of cols in board
	 */
	public int getNumColumns() {
		return numColumns;
	}
	/*
	 * Returns the cell given a row and col 
	 */
	public BoardCell getCellAt(int row, int col) {
		return board[row][col]; 
	}

	public void calcTargets(int i, int j, int numSteps) {
		BoardCell startCell = getCellAt(i,j);
		targets = new HashSet();
		visited = new HashSet();
		visited.add(startCell);

		findTargets(i, j, numSteps);
	}
	
	/*
	 * Function for finding the targets given the row column and the number of steps 
	 */

	public void findTargets(int i, int j, int numSteps) {
		BoardCell startCell = getCellAt(i,j);		
		Set<BoardCell> list = (Set)adjMatrix.get(startCell);
		for (BoardCell adjCell : list)
		{
			if (!visited.contains(adjCell))
			{
				visited.add(adjCell);

				if (adjCell.isDoorway()) {
					targets.add(adjCell);
				}
				else if (numSteps == 1) {
					targets.add(adjCell);
				}
				else {
					findTargets(adjCell.row,adjCell.column, numSteps - 1);

				}
				visited.remove(adjCell);
			}
		}
	}

	/*
	 * Returns the targets set of Board Cells
	 */
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	
	/*
	 *Calculates the adjacencies of the given cell 
	 */
	public void calcAdjacencies() {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns;  col++) {
				addAdj(row,col);
			}
		}
	}
	
	/*
	 * Adds the adjacencies into the adj matrix 
	 */

	public void addAdj(int row, int col) {
		Set<BoardCell> adj = new HashSet();
		BoardCell cell = getCellAt(row,col);

		if(cell.isWalkway()) {
			//Check if door is facing the right direction
			caseWalkway(row-1,col,adj,DoorDirection.DOWN);
			caseWalkway(row+1,col,adj,DoorDirection.UP);
			caseWalkway(row,col-1,adj,DoorDirection.RIGHT);
			caseWalkway(row,col+1,adj,DoorDirection.LEFT);
		}
		else if(cell.isDoorway()) {
			DoorDirection direction = cell.getDoorDirection();
			caseDoor(row,col,adj,direction);
		}

		adjMatrix.put(cell, adj);
	}
	/*
	 * Function for walkways 
	 */

	public void caseWalkway(int row, int col, Set<BoardCell> nextTo, DoorDirection direction) {
		if ((row < 0) || (col < 0) || (row >= numRows) || (col >= numColumns)) {
			return;
		}
		BoardCell cell = board[row][col];

		if (cell.isWalkway()) {
			nextTo.add(cell);
		}
		else if (cell.isDoorway())
		{
			DoorDirection dir = cell.getDoorDirection();
			if (dir == direction) {
				nextTo.add(cell);
			}
		}
	}
	
	/*
	 * Case for doorways
	 */

	public void caseDoor(int row, int col, Set<BoardCell> nextTo, DoorDirection direction) {
		if ((direction == DoorDirection.DOWN) && (row + 1 < numRows) && (board[(row + 1)][col].isWalkway())) {
			nextTo.add(board[(row + 1)][col]);
		}
		else if ((direction == DoorDirection.UP) && (row - 1 >= 0) && (board[(row - 1)][col].isWalkway())) {
			nextTo.add(board[(row - 1)][col]);
		}
		else if ((direction == DoorDirection.LEFT) && (col - 1 >= 0) && (board[row][(col - 1)].isWalkway())) {
			nextTo.add(board[row][(col - 1)]);
		}
		else if ((direction == DoorDirection.RIGHT) && (col + 1 < numColumns) && (board[row][(col + 1)].isWalkway())) {
			nextTo.add(board[row][(col + 1)]);
		}
	}
	
	/*
	 * Returns the adjacenies list 
	 */
	public Set<BoardCell> getAdjList(int i, int j) {
		Set<BoardCell> list = new HashSet();
		BoardCell cell = getCellAt(i,j);
		list = adjMatrix.get(cell);
		return list;
	}
	
	//Function to allow for testing
	public void setAnswer(Solution solution) {
		answer.person = solution.person;
		answer.room = solution.room;
		answer.weapon = solution.weapon;
	}
	
	public void selectAnswer() {
		
	}
	
	public Card handleSuggestion() {
		return null;
	}
	
	public boolean checkAccusation(Solution accusation) {
		return false; 
	}
	/*
	 * Function for dealing the cards to each player
	 * Random selectiion of cards and goes to each player sequentially. 
	 */
	
	public void dealCards() {
		ArrayList<Card> vist = new ArrayList<Card>();
		int playerCount = 0;
		while(vist.size() != 23) {
			int num = new Random().nextInt(deck.size());
			Card card = deck.get(num);
			
			if (vist.contains(card)) {
				continue;
			}
			
			else {
				
				Player player = players.get(playerCount);
				
				player.hand.add(card);
				
				vist.add(card);
				
				players.set(playerCount, player);
				
				playerCount++;
				
				if(playerCount == 6) {
					playerCount = 0;
				}
				
			}
		}
		
		
		
		
	}
	
	
	/*
	 * Function for converting a string to a color
	 */
	public Color convertColor(String strColor) {
		Color color;
		
		try {
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());
			color = (Color)field.get(null);
			
		} catch (Exception e) {
			color = null;
		}
		return color;
	}


}

