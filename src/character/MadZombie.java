package character;

import java.util.Iterator;
import java.util.List;

import zombieGame.Field;
import zombieGame.Location;

public class MadZombie extends Zombie {

    public MadZombie(String name, int healthPoints, Location location, Field field) {

        super(name, healthPoints, location, field);

    }

    protected void attack(Character c) {

        c.reduceHealthPoints(50);

    }

    public TypeCharacter getCharacter() {
        return TypeCharacter.MADZOMBIE;
    }
    
    public void hunt(List<Zombie> newZombies) {
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
    
    private Location findFood(Location location) {
        List<Location> adjacent = getField().adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object character = getField().getObjectAt(where);
            Character c = (Character) character;
            if (c != null) {
                if (c.getCharacter() == TypeCharacter.HUMAN ||
                        c.getCharacter() == TypeCharacter.ZOMBIE ||
                            c.getCharacter() == TypeCharacter.VAMPIRE) {
                    if (c.getAlive()) {
                        attack(c);
                        if(!c.getAlive()){
                            return where;
                        }
                    }
                }
            }
        }
        return null;
    }
}