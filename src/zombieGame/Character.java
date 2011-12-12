package zombieGame;
/**
 * Parent Character class
 * @author pylaffon
 *
 */
public class Character {
	protected String name;
	// name of the character
	protected int healthPoints; // represents the health
	// (once down to 0, this character will be destroyed)
	/**
	 * Constructor of Character class.
	 * @param name name of the character
	 * @param healthPoints initial HP
	 */
	public Character(String name, int healthPoints) {
		this.name = name;
		this.healthPoints = healthPoints;
	}
	// Accessors
	public String getName() {
		return name;
	}
	public int getHealthPoints() {
		return healthPoints;
	}
	
	public void endOfTurn(){};
	/**
	 * Decrease the number of HP by a certain amount. HP cannot go below 0.
	 * @param reduction number of HP to reduce
	 */
	public void reduceHealthPoints(int reduction) {
		healthPoints = healthPoints - reduction;
		if (healthPoints < 0) {
			healthPoints = 0;
		}
	}
	/**
	 * Output a character's saying to the screen
	 * @param str what the character says
	 */
	public void say(String str) {
		System.out.println(name + " says: " + str);
	}
	/**
	 * Method triggered when the character described by the current object
	 * meets another character, and does something to him (for example, attack).
	 * @param c the other character that this character meets
	 */
	public void encounterCharacter(Character c) {
		// Default action: do nothing
		System.out.println(name + " meets " + c.name + " and does not attack!");
	}
}
