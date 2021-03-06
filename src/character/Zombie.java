package character;

import java.util.Iterator;
import java.util.List;

import object.BaseObject;

import zombieGame.Field;
import zombieGame.Location;
import zombieGame.Simulator;



/**
 * Zombie class, derives from Character.
 * @author pylaffon
 *
 */
public class Zombie extends Character {
    
    private int touchByWeap; // permet de savoir si zombie a été touché par un shotgun
    
	/**
	 * Constructor of Zombie class.
	 * @param name name of the character
	 * @param healthPoints initial HP
	 */
	public Zombie(String name, int healthPoints, Location location, Field field) {
		super(name, healthPoints, location, field);
		touchByWeap = 0;
	}
	/**
	 * Output a character's saying to the screen
	 * @param str what the character says
	 */
	public void say(String str) {
		System.out.println(name + " says: BRAIIIIIINS!");
	}
	
    public void hunt(List<Zombie> newZombies) {
        if (getAlive() && touchByWeap == 0) {
            // Move towards a source of food if found.
            Location newLocation = findFood(getLocation());
            if (newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if (newLocation != null) {
            	if(getField().getObjectAt(newLocation) instanceof BaseObject){
            		BaseObject o = (BaseObject)getField().getObjectAt(newLocation);
            		o.setPut();
            	}
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

            Character c = null;
            try {
            	c = (Character) character;
            } catch (ClassCastException e) {
            	continue;
            }
            if (c != null) {
                if (c.getCharacter() == TypeCharacter.HUMAN) {
                    Human h = (Human) character;
                    if (h.getAlive()) {
                    	attack(h);
                    	if(!h.getAlive()){
                    		h.setHasBeenKillByZombie();
                   			return where;
                    	}
                    }
                }
            }
        }
        return null;
    }
	
	/**
	 * Method triggered on each character at the end of each turn.
	 */
	public void endOfTurn() {
		// Do nothing. Zombies are useless anyway...
	    if (touchByWeap > 0) {
	        --touchByWeap;
	    }
	    
	}
	
	protected void attack(Character c) {

	    c.reduceHealthPoints(100);

	}

	public void encounterCharacter(Character c) {
	    // Attack if the enemy is human or vampire (then 50% chance)
	    if (getAlive() && touchByWeap == 0) {
	        if (c.getCharacter() == TypeCharacter.HUMAN || 
                    (c.getCharacter() == TypeCharacter.VAMPIRE && Simulator.GenerateRandomBoolean())) {
    	        super.say(c.getName() + ", I'm gonna kill you!!"); // want Character#say not Zombie#say
    	        attack(c);
    	    }
	    }

	}
	
	/**
	 * Change l'état du touchByWeap
	 * 
	 * @param touchByWeap
	 */
	public void setTouchByWeap(int touchByWeap) {
	    this.touchByWeap += touchByWeap;
	}
	
	/**
	 * Renvoie touchByWeap, si touchByWeap est supérieur à 0 alors le zombie a été touché par un shotgun
	 * sinon s'il est égal à 0 alors il n'a pas été touché par un shotgun
	 * 
	 * @param touchByWeap
	 */
    public int getTouchByWeap() {
         return touchByWeap;
    }
    
    public TypeCharacter getCharacter() {
        return TypeCharacter.ZOMBIE;
    }
}
