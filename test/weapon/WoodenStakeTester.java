package weapon;

import static org.junit.Assert.*;

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

    @Before
    public void setUp() {
        String nomHuman = "Human";
        String nomVampire = "Vampire";
        int hpHuman = 100;
        int hpVampire = 30;
        int width = 100;
        int depht = 100;
        stake = new WoodenStake();
        Location location = new Location(3, 3);
        Field field = new Field(depht, width);
        vampire = new Vampire(nomVampire, hpVampire, location, field);
        human = new Human(nomHuman, hpHuman, location, field);
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
        assertEquals(false, stake.getUtilise());
        human.encounterCharacter(vampire);
        assertEquals(false, vampire.getAlive());
        assertEquals(true, stake.getUtilise());
    }

    @Test
    public void testIsDead() {
        assertEquals(false, stake.isDead());
        human.encounterCharacter(vampire);
        assertEquals(true, stake.isDead());
    }
    
    @Test
    public void testgetUtilise() {
        assertEquals(false, stake.getUtilise());
        human.encounterCharacter(vampire);
        assertEquals(true, stake.getUtilise());
    }
}
