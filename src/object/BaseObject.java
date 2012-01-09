package object;

import zombieGame.Field;
import zombieGame.Location;

public class BaseObject {
	private Field field;
    private Location location;
    
    public BaseObject (Field field, Location location) {
        this.field = field;
        setLocation(location);
    }
    
    /**
     * Place the object at the new location in the given field.
     * 
     * @param newLocation
     *            The object's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Indicate that the object was put. It is removed from the
     * field.
     */
    public void setPut() {
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
    
    public Field getfield() {
        return field;
    }
    
    public Location getLocation() {
        return location;
    }
}
