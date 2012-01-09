package object;

import zombieGame.Field;
import zombieGame.Location;

public class Object {
	private Field field;
    private Location location;
    
    public Object (Field field, Location location) {
        this.field = field;
        this.location = location;
    }
    
    public Field getfield() {
        return field;
    }
    
    public Location getLocation() {
        return location;
    }
}
