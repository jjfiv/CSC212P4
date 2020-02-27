package edu.smith.cs.csc212.spooky;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents all of the game state needed to understand the player.
 * At the beginning of this project, it is just the ID or name of a place.
 * 
 * @author jfoley
 *
 */
public class Player {
	private GameTime hour;
	
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private String place;
	
	/**
	 * THey player will now remember places they have been.
	 */
	private Set<String> visited;

	/**
	 * A player is created at the start of a game with just an initial place.
	 * @param initialPlace - where do we start?
	 */
	public Player(String initialPlace) {
		this.place = initialPlace;
		this.visited = new HashSet<>();
		this.hour = new GameTime(0);
	}

	public int getHour() {
		// TODO Auto-generated method stub
		return hour.getHour();
	}
	
	public GameTime getGameTime() {
		// TODO Auto-generated method stub
		return hour;
	}

	/**
	 * Get access to the place instance variable from outside this class.
	 * @return the id of the current location.
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Call this method when the player moves to a new place.
	 * @param place - the place we are now located at.
	 */
	public void moveTo(String place) {
		this.rememberThisPlace();
		this.place = place;
		this.getHour(); 
		hour.increaseHour();
	}

	//When you visit a place for the second time, print out a message indicating to the player 
		//that they have been to that place before. This will not carry over between games. -- LAB DONE
	public void rememberThisPlace() {
		this.visited.add(place);
	}
	/**
	 * This checks the player's memory.
	 * @return true if we have been here before.
	 */
	//When you visit a place for the second time, print out a message indicating to the player 
	//that they have been to that place before. This will not carry over between games. -- LAB DONE
	public boolean hasBeenHereBefore() {
		return this.visited.contains(this.getPlace());
	}
}
