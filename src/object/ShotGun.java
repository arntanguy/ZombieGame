package object;

import zombieGame.Field;
import zombieGame.Location;
import character.Character;
import character.Human;
import character.Zombie;

public class ShotGun extends Weapons {

    private int munitions;
    private int puissance;
    
    public ShotGun (int munitions, int puissance, Field field, Location location) {
        super(field, location, 3);
        if (munitions < 0 || puissance < 0) 
            throw new IllegalArgumentException("Ne doit pas être inférieur à 0");
        this.munitions = munitions;
        this.puissance = puissance;
    }
    
    public void attackWeap (Human h,Character character) {
        if (munitions > 0 && character instanceof Zombie) {
            --munitions;
            ((Zombie) character).setTouchByWeap(puissance);
        }
        if(munitions == 0){
        	h.noAmmo();
        }
    }
    
    public void recharge(int munitions) {
        this.munitions += munitions;
    }
    
    public boolean isDead() {
        return munitions == 0 ? true : false;
    }
    
    public int getMunition() {
        return munitions;
    }
}
