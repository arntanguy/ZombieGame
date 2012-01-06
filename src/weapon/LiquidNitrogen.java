package weapon;

import character.Character;
import character.Vampire;
import character.Zombie;

public class LiquidNitrogen extends Weapons {
    
    private int quantite;
    
    public LiquidNitrogen (int quantite) {
        if (quantite < 0 ) throw new IllegalArgumentException("Ne doit pas être inférieur à 0");
        this.quantite = quantite;
    }
    
    public void attackWeap (Character character) {
        if (quantite > 0 && (character instanceof Zombie || character instanceof Vampire)) {
            --quantite;
            character.setDead();
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