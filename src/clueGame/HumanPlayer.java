package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player {
	public HumanPlayer() {
		super();
	}

	public HumanPlayer(String name, int row, int col, Color color) {
		super(name,row,col,color);
	}

	@Override
	public void move(Board board) {
		notFinished = true;
		
		board.highlightSquare(true);
		
	}
	
	
	public boolean getFinStatus() {
		return notFinished;
	}
	
	@Override
	public void finishTurn(BoardCell clicked) {
		setRow(clicked.row);
		setColumn(clicked.column);
	}
}
