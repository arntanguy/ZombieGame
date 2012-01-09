package character;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import object.ShotGun;
import object.Weapons;

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
	private boolean hasBeenKillByZombie; // false, until a zombie kills this human
	private boolean haveWeapon;
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
		hasBeenKillByZombie = false;
		haveWeapon = false;
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
	
	// Accessors and mutators
	public boolean getHasBeenKillByZombie() {
		return hasBeenKillByZombie;
	}

	public void setHasBeenKillByZombie(){
		this.hasBeenKillByZombie = true;
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
	
	public void eat(){
		turnsSinceLastMeal = 0;
	}
	
	public void recharge(int nb){
		((ShotGun)weapon).recharge(nb);
	}
	
	 /**
     * This is what the human does most of the time - it runs around. Sometimes
     * it will breed or die of old age.
     * 
     * @param newHumans
     *            A list to add newly born humans to.
     */
    public void run(List<Human> newHumans) {
        if (getAlive()) {
            giveBirth(newHumans);
            Location newLocation = null;
            if(haveWeapon){
            	 say("I have a weapon!");
            	 // Move towards a source of food if found.
                findTarget(getLocation());
            }
            // Try to move into a free location.
            newLocation = getField().freeAdjacentLocation(getLocation());
            if (newLocation != null) {
                //System.out.println(newLocation);
                setLocation(newLocation);
            } else {
                // Overcrowding
                setDead();
            }
        }
    }
    /**
     * Tell the human to look for target adjacent to its current location. 
     * 
     * @param location
     *            Where in the field it is located.
     * @return Where target was found, or null if it wasn't.
     */
    private void findTarget(Location location) {
        List<Location> adjacent = getField().adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object character = getField().getObjectAt(where);
            Character c = (Character) character;
            if (c != null) {
                if ((c.getCharacter() == TypeCharacter.VAMPIRE) && (weapon.GetTypeTarget()==1)) {
                    Vampire v = (Vampire) character;
                    if (v.getAlive()) {
                    	weapon.attackWeap(v);
                    }
                }
                if ((c.getCharacter() == TypeCharacter.ZOMBIE) && 
                        ((weapon.GetTypeTarget()==2)||(weapon.GetTypeTarget()==3))) {
                    Zombie z = (Zombie) character;
                    if (z.getAlive()) {
                    	weapon.attackWeap(z);
                    }
                }
            }
        }
    }
    
    /**
     * Check whether or not this human is to give birth at this step. New
     * births will be made into free adjacent locations.
     * 
     * @param newhumans
     *            A list to add newly born humans to.
     */
    private void giveBirth(List<Human> newhumans) {
        // New humans are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Human young = new Human("human", 100, loc, getField());
            newhumans.add(young);
        }
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
	
	public void setWeapon(Weapons weapon) {
	    this.weapon = weapon;
	    haveWeapon = true;
	}
	public boolean getHaveWeapon(){
		return haveWeapon;
	}
	
	public Weapons getWeapon() {
	    return weapon;
	}
	
	public TypeCharacter getCharacter() {
	    return TypeCharacter.HUMAN;
	}
	
}
