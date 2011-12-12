package zombieGame;

public class MadZombie extends Zombie {

    public MadZombie(String name, int healthPoints) {

        super(name, healthPoints);

}

    protected void attack(Character c) {

        c.reduceHealthPoints(25);

    }

}