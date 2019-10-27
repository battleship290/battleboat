package csc290.battleship;

public class Coord {
private int row, col;
	
	/**
	 * Create Move object.
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 */
	public Coord(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Get the row value.
	 * 
	 * @return row value
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get the col value
	 * 
	 * @return col value
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return a string representation of this
	 */
	public String toString() {
		String rowString = BattleboatBoard.intToAlpha(this.row);
		return rowString + this.col;
	}
}
