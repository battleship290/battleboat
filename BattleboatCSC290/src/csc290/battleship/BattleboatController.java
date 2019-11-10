package csc290.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BattleboatController {
	
	protected Battleboat battleboat;
	Player player1, player2, player3, player4;
	BattleboatBoard board1, board2, board3, board4;
	
	public BattleboatController() {
		this.battleboat = new Battleboat();
		this.player1 = battleboat.getPlayer1();
		this.player2 = battleboat.getPlayer2();
		this.player3 = battleboat.getPlayer3();
		this.player4 = battleboat.getPlayer4();
		this.board1 = battleboat.getBoard(player1);
		this.board2 = battleboat.getBoard(player2);
		this.board3 = battleboat.getBoard(player3);
		this.board4 = battleboat.getBoard(player4);
		
	}
	
	public void setup() {
		while (!battleboat.isGameStarted()) {
			this.report();

			HashMap<String, ArrayList<String>> setOfBoats;
			
			while (!player1.isSetUp()) {
				setOfBoats = (HashMap<String, ArrayList<String>>) player1.getSetOfBoats();
				for (Map.Entry<String, ArrayList<String>> element : setOfBoats.entrySet()) {
					this.setupHelper(element, player1);
				}
			}
			while (!player2.isSetUp()) {
				setOfBoats = (HashMap<String, ArrayList<String>>) player2.getSetOfBoats();
				for (Map.Entry<String, ArrayList<String>> element : setOfBoats.entrySet()) {
					this.setupHelper(element, player2);
				}
			}
		}
	}
	
	private void setupHelper(Map.Entry<String, ArrayList<String>> element, Player player) {
		String type = element.getKey();
		ArrayList<String> value = element.getValue();
		int numOfBoats = Integer.parseInt(value.get(1));
		
		BattleboatBoard board = this.battleboat.getBoard(player);
		
		
		for (int count = 1; count <= numOfBoats; count++) {
			Coord start = player.getCoord();
			Coord end = player.getCoord();
			while (!board.validCoordinates(start, end)) {
				start = player.getCoord();
				end = player.getCoord();
			}
			player.addBoat(type, start, end);
			board.placeBoat(start, end);
			this.report();
		}
	}
	
	public void play() {
		while (!battleboat.isGameOver()) {
			this.report();
			
			Move move=null;
			char whosTurn = battleboat.getWhosTurn();
			
			if(whosTurn==Battleboat.P1)move = player1.getMove();
			if(whosTurn==Battleboat.P2)move = player2.getMove();

			this.reportMove(whosTurn, move);
			battleboat.move(move.getRow(), move.getCol(), move.getMissileIndex());
		}
		this.reportFinal();
	}
	protected void reportMove(char whosTurn, Coord move) { }
	protected void report() { }
	protected void reportFinal() { }
}
