package character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import weapon.LiquidNitrogen;
import zombieGame.Field;
import zombieGame.Location;

public class HumanTester {

    private Human human;
    private Field field;
    private Location location;
    private final int HP = 100;
    private final int TAILLE = 4;
    private final int ROW = 2;
    private final int COL = 3;
    private final String NOM_HUMAN = "Human";
    
    @Before
    public void setUp() {
        location = new Location(ROW, COL);
        field = new Field(TAILLE, TAILLE);
        human = new Human(NOM_HUMAN, HP, location, field);
    }

    @After
    public void tearDown() {
        human = null;
        location = null;
        field = null;
    }
    
    @Test
    public void testGetHasBeenBitten() {
        assertEquals(false, human.getHasBeenBitten());
        Vampire vampire = new Vampire ("Vampire", HP, location, field);
        vampire.bite(human);
        assertEquals(true, human.getHasBeenBitten());
    }
    
    @Test
    public void testSetHasBeenBitten() {
        human.setHasBeenBitten(true);
        assertEquals(true, human.getHasBeenBitten());
        human.setHasBeenBitten(false);
        assertEquals(false, human.getHasBeenBitten());
    }
    
    @Test
    public void testEndOfTurn() {
        assertEquals(HP, human.getHealthPoints());
        for (int i = 0; i <= 3; ++i) {
            human.endOfTurn();
        }
        assertEquals(HP-2, human.getHealthPoints());
    }
    
    @Test
    public void testRun() {
        List<Human> listHuman = new ArrayList<Human> ();
        for (int row = 0; row < TAILLE; row++) {
            for (int col = 0; col < TAILLE-1; col++) {
                    Location loc = new Location(row, col);
                    Human h = new Human(NOM_HUMAN + row + col, HP, loc, field);
                    listHuman.add(h);
            }
        }
        human.run(listHuman);
        assertEquals(new Location(ROW-1, COL), human.getLocation());
        Location loc = new Location(human.getLocation().getRow()-1, human.getLocation().getCol());
        listHuman.add(new Human(NOM_HUMAN, HP, loc, field));
        loc = new Location(human.getLocation().getRow()+1, human.getLocation().getCol());
        listHuman.add(new Human(NOM_HUMAN, HP, loc, field));
        human.run(listHuman);
        assertEquals(false, human.getAlive());
    }
    
    @Test
    public void testBirth() {
        List <Human> listHuman = new ArrayList<Human>();
        listHuman.add(human);
        int j = 0;
        while (j < 30) {
            List <Human> newBorn = new ArrayList<Human>();
            int b = human.run(newBorn);
            System.out.println(b);
            for (int i = 0; i < newBorn.size(); i++) {
                listHuman.add(newBorn.get(i));
            }
            ++j;
        }
        //System.out.println(listHuman.size());
        
        
    }
    
    @Test
    public void testTurnIntoVampire() {
        Vampire v = human.turnIntoVampire();
        assertEquals(NOM_HUMAN, v.getName());
        assertEquals(HP, v.getHealthPoints());
        assertEquals(location, v.getLocation());
        assertEquals(field, v.getField());
    }
    
    @Test
    public void testTurnIntoZombie() {
        Zombie z = human.turnIntoZombie();
        assertEquals(NOM_HUMAN, z.getName());
        assertEquals(30, z.getHealthPoints());
        assertEquals(location, z.getLocation());
        assertEquals(field, z.getField());
    }
    
    @Test
    public void testEncounterCharacter() {
        Zombie zombie = new Zombie("Zombie", HP, location, field);
        human.encounterCharacter(zombie);
        assertEquals(HP, zombie.getHealthPoints());
        LiquidNitrogen nitrogen = new LiquidNitrogen(1, field, location);
        human.setWeapon(nitrogen);
        human.encounterCharacter(zombie);
    }
    
    @Test
    public void testSetWeapon() {
        LiquidNitrogen nitrogen = new LiquidNitrogen(1, field, location);
        human.setWeapon(nitrogen);
        assertEquals(nitrogen, human.getWeapon());
    }
}
