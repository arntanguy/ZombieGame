package zombieGame;

import java.util.Iterator;
import java.util.List;

/**
 * Vampire class, derives from Character.
 * @author pylaffon
 *
 */
public class Vampire extends Character {

	private boolean isThirsty;
	
    public Vampire(String name, int healthPoints, Location location, Field field) {
        super(name, healthPoints, location, field);
        isThirsty = false;
    }
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
     * This is what the fox does most of the time: it hunts for rabbits. In the
     * process, it might breed, die of hunger, or die of old age.
     * 
     * @param field
     *            The field currently occupied.
     * @param newFoxes
     *            A list to add newly born foxes to.
     */
    public void hunt(List<Vampire> newVampires) {
        if (getAlive()) {
            // Move towards a source of food if found.
            Location newLocation = findFood(getLocation());
            if (newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }
    
    /**
     * Tell the vampire to look for humans adjacent to its current location. Only
     * the first live human is eaten.
     * 
     * @param location
     *            Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood(Location location) {
        List<Location> adjacent = getField().adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object character = getField().getObjectAt(where);
            if (character instanceof Human) {
                Human h = (Human) character;
                if (h.getAlive()) {
                	if(isThirsty){
                		bite(h);
                	}
                	else{
                		attack(h);
                		if(!h.getAlive()){
                			h.setDead();
                			return where;
                		}
                	}
                }
            }
        }
        return null;
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
	
	protected void attack(Character c) {
	    c.reduceHealthPoints(10);
	}

	public void encounterCharacter(Character c) {
	    if (getAlive()) {
    	    // Vampires always attack 
    	    say(c.getName() + ", I'm gonna kill you!!");
    	    attack(c);
	    }
	}
}