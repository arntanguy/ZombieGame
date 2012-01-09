package object;

import zombieGame.Field;
import zombieGame.Location;
import character.Character;
import character.Human;

public abstract class Weapons extends BaseObject {
    
    private Field field;
    private Location location;
    private int typeTarget;
    
    public Weapons (Field field, Location location, int typeTarget) {
    	super(field, location);
        this.typeTarget = typeTarget;
    }
    
    public abstract void attackWeap (Human h,Character character);
    
    public abstract boolean isDead ();
    
    public int GetTypeTarget(){
    	return typeTarget;
    }
}
