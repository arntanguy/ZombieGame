package object;

import character.Human;
import zombieGame.Field;
import zombieGame.Location;

public class Food extends BaseObject{
	public Food (Field field, Location location) {
        super(field,location);
    }
	public void useFood(Human h){
		h.eat();
	}
}
