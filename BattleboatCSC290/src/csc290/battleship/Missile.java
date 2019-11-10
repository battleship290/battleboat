package csc290.battleship;

public class Missile {
	private String type;
	private int quantity;
	
	/**
	 * Constructs an instance of a default missile
	 */
	
	public Missile() {
		// this is a regular missile
		this.type = "regular";
		this.quantity = -1;
	}
	
	/**
	 * Constructs a non-default missile 
	 * @param type The type of the missile
	 * @param quantity The quantity of the missile
	 */
	
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
	
	public boolean canUse() {
		if (this.quantity == 0) {
			return false;
		}
		else return true;
	}
	/**
	 * 
	 * @return Whether a player uses a missile. 
	 */
	public boolean use() {
		if (this.quantity < 0) {
			return true;
		}
		else if (this.quantity > 0) {
			this.quantity --;
			return true;
		}
		else return false;
		
	}
}
