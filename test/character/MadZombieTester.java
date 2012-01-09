package character;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombieGame.Field;
import zombieGame.Location;

public class MadZombieTester {

    private Vampire vampire;
    private Zombie zombie;
    private MadZombie madZombie;
    private Field field;
    private Location location;
    private final int TAILLE = 4;
    private final int HP = 100;
    private final int ROW = 2;
    private final int COL = 3;
    
    @Before
    public void setUp() {
        location = new Location(ROW, COL);
        field = new Field(TAILLE, TAILLE);
        zombie = new Zombie("Zombie", HP, new Location(ROW-1, COL), field);
        vampire = new Vampire ("Vampire", HP, new Location(ROW, COL-1), field);
        madZombie = new MadZombie("MadZombie", HP, location, field);
    }

    @After
    public void tearDown() {
        location = null;
        field = null;
        zombie = null;
        vampire = null;
        madZombie = null;
    }

    @Test
    public void testHunt() {
        madZombie.hunt(null);
        assertEquals(HP-50, zombie.getHealthPoints());
        assertEquals(HP-50, vampire.getHealthPoints());
    }
    
    @Test
    public void testGetCharacter() {
        assertEquals(TypeCharacter.MADZOMBIE, madZombie.getCharacter());
    }
}
