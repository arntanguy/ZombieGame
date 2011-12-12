package zombiegame;
/**
 * Vampire class, derives from Character.
 * @author pylaffon
 *
 */
public class Vampire extends Character {
	private boolean isThirsty;
	// ... add your constructor code here (question 2) ...
	// Accessors and mutators
	public boolean getIsThirsty() {
		return isThirsty;
	}
	public void setIsThirsty(boolean isThirsty) {
		this.isThirsty = isThirsty;
	}
	/**
	 * Method triggered on each character at the end of each turn.
	 */
	public void endOfTurn() {
		// The vampire has 50% chance of becoming thirsty, if he is not already
		if (isThirsty || Simulator.GenerateRandomBoolean()) {
			isThirsty = true;
			say("I am thirsty now!!");
		}
	}
	/**
	 * Method called when a vampire decides to bite a human
	 * @param h Human who gets bitten by this vampire
	 */
	public void bite(Human h) {
		// The human has no way to escape. He gets bitten.
		h.setHasBeenBitten(true);
		say("I have bitten you, " + h.getName() + "!");
		// Vampire is not thirsty anymore
		isThirsty = false;
	}
}