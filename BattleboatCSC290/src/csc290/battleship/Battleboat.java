package csc290.battleship;

import java.util.Random;

public class Battleboat {
	
	public static final char P1 = '1', P2 = '2', P3 = '3', P4 = '4';
	private int numberOfPlayers = 2;
	public static final int DIMENSION = 10; // default size
	private char whosTurn = P1; // P1 moves first!
	
	private BattleboatBoard board1 = new BattleboatBoard(DIMENSION);
	private BattleboatBoard board2 = new BattleboatBoard(DIMENSION);
	private BattleboatBoard board3 = new BattleboatBoard(DIMENSION);
	private BattleboatBoard board4 = new BattleboatBoard(DIMENSION);
	

	public boolean setNumPlayers(int num) {
		if (num % 2 == 0 && num > 0) {
			this.numberOfPlayers = num;
			return true;
		}
		return false;
	}
	
	public char getWhosTurn() {
		return this.whosTurn;
	}


	public boolean move(int row, int col) {
		if (this.numberOfPlayers == 2) {
			
		}
	}


	public int getLifeCount(char player) {
		if (player == P1) return this.board1.getHealthyBoatCount();
		else if (player == P2) return this.board2.getHealthyBoatCount();
		else if (player == P3) return this.board3.getHealthyBoatCount();
		else if (player == P4) return this.board4.getHealthyBoatCount();
		else return 0;
	}


	public char getWinner() {
		int countP1 = this.getLifeCount(P1);
		int countP2 = this.getLifeCount(P2);
		int countP3 = this.getLifeCount(P3);
		int countP4 = this.getLifeCount(P4);
		if (this.isGameOver(countP1, countP2, countP3, countP4)) {
			if (countP2 == 0 && countP4 == 0) return P1;
			else if (countP1 == 0 && countP3 == 0) return P2;
			else return BattleboatBoard.EMPTY;
		}
		return BattleboatBoard.EMPTY;
	}

	public boolean isGameOver(int p1Life, int p2Life, int p3Life, int p4Life) {
		if (this.numberOfPlayers == 2 && (p1Life == 0 || p2Life == 0)) {
			return true;
		}
		else if (this.numberOfPlayers == 4 && ((p1Life == 0 && p3Life == 0) || (p2Life == 0 && p4Life == 0))) {
			return true;
		}
		else return false;
	}

	/**
	 * 
	 * @return a string representation of the board.
	 */
	public String getBoardString(char player) {
		if (player == P1) return this.board1.toString();
		else if (player == P2) return this.board2.toString();
		else if (player == P3) return this.board3.toString();
		else if (player == P4) return this.board4.toString();
		else return "";
		
	}

	/**
	 * run this to test the current class. We play a completely random game. DO NOT
	 * MODIFY THIS!! See the assignment page for sample outputs from this.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	
	//MY METHODS @author zhaodav7
	public BattleboatBoard getBoard(char player) {
		if (player == P1) return this.board1;
		else if (player == P2) return this.board2;
		else if (player == P3) return this.board3;
		else if (player == P4) return this.board4;
		else return null;
	}
	
}

