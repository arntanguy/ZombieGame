package weapon;


import static org.junit.Assert.assertEquals;

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
    
    private final int MUNITIONS = 6;
    private final int PUISSANCE = 2;
    private final int HP_HUMAN = 100;
    
    @Before
    public void setUp() {
        String nomHuman = "Human";
        String nomZombie = "Zombie";
        
        int hpZombie = 30;
        int width = 100;
        int depht = 100;
        shotgun = new ShotGun(MUNITIONS, PUISSANCE);
        Location location = new Location(3, 3);
        Field field = new Field(depht, width);
        zombie = new Zombie(nomZombie, hpZombie, location, field);
        human = new Human(nomHuman, HP_HUMAN, location, field);
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
        human.encounterCharacter(zombie);
        assertEquals(MUNITIONS-1, shotgun.getMunition());
        zombie.encounterCharacter(human);
        assertEquals(HP_HUMAN, human.getHealthPoints());
        for(int i = 0; i < PUISSANCE; ++i) {
            zombie.endOfTurn();
        }
        zombie.encounterCharacter(human);
        assertEquals(HP_HUMAN-5, human.getHealthPoints());
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
            human.encounterCharacter(zombie);
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
        new ShotGun(munition, PUISSANCE);
    }
    
    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testExcPuissance() {
        int puissance = -4;
        new ShotGun(MUNITIONS, puissance);
    }
}
