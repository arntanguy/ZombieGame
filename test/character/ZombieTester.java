package character;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import object.ShotGun;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombieGame.Field;
import zombieGame.Location;

public class ZombieTester {

    private Zombie zombie;
    private Human human;
    private Field field;
    private Location location;
    private final int HP = 100;
    private final int TAILLE = 4;
    private final int ROW = 2;
    private final int COL = 3;
    private final String NOM = "Zombie";
    
    @Before
    public void setUp() {
        field = new Field(TAILLE, TAILLE);
        human = new Human("Human", HP, new Location (ROW-1,COL), field);
        location = new Location(ROW, COL);
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
        List<Zombie> newZombies = new ArrayList<Zombie> ();
        zombie.hunt(newZombies);
        assertEquals(HP-100, human.getHealthPoints());
        //assertEquals(true, human.getHasBeenKillByZombie());
        human = new Human("Human", HP, new Location (ROW-1,COL), field);
        int munition = 3;
        int puissance = 2;
        ShotGun shotgun = new ShotGun(munition, puissance, field, 
                new Location (zombie.getLocation().getRow()-1, zombie.getLocation().getCol()));
        human.setWeapon(shotgun);
        List<Human> newHumans = new ArrayList<Human> ();
        human.run(newHumans);
        zombie.hunt(newZombies);
        assertEquals(HP, human.getHealthPoints());
    }
    
    @Test
    public void testEndOfTurn() {
        int munition = 3;
        int puissance = 2;
        ShotGun shotgun = new ShotGun(munition, puissance, field, new Location (ROW-1,COL));
        human.setWeapon(shotgun);
        List<Human> newHumans = new ArrayList<Human> ();
        human.run(newHumans);
        assertEquals(2, zombie.getTouchByWeap());
        for (int i = 0; i < puissance; ++i) {
            zombie.endOfTurn();
        }
        assertEquals(0, zombie.getTouchByWeap());
    }
    
    @Test
    public void testEncounterCharacter() {
        zombie.encounterCharacter(human);
        assertEquals(HP-100, human.getHealthPoints());
    }
    
    @Test
    public void testSetTouchByWeap() {
        int munition = 3;
        int puissance = 2;
        ShotGun shotgun = new ShotGun(munition, puissance, field, new Location (ROW-1,COL));
        Human h = new Human("Human", HP, new Location (ROW-1,COL), field);
        h.setWeapon(shotgun);
        List<Human> newHumans = new ArrayList<Human> ();
        h.run(newHumans);
        assertEquals(puissance, zombie.getTouchByWeap());
        int newPuis = 4;
        zombie.setTouchByWeap(newPuis);
        assertEquals(puissance + newPuis, zombie.getTouchByWeap());
    }
    
    @Test
    public void testGetTouchByWeap() {
        assertEquals(0, zombie.getTouchByWeap());
    }
    
    @Test
    public void testGetCharacter() {
        assertEquals(TypeCharacter.ZOMBIE, zombie.getCharacter());
    }
}
