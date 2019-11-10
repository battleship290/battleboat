package csc290.battleship;

public class Move extends Coord{
	private int missileIndex;
	
	public Move(int row, int col, int index) {
		super(row, col);
		this.missileIndex = index;
	}
	
	/**
	 * Get the index value
	 * 
	 * @return index value
	 */
	public int getMissileIndex() {
		return missileIndex;
	}
	
	public String toString() {
		return super.toString();
	}
}
