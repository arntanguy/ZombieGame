// test du plugin git
package character;

import zombieGame.Field;
import zombieGame.Location;

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
	// The character's position.
    private Location location;
    // The field occupied.
    private Field field;
    private boolean alive;
    
    
	/**
	 * Constructor of Character class.
	 * @param name name of the character
	 * @param healthPoints initial HP
	 */

	public Character(String name, int healthPoints, Location location, Field field) {
		this.name = name;
		this.healthPoints = healthPoints;
		this.field = field;
		setLocation(location);
		alive = true;
	}
	/**
     * Place the character at the new location in the given field.
     * 
     * @param newLocation
     *            The character's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
	
	 /**
     * Return the character's location.
     * 
     * @return The character's location.
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * Return the character's alive.
     * 
     * @return The character's alive.
     */
    public boolean getAlive() {
        return alive;
    }
    
    
    /**
     * Return the character's field.
     * 
     * @return The character's field.
     */
    public Field getField() {
        return field;
    }
    
	// Accessors
	public String getName() {
		return name;
	}
	public int getHealthPoints() {
		return healthPoints;
	}
	
	  /**
     * Indicate that the character is no longer alive. It is removed from the
     * field.
     */
    public void setDead() {
        alive = false;
        healthPoints = 0;
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
	
	public void endOfTurn(){};
	/**
	 * Decrease the number of HP by a certain amount. HP cannot go below 0.
	 * @param reduction number of HP to reduce
	 */
	public void reduceHealthPoints(int reduction) {
		healthPoints = healthPoints - reduction;
		if (healthPoints < 0) {
			/*healthPoints = 0;
			alive = false;*/
		    setDead();
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
