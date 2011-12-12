package zombieGame;
/**
 * Human class, derives from Character
 * @author pylaffon
 *
 */
public class Human extends Character {
	private boolean hasBeenBitten; // false, until a vampire bites this human
	private int turnsSinceLastMeal; // the human will lose health if he's too hungry
	/**
	 * Constructor of Human class.
	 * At the beginning of the game, humans just had dinner, and have not been bitten yet.
	 * @param name name of the character
	 * @param healthPoints initial HP
	 */
	public Human(String name, int healthPoints) {
		super(name, healthPoints);
		hasBeenBitten = false;
		turnsSinceLastMeal = 0;
	}
	// Accessors and mutators
	public boolean getHasBeenBitten() {
		return hasBeenBitten;
	}
	public void setHasBeenBitten(boolean hasBeenBitten) {
		this.hasBeenBitten = hasBeenBitten;
	}
	/**
	 * Method triggered on each character at the end of each turn.
	 */
	public void endOfTurn() {
		// Increment the number of turns since the last time the human ate
		turnsSinceLastMeal++;
		// If the human is too hungry, he will lose health...
		if (turnsSinceLastMeal > 3) {
			healthPoints -= 2;
		}
	}
	/**
	 * Transform this human who has been bitten, into a blood-thirsty vampire.
	 * @return a new object of class Vampire, with the same name and healthpoints
	 *
as this human; the new vampire is immediately thirsty
	 */
	public Vampire turnIntoVampire() {
		return new Vampire(name, healthPoints);
	}
	
	public void encounterCharacter(Character c) { 

	    say("Go away!");

	}
}
