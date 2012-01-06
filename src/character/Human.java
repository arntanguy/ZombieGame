package character;

import java.util.List;
import java.util.Random;

import weapon.Weapons;
import zombieGame.Field;
import zombieGame.Location;
import zombieGame.Randomizer;



/**
 * Human class, derives from Character
 * @author pylaffon
 *
 */
public class Human extends Character {
	private boolean hasBeenBitten; // false, until a vampire bites this human
	private int turnsSinceLastMeal; // the human will lose health if he's too hungry
	// A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
 // The likelihood of a human breeding.
    private static final double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
	// Indique s'il l'humain possède une arme
    private Weapons weapon;
	/**
	 * Constructor of Human class.
	 * At the beginning of the game, humans just had dinner, and have not been bitten yet.
	 * @param name name of the character
	 * @param healthPoints initial HP
	 */
	public Human(String name, int healthPoints, Location location, Field field) {
		super(name, healthPoints, location, field);
		hasBeenBitten = false;
		turnsSinceLastMeal = 0;
		weapon = null;
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
     * This is what the human does most of the time - it runs around. Sometimes
     * it will breed or die of old age.
     * 
     * @param newHumans
     *            A list to add newly born humans to.
     */
    public int run(List<Human> newHumans) {
        int b = 0;
        if (getAlive()) {
            b =giveBirth(newHumans);
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
        return b;
    }
    
    /**
     * Check whether or not this human is to give birth at this step. New
     * births will be made into free adjacent locations.
     * 
     * @param newhumans
     *            A list to add newly born humans to.
     */
    private int giveBirth(List<Human> newhumans) {
        // New humans are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();
        int b= 0;
        for (b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Human young = new Human("human", 100, loc, getField());
            newhumans.add(young);
        }
        return b;
    }
    
    /**
     * Generate a number representing the number of births, if it can breed.
     * 
     * @return The number of births (may be zero).
     */
    private int breed() {
        int births = 0;
        if (rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }
   
	/**
	 * Transform this human who has been bitten, into a blood-thirsty vampire.
	 * @return a new object of class Vampire, with the same name and healthpoints
	 *as this human; the new vampire is immediately thirsty
	 */
	public Vampire turnIntoVampire() {
		return new Vampire(name, healthPoints, getLocation(), getField());
	}
	
	public Zombie turnIntoZombie() {
		return new Zombie(name, 30, getLocation(), getField());
	}

    public void encounterCharacter(Character c) {
        //vérifie si human est toujours en vie et qu'il possède une arme, il attaque l'ennemi
        if (getAlive()) {
            if (c instanceof Zombie || c instanceof Vampire) {
                if (weapon != null) {
                    say("I have a weapon!");
                    weapon.attackWeap(c);
                    if (weapon.isDead()) {
                        weapon = null;
                    }
                }
                else {
                    say("Go away!");
                }
            }
        }
    }
	
	public void setWeapon(Weapons weapon) {
	    this.weapon = weapon;
	}
	
	public Weapons getWeapon() {
	    return weapon;
	}
}
