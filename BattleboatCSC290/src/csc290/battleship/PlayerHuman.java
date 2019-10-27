package csc290.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerHuman {
	
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter within range";
	private static final String IO_ERROR_MESSAGE = "I/O Error";
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	
	private char player;
	private ArrayList<Boat> boats;
	private ArrayList<Missile> missiles;
	private int dimensionReference = 10;
	
	protected Map<String, ArrayList<String>> setOfBoats; //shows what kind of boats this player can have
	
	// default Constructor
	public PlayerHuman(char player) {
		this.player = player;
		setUpBoats();
		this.boats = new ArrayList<Boat>(); // need to fill by method
		this.missiles = new ArrayList<Missile>();
		this.missiles.add(new Missile());
		this.dimensionReference = 10;
	}
	
	
	//default BOATs set
	public String setUpBoats() {
		setOfBoats = new HashMap<String, ArrayList<String>>();
		
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
		
		return "default";
	}

	public Coord getMove() {
		
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Coord(row, col);
	}
	
	private int getMove(String message) {
		
		int move, lower = 0, upper = this.dimensionReference--;
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
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
