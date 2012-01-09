package weapon;

import static org.junit.Assert.*;

import java.util.ArrayList;

import object.WoodenStake;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombieGame.Field;
import zombieGame.Location;
import character.Human;
import character.Vampire;

public class WoodenStakeTester {
    
    private Human human;
    private Vampire vampire;
    private WoodenStake stake;
    private Field field;
    private Location location;
    
    private final int ROW = 2;
    private final int COL = 3;
    private final int TAILLE = 4;
    private final String NOM_VAMP = "Vampire";
    private final int HP = 100;
    @Before
    public void setUp() {
        field = new Field(TAILLE, TAILLE);
        String nomHuman = "Human";
        location = new Location(ROW, COL);
        stake = new WoodenStake(field, location);
        vampire = new Vampire(NOM_VAMP, HP, new Location(3, 3), field); 
        human = new Human(nomHuman, HP, location, field);
        human.setWeapon(stake);
    }

    @After
    public void tearDown() {
        stake = null;
        human = null;
        vampire = null;
    }
    
    @Test
    public void testAttackWeap() {
        human.run(new ArrayList<Human>());
        assertEquals(false, vampire.getAlive());
        assertEquals(true, stake.isDead());
        vampire = new Vampire(NOM_VAMP, HP, new Location(3, 2), field); 
        human.run(new ArrayList<Human>());
        assertEquals(true, vampire.getAlive());
    }

    @Test
    public void testIsDead() {
        assertEquals(false, stake.isDead());
        human.run(new ArrayList<Human>());
        assertEquals(true, stake.isDead());
    }
}
