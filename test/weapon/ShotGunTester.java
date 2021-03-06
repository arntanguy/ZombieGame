package weapon;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import object.ShotGun;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zombieGame.Field;
import zombieGame.Location;
import character.Human;
import character.Zombie;

public class ShotGunTester {

    private Human human;
    private Zombie zombie;
    private ShotGun shotgun;
    private Field field;
    private Location location;
    
    private final int MUNITIONS = 6;
    private final int PUISSANCE = 2;
    private final int HP = 100;
    private final int ROW = 2;
    private final int COL = 3;
    private final int TAILLE = 4;
    
    @Before
    public void setUp() {
        String nomHuman = "Human";
        String nomZombie = "Zombie";
        location = new Location(ROW, COL);
        field = new Field(TAILLE, TAILLE);
        shotgun = new ShotGun(MUNITIONS, PUISSANCE, field, location);
        zombie = new Zombie(nomZombie, HP, new Location(2, 2), field);
        human = new Human(nomHuman, HP, location, field);
        human.setWeapon(shotgun);
    }

    @After
    public void tearDown() {
        human = null;
        zombie = null;
        shotgun = null;
    }

    @Test
    public void testAttackWeap() {
        human.run(new ArrayList<Human>());
        assertEquals(MUNITIONS-1, shotgun.getMunition());
        zombie.hunt(new ArrayList<Zombie>());
        assertEquals(HP, human.getHealthPoints());
        for(int i = 0; i < PUISSANCE; ++i) {
            zombie.endOfTurn();
        }
        zombie.hunt(new ArrayList<Zombie>());
        assertEquals(0, human.getHealthPoints());
    }
    
    @Test
    public void testRecharge() {
        int recharge = 6;
        shotgun.recharge(recharge);
        assertEquals(recharge + MUNITIONS, shotgun.getMunition());
    }
    
    @Test
    public void testIsDead() {
        assertEquals(false, shotgun.isDead());
        for(int i = 0; i < MUNITIONS; ++i) {
            human.run(new ArrayList<Human>());
        }
        assertEquals(true, shotgun.isDead());
    }
    
    @Test
    public void testgetQuantite() {
        assertEquals(MUNITIONS, shotgun.getMunition());
    }
    
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testExcMunition() {
        int munition = -4;
        new ShotGun(munition, PUISSANCE, field, location);
    }
    
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testExcPuissance() {
        int puissance = -4;
        new ShotGun(MUNITIONS, puissance, field, location);
    }
    
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testExcRecharge() {
        int recharge = -6;
        shotgun.recharge(recharge);
    }
}
