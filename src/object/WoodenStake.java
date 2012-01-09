package object;

import zombieGame.Field;
import zombieGame.Location;
import character.Character;
import character.TypeCharacter;
import character.Vampire;

public class WoodenStake extends Weapons {
    
    private boolean utilise;
    
    public WoodenStake (Field field, Location location) {
        super(field, location, 1);
        utilise = false;
    }
    
    public void attackWeap(Character character) {
        if (utilise == false && character.getCharacter() == TypeCharacter.VAMPIRE) {
            utilise = true;
            character.setDead();
        }
    }
    
    public boolean isDead() {
        return utilise;
    }
}
