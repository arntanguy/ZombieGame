package character;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombieGame.Field;
import zombieGame.Location;

public class MadZombieTester {

    private MadZombie madZombie;
    private Human human;
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
        human = new Human("Human", HP, new Location(ROW-1, COL), field);
        madZombie = new MadZombie("MadZombie", HP, location, field);
    }

    @After
    public void tearDown() {
        location = null;
        field = null;
        human = null;
        madZombie = null;
    }

    @Test
    public void testHunt() {
        madZombie.hunt(null);
        assertEquals(HP-50, human.getHealthPoints());
    }
    
    @Test
    public void testGetCharacter() {
        assertEquals(TypeCharacter.MADZOMBIE, madZombie.getCharacter());
    }
}
