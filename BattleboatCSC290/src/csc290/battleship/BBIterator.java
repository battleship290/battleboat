package csc290.battleship;

import java.util.Iterator;


public class BBIterator implements Iterator<Coord> {

	
private int row, col;
	
	public BBIterator() { 
		this.row = 0;
		this.col = -1;
	}
	
	@Override
	public boolean hasNext() {
		if (this.row == Battleboat.DIMENSION - 1 && this.col == Battleboat.DIMENSION - 1)
			return false;
		return true;
	}

	@Override
	public Coord next() {
		this.col++;
		if (this.col == Battleboat.DIMENSION) {
			this.row++;
			this.col = 0;
		}
		return new Coord(this.row, this.col);
	}

}
