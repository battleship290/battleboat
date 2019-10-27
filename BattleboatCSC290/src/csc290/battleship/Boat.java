package csc290.battleship;

import java.util.ArrayList;

public class Boat {
	private ArrayList<Coord> coordinates;
	private ArrayList<Coord> wounds = new ArrayList<Coord>();
	private boolean isSunk = false;
	private int size;
	private int health;
	private String type;
	
	public Boat(ArrayList<Coord> coord, String type) {
		this.coordinates = coord;
		this.size = this.coordinates.size();
		this.health = this.coordinates.size();
		this.type = type;
	}
	
	private boolean checkSunk() {
		if (this.health <= 0) {
			this.isSunk = true;
		}
		return this.isSunk;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public String getType() {
		return this.type;
	}
	
	public boolean getSunkValue() {
		return this.isSunk;
	}
	

	
}
