package csc290.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Player {
	
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter within range";
	private static final String IO_ERROR_MESSAGE = "I/O Error";
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	
	private char player;
	private ArrayList<Boat> boats;
	private ArrayList<Missile> missiles;
	private int dimensionReference = 10;
	private int numOfBoats;
	
	protected Map<String, ArrayList<String>> setOfBoats; //shows what kind of boats this player can have
	
	// default Constructor
	public Player(char player) {
		this.player = player;
		this.numOfBoats = 0;
		setUpBoats();
		this.boats = new ArrayList<Boat>(); // need to fill by method
		this.missiles = new ArrayList<Missile>();
		this.missiles.add(new Missile());
		this.dimensionReference = 10;
	}
	
	public ArrayList<Missile> getMissiles() {
		return this.missiles;
	}
	
	public Missile getMissile(int index) {
		if (missiles.size() <= index) return this.missiles.get(0);
		else return this.missiles.get(index);
	}
	
	public ArrayList<Boat> getBoats() {
		return this.boats;
	}
	
	public Map<String, ArrayList<String>> getSetOfBoats() {
		return this.setOfBoats;
	}
	
	/**
	 * Check if all boats are placed
	 * 
	 * @return
	 */
	public boolean isSetUp() {
		if (this.getBoats().size() == this.numOfBoats--) return true;
		else return false;
	}
	
	//default BOATs set
	public String setUpBoats() {
		this.setOfBoats = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> regular2 = new ArrayList<String>();
		ArrayList<String> regular3 = new ArrayList<String>();
		ArrayList<String> regular4 = new ArrayList<String>();
		ArrayList<String> regular5 = new ArrayList<String>();
		
		this.setOfBoats.putIfAbsent("regular_2", regular2);
		regular2.add("2");
		regular2.add("1");
		this.setOfBoats.putIfAbsent("regular_3", regular3);
		regular3.add("3");
		regular3.add("2");
		this.setOfBoats.putIfAbsent("regular_4", regular4);
		regular4.add("4");
		regular4.add("1");
		this.setOfBoats.putIfAbsent("regular_5", regular5);
		regular5.add("5");
		regular5.add("1");
		
		for (Map.Entry<String, ArrayList<String>> element : this.setOfBoats.entrySet()) {
			this.numOfBoats += Integer.parseInt(element.getValue().get(1));
		}
		
		return "default";
	}
	
	private static int lengthHelper(Coord start, Coord end) {
		int drow, dcol;
		drow = end.getRow() - start.getRow() + 1;
		dcol = end.getCol() - start.getCol() + 1;
		
		if (drow != 0) return Math.abs(drow);
		else if (dcol != 0) return Math.abs(dcol);
		else return 0;
	}
	
	/**
	 * Add a boat to boats list
	 * 
	 * @param type
	 * @param coord
	 * @param length
	 * @param drow
	 * @param dcol
	 */
	public void addBoat(String type, Coord coordStart, Coord coordEnd) {
		ArrayList<Coord> coords = new ArrayList<Coord>();
		Coord tempCoord;
		
		int length = lengthHelper(coordStart, coordEnd);
		if (length == 0) return;
		int drow = (int) (coordEnd.getRow() - coordStart.getRow())/length;
		int dcol = (int) (coordEnd.getCol() - coordStart.getCol())/length;

		for (int i = 0; i < length; i ++) {
			tempCoord = new Coord(coordStart.getRow() + (i*drow), coordStart.getCol() + (i*dcol));
			coords.add(tempCoord);
		}
		this.getBoats().add(new Boat(coords, type));
	}
	
	/**
	 * Updates boats list to note a boat has been hit.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean updateBoats(int row, int col) {
		boolean changeMade = false;
		
		// iterate through the list of boats the player owns
		for (int i = 0; i < this.boats.size(); i++) {
			Boat currentBoat = this.boats.get(i);
			// update boat that has been hit
			if (currentBoat.checkHit(row, col)) {
				// boat has been updated, do something
				
				System.out.println(currentBoat.getType() + " is hit");
				
				// check if boat has been sunk
				if (currentBoat.getSunkValue()) {
					System.out.println(currentBoat.getType() + " is sunk");
				}
				
				changeMade = true;
			}
		}
		return changeMade;
	}
	
	public Coord getCoord() {
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Coord(row, col);
	}
	
	public Move getMove() {
		
		int row = getMove("row: ");
		int col = getMove("col: ");
		int missile = getMove("missile index: ");
		return new Move(row, col, missile);
	}
	
	private int getMove(String message) {
		
		int move, lower = 0;
		while (true) {
			try {
				System.out.print(message);
				String line = Player.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
	
}