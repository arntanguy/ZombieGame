package object;

import zombieGame.Field;
import zombieGame.Location;
import character.Character;
import character.Human;
import character.TypeCharacter;
import character.Vampire;

public class WoodenStake extends Weapons {
    
    private boolean utilise;
    
    public WoodenStake (Field field, Location location) {
        super(field, location, 1);
        utilise = false;
    }
    

    public void attackWeap(Human h, Character character) {
        if (utilise == false && character.getCharacter() == TypeCharacter.VAMPIRE) {
            utilise = true;
            character.setDead();
            h.weaponRemove();
        }
    }
    
    public boolean isDead() {
        return utilise;
    }
}
