package character;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombieGame.Field;
import zombieGame.Location;

public class VampireTester {

    private Vampire vampire;
    private Field field;
    private Location location;
    private final int HP = 100;
    private final int TAILLE = 4;
    private final int ROW = 2;
    private final int COL = 3;
    private final String NOM_VAMPIRE = "Vampire";
    
    @Before
    public void setUp() {
        location = new Location(ROW, COL);
        field = new Field(TAILLE, TAILLE);
        vampire = new Vampire (NOM_VAMPIRE, HP, location, field);
    }

    @After
    public void tearDown() {
        vampire = null;
        field = null;
        location = null;
    }

    @Test
    public void testGetIsThirsty() {
        assertEquals(false, vampire.getIsThirsty());
    }
    
    @Test
    public void testSetIsThirsty() {
        vampire.setIsThirsty(true);
        assertEquals(true, vampire.getIsThirsty());
        vampire.setIsThirsty(false);
        assertEquals(false, vampire.getIsThirsty());
    }
    
    @Test
    public void testHunt() {
        List<Vampire> listVampire = new ArrayList<Vampire>();
        List<Human> listHuman = new ArrayList<Human> ();
        for (int row = 0; row < TAILLE; row++) {
            for (int col = 0; col < TAILLE-1; col++) {
                    Location loc = new Location(row, col);
                    Human human = new Human ("Human", HP, loc, field);
                    listHuman.add(human);
            }
        }
        vampire.hunt(listVampire);
        assertEquals(new Location(1,3), vampire.getLocation());
        List<Location> adjacent = vampire.getField().adjacentLocations(location);
        Iterator<Location> it = adjacent.iterator();
        while (it.hasNext()) {
            Location where = it.next();
            Object character = vampire.getField().getObjectAt(where);
            
            if (character instanceof Human) {
                Human h = (Human) character;
                assertEquals(HP-10, h.getHealthPoints());
            }
        }
        vampire.setIsThirsty(true);
        vampire.hunt(listVampire);
        it = adjacent.iterator();
        Location where = new Location(1,2);
        Object character = vampire.getField().getObjectAt(where);
        if (character instanceof Human) {
            Human h = (Human) character;
            assertEquals(true, h.getHasBeenBitten());
        }
    }
    
    @Test
    public void testEncounterCharacter() {
        Human human = new Human ("Human", HP, new Location(ROW-1, COL), field);
        vampire.encounterCharacter(human);
        assertEquals(HP-10, human.getHealthPoints());
    }
    
    @Test
    public void testGetCharacter() {
        assertEquals(TypeCharacter.VAMPIRE, vampire.getCharacter());
    }
}
