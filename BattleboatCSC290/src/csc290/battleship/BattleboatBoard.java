package csc290.battleship;

import java.io.IOException;

// BTL == bellow this line

public class BattleboatBoard {

	public static final char EMPTY = ' ', BOAT = 'B', HIT = 'X', MISS = 'O';
	private int dim = 10;
	private char[][] board;
	
	
	/**
	 * Constructor default
	 */
	public BattleboatBoard() {
		board = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				this.board[row][col] = EMPTY;
			}
		}
	}
	
	/**
	 * Constructor with variable dimension
	 * @param dim
	 */
	public BattleboatBoard(int dim) {
		this.dim = dim;
		board = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				this.board[row][col] = EMPTY;
			}
		}
	}
	
	/**
	 * return if the coordinate is valid
	 * @param row
	 * @param col
	 * @return boolean
	 */
	private boolean validCoordinate(int row, int col) {
		if (row < 0 || col < 0 || row > dim-1 || col > dim-1) return false;
		return true;
	}
	
	public boolean validCoordinates(Coord start, Coord end) {
		boolean startValid = this.validCoordinate(start.getRow(), start.getCol());
		boolean endValid = this.validCoordinate(end.getRow(), end.getCol());
		return startValid && endValid;
		
	}
	
	/**
	 * Counts the number of live Boat tiles left
	 * @return
	 */
	public int getHealthyBoatCount() {
		int count = 0;
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				if (this.board[row][col] == BOAT) count++; 
			}
		}
		return count;
	}
	
	/**
	 * check if the object at row,col is a boat
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean isBoat(int row, int col) {
		if (!this.validCoordinate(row, col)) return false;
		if (this.board[row][col] == BOAT || this.board[row][col] == HIT) return true;
		else return false;
	}

	/**
	 * check if the row,col coordinate has a move
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean hasMove(int row, int col) {
		if (!this.validCoordinate(row, col)) return false;
		if (this.board[row][col] == HIT || this.board[row][col] == MISS) return false;
		else return true;
	}
	
	/**
	 * try to make a move at the row,col coordinate with the missile m, and return true if the move is made.
	 * 
	 * @param row
	 * @param col
	 * @param m
	 * @return boolean
	 */
	public boolean move(int row, int col, Missile missile) {
		if (missile.getType() == "regular" && this.hasMove(row, col)) {
			if (this.board[row][col] == BOAT) {
				this.board[row][col] = HIT;
				// update other possible conditions BTL
				return true;
			}
			else if (this.board[row][col] == EMPTY) {
				this.board[row][col] = MISS;
				// update other possible conditions BTL 
				return true;
			}
			// this case should technically never happen
			else return false;
		}
		
		// *****if else statements for missiles of other types BTL*****
		
		else {
			return false;
		}
	}

	/**
	 * check if the the boat with length of length can be place at the coordinate row_start,col_start, return true if can be placed
	 * @param row_start
	 * @param col_start
	 * @param drow
	 * @param dcol
	 * @param length
	 * @return boolean
	 */
	private boolean canPlaceBoat(int row_start, int col_start, int drow, int dcol, int length) {
		int count = 1;
		int row = row_start, col = col_start;
		while (count <= length) {
			if (isBoat(row, col)) {
				return false;
			}
			row += drow;
			col += dcol;
			count ++;
		}
		return true;
	}
	
	public boolean placeBoat(Coord start, Coord end) {
		return this.placeBoat(start.getRow(), start.getCol(), end.getRow(), end.getCol());
	}
	
	/**
	 * place the boat at coordinate row_start,col_start, return true if the boat is placed 
	 * @param row_start
	 * @param col_start
	 * @param row_end
	 * @param col_end
	 * @return boolean
	 */
	public boolean placeBoat(int row_start, int col_start, int row_end, int col_end) {
		int row = row_start; 
		int col = col_start;
		int drow = (row_end - row_start), dcol = (col_end - col_start), length;
		
		if (drow != 0) {
			length = java.lang.Math.abs(drow) + 1;
		}
		else if (dcol != 0) {
			length = java.lang.Math.abs(dcol) + 1;
		}
		else {
			length = 1;
		}
		
		if (length > 1) {
			drow = drow/(length - 1);
			dcol = dcol/(length - 1);
		}
		
		System.out.println(row + " "+ col + " "+ drow + " "+ dcol + " "+ length);
		
		
		if (canPlaceBoat(row_start, col_start, drow, dcol, length)) {
			for (int count = 1; count <= length; count ++) {
				this.set(row, col, BOAT);
				row += drow;
				col += dcol;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Getter method for board
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public char get(int row, int col) {
		if (this.validCoordinate(row, col)) return this.board[row][col];
		return EMPTY;
	}
	
	/**
	 * Setter method for board
	 * 
	 * @param row
	 * @param col
	 * @param letter
	 */
	public void set(int row, int col, char letter) {
		this.board[row][col] = letter;
	}
	
	/**
	 * Integer to Alphabet converting helper method, designed for integer of any size.
	 * 
	 * @param i
	 * @return
	 */
	public static String intToAlpha(int i) {
	    if(i < 0) {
	        return "-" + intToAlpha(-i-1);
	    }

	    int quotient = i/26;
	    int remainder = i%26;
	    char letter = (char)((int)'A' + remainder);
	    if(quotient == 0) {
	        return "" + letter;
	    } 
	    else {
	        return intToAlpha(quotient - 1) + letter;
	    }
	}
	
	/**
	 * @return a string representation of this, just the play area, with no
	 *         additional information. DO NOT MODIFY THIS!!
	 */
	public String toString() {
		/**
		 * See assignment web page for sample output.
		 */
		String s = "";
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			if (col < 10) {
				s += " " + col + " ";
			}
			else s += col + " ";
		}
		s += '\n';

		s += " +";
		for (int col = 0; col < this.dim; col++) {
			s += "--+";
		}
		s += '\n';

		//change row
		for (int row = 0; row < this.dim; row++) {
			
			String rowString = BattleboatBoard.intToAlpha(row);
			
			s += rowString + "|";
			for (int col = 0; col < this.dim; col++) {
				s += this.board[row][col] + " |";
			}
			s += rowString + "\n";

			s += " +";
			for (int col = 0; col < this.dim; col++) {
				s += "--+";
			}
			s += '\n';
		}
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			if (col < 10) {
				s += " " + col + " ";
			}
			else s += col + " ";
		}
		s += '\n';
		return s;
	}
	
	
	
	
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		BattleboatBoard board = new BattleboatBoard(10);
		System.out.println(board.toString());
//		BattleboatBoard bigBoard = new BattleboatBoard(26);
//		System.out.println(bigBoard.toString());
		
		System.out.println(board.placeBoat(4,3,7,3));
		System.out.println(board.board[4][3]);
		System.out.println(board.get(4, 3));
		board.set(0, 0, BOAT);
		System.out.println(board.toString());
		System.out.println(board.canPlaceBoat(0,0,1,0,5));
		System.out.println(board.canPlaceBoat(4,2,0,1,0));
		System.out.println(board.canPlaceBoat(4,2,0,1,5));
		
//		System.out.println(bigBoard.placeBoat(4,3,7,3));
//		System.out.println(bigBoard.toString());
//		System.out.println(bigBoard.canPlaceBoat(3,3,1,0,5));
		
	}

}