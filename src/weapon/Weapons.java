package weapon;

import zombieGame.Field;
import zombieGame.Location;
import character.Character;

public abstract class Weapons {
    
    private Field field;
    private Location location;
    
    public Weapons (Field field, Location location) {
        this.field = field;
        this.location = location;
    }
    
    public abstract void attackWeap (Character character);
    
    public abstract boolean isDead ();
   
    public Field getfield() {
        return field;
    }
    
    public Location getLocation() {
        return location;
    }
}
