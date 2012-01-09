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
}