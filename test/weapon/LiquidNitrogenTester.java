package weapon;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import object.LiquidNitrogen;
import zombieGame.Field;
import zombieGame.Location;

import character.Human;
import character.Vampire;
import character.Zombie;

public class LiquidNitrogenTester {

    private Human human;
    private Zombie zombie;
    private LiquidNitrogen nitrogen;
    private Field field;
    private Location location;

    private final int QUANTITE = 3;
    private final int HP = 100;
    private final int ROW = 2;
    private final int COL = 3;
    private final int TAILLE = 4;
    private final String NOM_ZOMBIE = "Zombie";
    
    @Before
    public void setUp() {
        location = new Location(ROW, COL);
        field = new Field(TAILLE, TAILLE);
        String nomHuman = "Human";

        nitrogen = new LiquidNitrogen(QUANTITE, field, location);
        zombie = new Zombie(NOM_ZOMBIE, HP, new Location(3, 3), field);
        human = new Human(nomHuman, HP, location, field);
        human.setWeapon(nitrogen);
    }

    @After
    public void tearDown() {
        nitrogen = null;
        human = null;
        zombie = null;
    }

    @Test
    public void testAttackWeap() {
        human.run(new ArrayList<Human>());
        assertEquals(false, zombie.getAlive());
        assertEquals(QUANTITE-1, nitrogen.getQuantite());
        Vampire vampire = new Vampire("Vampire", HP, new Location(human.getLocation().getRow(), 
                human.getLocation().getCol()-1), field);
        human.run(new ArrayList<Human>());
        assertEquals(QUANTITE-2, nitrogen.getQuantite());
    }
    
    @Test
    public void testRecharge() {
        int recharge = 5;
        nitrogen.recharge(recharge);
        assertEquals(recharge + QUANTITE, nitrogen.getQuantite());
    }
    
    @Test
    public void testIsDead() {
        assertEquals(false, nitrogen.isDead());
        for(int i = 0; i < QUANTITE; ++i) {
            human.run(new ArrayList<Human>());
            human.setLocation(location);
            zombie = new Zombie(NOM_ZOMBIE, HP, new Location(3, 3), field);
        }
        assertEquals(true, nitrogen.isDead());
    }
    
    @Test
    public void testgetQuantite() {
        assertEquals(QUANTITE, nitrogen.getQuantite());
    }
    
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testExcLiquidNitrogen() {
        int quantite = -4;
        new LiquidNitrogen(quantite, field, location);
    }
    
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testExcRecharge() {
        int recharge = -3;
        nitrogen.recharge(recharge);
    }
}
