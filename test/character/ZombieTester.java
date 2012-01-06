package character;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombieGame.Field;
import zombieGame.Location;

public class ZombieTester {

    private Zombie zombie;
    private Field field;
    private Location location;
    private final int HP = 100;
    private final int TAILLE = 4;
    private final int ROW = 2;
    private final int COL = 3;
    private final String NOM = "Zombie";
    
    @Before
    public void setUp() {
        location = new Location(ROW, COL);
        field = new Field(TAILLE, TAILLE);
        zombie = new Zombie(NOM, HP, location, field);
    }

    @After
    public void tearDown() {
        zombie = null;
        field = null;
        location = null;
    }

    @Test
    public void testHunt() {
        
    }
}
