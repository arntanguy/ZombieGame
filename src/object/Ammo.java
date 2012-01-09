package object;

import character.Human;
import zombieGame.Field;
import zombieGame.Location;

public class Ammo extends Object{
	private int nbAmmo;
	public Ammo(Field field, Location location, int nb){
		super(field, location);
		nbAmmo = nb;
	}
	
	public boolean takeAmmo(Human h){
		if(h.getWeapon().GetTypeTarget()==3){
			h.recharge(nbAmmo);
			return true;
		}
		return false;
	}

}
