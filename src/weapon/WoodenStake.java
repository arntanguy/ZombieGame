package weapon;

import character.Character;
import character.Vampire;

public class WoodenStake extends Weapons {
    
    private boolean utilise;
    
    public WoodenStake () {
        utilise = false;
    }
    
    public void attackWeap(Character character) {
        if (utilise == false && character instanceof Vampire) {
            utilise = true;
            character.setDead();
        }
    }
    
    public boolean isDead() {
        return utilise;
    }
    
    public boolean getUtilise() {
        return utilise;
    }
}
