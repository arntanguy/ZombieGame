package zombiegame;
/**
 * Zombie class, derives from Character.
 * @author pylaffon
 *
 */
public class Zombie extends Character {
	/**
	 * Constructor of Zombie class.
	 * @param name name of the character
	 * @param healthPoints initial HP
	 */
	public Zombie(String name, int healthPoints) {
		super(name, healthPoints);
	}
	/**
	 * Output a character's saying to the screen
	 * @param str what the character says
	 */
	public void say(String str) {
		System.out.println(name + " says: BRAIIIIIINS!");
	}
	/**
	 * Method triggered on each character at the end of each turn.
	 */
	public void endOfTurn() {
		// Do nothing. Zombies are useless anyway...
	}
}
