package weapon;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import weapon.LiquidNitrogen;
import zombieGame.Field;
import zombieGame.Location;

import character.Human;
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
    
    @Before
    public void setUp() {
        location = new Location(ROW, COL);
        field = new Field(TAILLE, TAILLE);
        String nomHuman = "Human";
        String nomZombie = "Zombie";
        int width = 100;
        int depht = 100;
        nitrogen = new LiquidNitrogen(QUANTITE, field, location);
        Location location = new Location(3, 3);
        Field field = new Field(depht, width);
        zombie = new Zombie(nomZombie, HP, location, field);
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
        human.encounterCharacter(zombie);
        assertEquals(false, zombie.getAlive());
        assertEquals(QUANTITE-1, nitrogen.getQuantite());
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
            human.encounterCharacter(zombie);
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
}
