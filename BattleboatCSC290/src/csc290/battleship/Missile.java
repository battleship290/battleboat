package csc290.battleship;

public class Missile {
	private String type;
	private int quantity;
	
	public Missile() {
		// this is a regular missile
		this.type = "regular";
		this.quantity = -1;
	}
	
	public Missile(String type, int quantity) {
		this.type = type;
		this.quantity = quantity;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	

}
