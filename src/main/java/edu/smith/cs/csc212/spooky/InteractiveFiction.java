package edu.smith.cs.csc212.spooky;

import java.util.List;

/**
 * This is our main class for SpookyMansion.
 * It interacts with a GameWorld and handles user-input.
 * It can play any game, really.
 *
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This method actually plays the game.
	 * @param input - a helper object to ask the user questions.
	 * @param game - the places and exits that make up the game we're playing.
	 * @return where - the place the player finished.
	 */
	static String runGame(TextInput input, GameWorld game) {
		// This is the current location of the player (initialize as start).
		Player player = new Player(game.getStart());

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		
		System.out.println("Type help if you need some clarification!");
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(player.getPlace());
			
			System.out.println();
			System.out.println("... --- ...");
			System.out.println(here.getDescription());
			System.out.println(player.getHour() + ":00");
			
			//When you visit a place for the second time, print out a message indicating to the player 
			//that they have been to that place before. This will not carry over between games. -- LAB DONE
			if (player.hasBeenHereBefore()) {
				System.out.println("Why do we remember the layout of this place ... Have we perhaps been here before???");
			}
			player.rememberThisPlace();
			
			// Game over after print!
			if (here.isTerminalState()) {
				break;
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits();

			for (int i=0; i<exits.size(); i++) {
				Exit e = exits.get(i);
				System.out.println(" "+i+". " + e.getDescription());
			}

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords("?");
			if (words.size() > 1) {
				System.out.println("Only give the system 1 word at a time!");
				continue;
			}

			// Get the word they typed as lowercase, and no spaces.
			// Do not uppercase action -- I have lowercased it.
			String action = words.get(0).toLowerCase().trim();
 
			//(3) Allow users to quit with the word "escape" or just the letter "q". (They will still need to press enter). DONE
			if (action.equals("quit") || action.equals("escape") || action.equals("q")) {
				if (input.confirm("Are you sure you want to quit?")) {
					// quit!
					break;
				} else {
					// go to the top of the game loop!
					continue;
				}
			}
			//(3) Add a "help" command that explains to type in the number of the room as well as how to quit. DONE
			if (action.equals("help")) {
				System.out.println("Type in the number of the room to progress through the game! If you want to quit press "
						+ "either 'quit,' 'escape', or 'q'!");
				continue;
			}
			
			if (action.equals("search")) {
				here.searchAllExitsEvenTheInvisibleOnes();
				continue;
			}
			
			if (action.equals("rest")) {
				player.getGameTime().increaseHour();
				player.getGameTime().increaseHour();
				System.out.println("You've rested for 2 hours ... I wonder what time it is now?");
				continue;
			}
			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = exits.get(exitNum);
			if (destination.canOpen(player)) {
				player.moveTo(destination.getTarget());
			} else {
				// TODO: some kind of message about it being locked?
			}
		}

		return player.getPlace();
	}

	/**
	 * This is where we play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();

		// Actually play the game.
		runGame(input, game);

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println("\n\n>>> GAME OVER <<<");
	}

}
