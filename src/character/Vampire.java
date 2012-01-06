package character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import zombieGame.Field;
import zombieGame.Location;
import zombieGame.Simulator;



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
	

    public void hunt(List<Vampire> newVampires) {
        if (getAlive()) {
            // Move towards a source of food if found.
            Location newLocation = findFood(getLocation());
            // try to move to a free location.
            Location loc = getField().freeAdjacentLocation(getLocation());
            // See if it was possible to move.
            if (loc != null) {
                setLocation(loc);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }
    
    /**
     * Tell the vampire to look for humans adjacent to its current location. 
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
                		return where;
                	}
                	else{
                		attack(h);
                		if(!h.getAlive()){
                			h.setDead();
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