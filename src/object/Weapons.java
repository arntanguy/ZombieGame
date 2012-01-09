package object;

import zombieGame.Field;
import zombieGame.Location;
import character.Character;

public abstract class Weapons extends Object {
    
    private Field field;
    private Location location;
    private int typeTarget;
    
    public Weapons (Field field, Location location, int typeTarget) {
    	super(field, location);
        this.typeTarget = typeTarget;
    }
    
    public abstract void attackWeap (Character character);
    
    public abstract boolean isDead ();
    
    public int GetTypeTarget(){
    	return typeTarget;
    }
}
