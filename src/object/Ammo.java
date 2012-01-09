package object;

import character.Human;
import zombieGame.Field;
import zombieGame.Location;

public class Ammo extends BaseObject{
	private int nbAmmo;
	public Ammo(Field field, Location location, int nb){
		super(field, location);
		nbAmmo = nb;
	}
	
	public void takeAmmo(Human h){
		if(h.getHaveWeapon()){
			if(h.getWeapon().GetTypeTarget()==3){
				h.recharge(nbAmmo);
			}
		}
	}

}
