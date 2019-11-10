package csc290.battleship;

public class BattleboatControllerHumanVSHuman extends BattleboatControllerVerbose{
	
	public BattleboatControllerHumanVSHuman() {
		super();
		this.player1 = this.battleboat.getPlayer1();
		this.player2 = this.battleboat.getPlayer2();
	}

	/**
	 * Run main to play two Humans against each other at the console.
	 * @param args
	 */
	public static void main(String[] args) {
		BattleboatControllerHumanVSHuman oc = new BattleboatControllerHumanVSHuman();
		oc.setup();
		oc.play();
	}
}