package csc290.battleship;

import BattleBoatView.util.Observable;

public class Battleboat extends Observable{
	
	public static final char P1 = '1', P2 = '2', P3 = '3', P4 = '4';
	private int numberOfPlayers = 2;
	public static final int DIMENSION = 10; // default size
	private char whosTurn = P1; // P1 moves first!
	
	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public Player getPlayer3() {
		return player3;
	}
	public Player getPlayer4() {
		return player4;
	}

	private Player player1 = new Player(P1);
	private Player player2 = new Player(P2);
	private Player player3 = new Player(P3);
	private Player player4 = new Player(P4);
	
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
	
	public void updateTurn() {
		if (this.getWinner() != BattleboatBoard.EMPTY) {
			this.whosTurn = BattleboatBoard.EMPTY;
		}
		else if (this.numberOfPlayers == 2) {
			if (this.getWhosTurn() == P1) {
				this.whosTurn = P2;
			}
			else this.whosTurn = P1;
		}
		else if (this.numberOfPlayers == 4) {
			if (this.getWhosTurn() == P1) {
				this.whosTurn = P2;
			}
			else if (this.getWhosTurn() == P2) {
				this.whosTurn = P3;
			}
			else if (this.getWhosTurn() == P3) {
				this.whosTurn = P4;
			}
			else this.whosTurn = P1;
		}
	}

	public int getLifeCount(char player) {
		if (player == P1) return this.board1.getHealthyBoatCount();
		else if (player == P2) return this.board2.getHealthyBoatCount();
		else if (player == P3) return this.board3.getHealthyBoatCount();
		else if (player == P4) return this.board4.getHealthyBoatCount();
		else return 0;
	}

	/**
	 * Returns winner of game iff the game is over
	 * 
	 * @return P1 or P2 can be used to link to 
	 * Team1 and Team2 once 2v2 is implemented
	 * EMPTY if there is no winner or game is not over
	 */
	public char getWinner() {
		// count of number of un-hit BOAT coordinates left for each player
		int countP1 = this.getLifeCount(P1); 
		int countP2 = this.getLifeCount(P2);
		int countP3 = this.getLifeCount(P3); // will be 0 by default in 1v1
		int countP4 = this.getLifeCount(P4); // will be 0 by default in 1v1
		
		if (this.isGameOver()) { //check if game is over
			if (countP2 == 0 && countP4 == 0) return P1; 
			else if (countP1 == 0 && countP3 == 0) return P2;
			else return BattleboatBoard.EMPTY; // should not happen, this is a fail safe
		}
		return BattleboatBoard.EMPTY; // game is likely not over
	}
	
	/**
	 * Returns the token on the player's board at (row, column)
	 * 
	 * @param player
	 * @param row
	 * @param col
	 * @return
	 */
	public char getToken(char player, int row, int col) {
		if (player == P1) {
			return this.board1.get(row, col);
		}
		else if (player == P2) {
			return this.board2.get(row, col);
		}
		else if (player == P3) {
			return this.board3.get(row, col);
		}
		else if (player == P4) {
			return this.board4.get(row, col);
		}
		return BattleboatBoard.MISS;
	}
	
	public Player getPlayer(char player) {
		if (player == P1) {
			return player1;
		}
		else if (player == P2) {
			return player2;
		}
		else if (player == P3) {
			return player3;
		}
		else if (player == P4) {
			return player4;
		}
		else return null;
	}
	
	public BattleboatBoard getBoard(Player player) {
		if (player == player1) {
			return board1;
		}
		else if (player == player2) {
			return board2;
		}
		else if (player == player3) {
			return board3;
		}
		else if (player == player4) {
			return board4;
		}
		else return null;
	}
	
	public boolean placeBoat(BattleboatBoard board, Coord start, Coord end, int fixedLength) {
		return board.placeBoat(start, end, fixedLength);
	}
	
	/**
	 * Helper function to try and make legitimate move
	 * 
	 * @param targetPlayer
	 * @param row
	 * @param col
	 * @param missile
	 * @return
	 */
	public boolean moveHelper(Player targetPlayer, int row, int col, Missile missile) {

		BattleboatBoard targetBoard = this.getBoard(targetPlayer);
		// check if move is valid
		if (targetBoard.move(row, col, missile)) {
			// use the missile
			missile.use();
			// check if there was a hit
			if (targetBoard.get(row, col) == BattleboatBoard.HIT) {
				targetPlayer.updateBoats(row, col);
			}
			this.updateTurn();
			this.notifyObservers();
			return true;
		}
		return false;
	}
	
	/**
	 * Primitive move method for 2 players, without target selection
	 * 
	 * @param row
	 * @param col
	 * @param m
	 * @return
	 */
	public boolean move(int row, int col, int index) {
		this.notifyObservers();
		char currentPlayer = this.getWhosTurn();
		Missile missile = this.getPlayer(currentPlayer).getMissile(index);
		if (!missile.canUse()) return false;
		
		Player targetPlayer;
		
		if (this.numberOfPlayers == 2) {
			if (currentPlayer == P1) {
				targetPlayer = player2;
			}
			else {
				targetPlayer = player1;
			}
			return this.moveHelper(targetPlayer, row, col, missile);
		}
		return false;
	}
	
	/**
	 * move selection with targeting option
	 * 
	 * @param target
	 * @param row
	 * @param col
	 * @param m
	 * @return
	 */
	public boolean move(char target, int row, int col, int index) {
		char currentPlayer = this.getWhosTurn();
		Missile missile = this.getPlayer(currentPlayer).getMissile(index);
		
		Player targetPlayer = this.getPlayer(target);
		BattleboatBoard targetBoard = this.getBoard(targetPlayer);
		
		if (this.numberOfPlayers == 4) {
			
		}
		return false;
	}

	public boolean isGameStarted() {
		boolean stateOf2 = (player1.isSetUp() && player2.isSetUp());
		boolean stateOf4 = true;
		if (this.numberOfPlayers == 4) {
			stateOf4 = (player3.isSetUp() && player4.isSetUp());
		}
		return (stateOf2 && stateOf4);
	}
	
	public boolean isGameOver() {
		int p1Life = this.getLifeCount(P1); 
		int p2Life = this.getLifeCount(P2); 
		int p3Life = this.getLifeCount(P3); 
		int p4Life = this.getLifeCount(P4); 
		if (this.numberOfPlayers == 2 && (p1Life == 0 || p2Life == 0)) {
			return true;
		}
		else if (this.numberOfPlayers == 4 && 
				((p1Life == 0 && p3Life == 0) || (p2Life == 0 && p4Life == 0))) {
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

