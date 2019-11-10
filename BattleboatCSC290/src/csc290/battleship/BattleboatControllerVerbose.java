package csc290.battleship;

public abstract class BattleboatControllerVerbose extends BattleboatController{

	protected void reportMove(char whosTurn, Move move) {
		System.out.println(whosTurn + " makes move " + move + "\n");
	}

	protected void report() {
		
		String s = battleboat.getBoardString(Battleboat.P1) + battleboat.getBoardString(Battleboat.P2) + Battleboat.P1 + ":" 
				+ battleboat.getLifeCount(Battleboat.P1) + " "
				+ Battleboat.P2 + ":" + battleboat.getLifeCount(Battleboat.P2) + "  " 
				+ battleboat.getWhosTurn() + " moves next";
		System.out.println(s);
	}

	protected void reportFinal() {
		
		String s = battleboat.getBoardString(Battleboat.P1) + battleboat.getBoardString(Battleboat.P2) + Battleboat.P1 + ":" 
				+ battleboat.getLifeCount(Battleboat.P1) + " "
				+ Battleboat.P2 + ":" + battleboat.getLifeCount(Battleboat.P2) 
				+ "  " + battleboat.getWinner() + " won\n";
		System.out.println(s);
	}
	

}