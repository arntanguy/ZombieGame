package object;

import zombieGame.Field;
import zombieGame.Location;
import character.Character;
import character.Human;
import character.Vampire;
import character.Zombie;

public class LiquidNitrogen extends Weapons {
    
    private int quantite;
    
    public LiquidNitrogen (int quantite, Field field, Location location) {
        super(field, location, 2);
        if (quantite < 0 ) throw new IllegalArgumentException("Ne doit pas être inférieur à 0");
        this.quantite = quantite;
    }
    
    public void attackWeap (Human h,Character character) {
        if (quantite > 0 && (character instanceof Zombie || character instanceof Vampire)) {
            --quantite;
            character.setDead();
        }
        if(quantite == 0){
        	h.weaponRemove();
        }
    }
    
    public void recharge(int quantite) {
        this.quantite += quantite;
    }
    
    public boolean isDead() {
        return quantite == 0 ? true : false;
    }
    
    public int getQuantite() {
        return quantite;
    }
}