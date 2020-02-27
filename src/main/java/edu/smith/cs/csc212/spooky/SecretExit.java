package edu.smith.cs.csc212.spooky;

public class SecretExit extends Exit{

	private boolean hidden = true;
	
	public SecretExit(String target, String description) {
		super(target, description);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isSecret() {
		//if (e.search()) {
			//return visible;
		//}
		return hidden;
	}
	
	public void search() {
		//if (action.equals("help")) {
			//System.out.println("Type in the number of the room to progress through the game! If you want to quit press "
					//+ "either 'quit,' 'escape', or 'q'!");
			//continue;
		//}
		hidden = false;
	}

}
