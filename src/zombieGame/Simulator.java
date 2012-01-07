package zombieGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import weapon.LiquidNitrogen;
import weapon.ShotGun;
import weapon.WoodenStake;

import character.Character;
import character.Human;
import character.MadZombie;
import character.Vampire;
import character.Zombie;

/**
 * Simulator for Midterm Zombiegame.
 * @author pylaffon
 *
 */
public class Simulator {
	// Default health points for our creatures
	private static final int HP_HUMANS = 100;
	private static final int HP_VAMPIRES = 150;
	private static final int HP_ZOMBIES = 30;
	// Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 100;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 100;
    // The probability that a human will be created in any given grid position.
    private static final double HUMAN_CREATION_PROBABILITY = 4;
    // The probability that a human will be created in any given grid position.
    private static final double VAMPIRE_CREATION_PROBABILITY = 3;
    // The probability that a human will be created in any given grid position.
    private static final double ZOMBIE_CREATION_PROBABILITY = 3;
    // The probability that a human will be created in any given grid position.
    private static final double MADZOMBIE_CREATION_PROBABILITY = 6;

    private List<Human> humans;
    private List<Zombie> zombies;
    private List<Vampire> vampires;
    private List<MadZombie> madZombies;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;

    private List<ShotGun> shotguns;
    private List<LiquidNitrogen> nitrogens;
    private List<WoodenStake> stakes;
	
	// List of characters currently in the game
	private ArrayList<Character> characterList;
	/**
	 * Initialize game.
	 */
	public void init() {
//		// Create characters
//		Human h1 = new Human("Human 1", HP_HUMANS);
//		Human h2 = new Human("Human 2", HP_HUMANS);
//		Vampire v1 = new Vampire("Vampire 1", HP_VAMPIRES);
//		Vampire v2 = new Vampire("Vampire 2", HP_VAMPIRES);
//		Zombie z1 = new Zombie("Zombie 1", HP_ZOMBIES);
//		MadZombie mz1 = new MadZombie("MadZombie 1", HP_ZOMBIES); 
		// Add characters to the list
		characterList = new ArrayList<Character>();
//		characterList.add(h1);
//		characterList.add(h2);
//		characterList.add(v1);
//		characterList.add(v2);
//		characterList.add(z1);
//		characterList.add(mz1);
		/*humans = new ArrayList<Human>();
	    zombies = new ArrayList<Zombie>();
	    vampires = new ArrayList<Vampire>();
	    madZombies = new ArrayList<MadZombie>();*/
//	    humans.add(h1);
//	    humans.add(h2);
//	    vampires.add(v1);
//	    vampires.add(v2);
//	    zombies.add(z1);
//	    madZombies.add(mz1);
	    
	    shotguns = new ArrayList<ShotGun>();
	    nitrogens = new ArrayList<LiquidNitrogen>();
	    stakes = new ArrayList<WoodenStake>();
	    
	    field = new Field(DEFAULT_DEPTH, DEFAULT_WIDTH);

	    // Create a view of the state of each location in the field.
	    view = new SimulatorView(DEFAULT_DEPTH, DEFAULT_WIDTH);
	    view.setColor(Human.class, Color.orange);
	    view.setColor(Zombie.class, Color.green);
	    view.setColor(Vampire.class, Color.black);
	    view.setColor(MadZombie.class, Color.red);
	    

	    // Setup a valid starting point.
	    reset();
	}
	/**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public void runLongSimulation() {
        simulate(50000);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * 
     * @param numSteps
     *            The number of steps to run for.
     */
    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }
	
	/**
	 * Perform all game logic for next turn.
	 */
	public void nextTurn() {
		// All characters encounter the next character in the list (question 5)
		/*for (int i = 0; i < characterList.size(); ++i) {
			Character c = characterList.get(i);
			Character encountered = characterList.get((i+1)%(characterList.size()));
			c.encounterCharacter(encountered);
		}*/
		 // Dead characters are removed from the character list
        // ... add your code here (question 6) ...
        // Need an iterator for removing objects while looping over a collection
        for (Iterator<Character> it = characterList.iterator(); it.hasNext();) {
            Character c = it.next();
            if (c.getHealthPoints() <= 0) {
                it.remove();
            }
        }
     // Each vampire (if he is thirsty) bites the first Human in the list who has not been bitten yet
     // ... add your code here (question 7a) ...
      /*for (Character c1 : characterList) {
          if ((c1 instanceof Vampire) && ((Vampire) c1).getIsThirsty()) {
              Vampire v = (Vampire) c1;

              // Find the first human in the list, and bite him
              boolean hasBitten = false;
              for (Character c2 : characterList) {
                  if (!hasBitten && (c2 instanceof Human) && !((Human) c2).getHasBeenBitten()) {
                      v.bite((Human) c2);
                      hasBitten = true;
                  }
              }
          }
      }*/
		// Humans that have been bitten become vampires

		// ... add your code here (question 7b) ...

		for (int i = 0; i < characterList.size(); ++i) {
			Character c = characterList.get(i);
			if (c instanceof Human && ((Human) c).getHasBeenBitten()) {
				Vampire newVampire = ((Human) c).turnIntoVampire();
				characterList.set(i, newVampire);
				c = null;
			}
		}
		//Perform end-of-turn actions for all characters (question 4)
		for (int i = 0; i < characterList.size(); ++i) {
			Character c = characterList.get(i);
			c.endOfTurn();
		}

	}
	
	/**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        step++;

        // Provide space for newborn humans.
        List<Human> newHumans = new ArrayList<Human>();
        // Provide space for newborn vampires.
        List<Vampire> newVampires = new ArrayList<Vampire>();
        // Provide space for newborn wolfs.
        List<Zombie> newZombies = new ArrayList<Zombie>();
        // Let all characters act.
        for (Iterator<Character> it = characterList.iterator(); it.hasNext();) {
        	Character c = it.next();
        	if(c instanceof Human){
        		Human h = (Human) c;
        		h.run(newHumans);
                /*if (!h.getAlive()) {
                    it.remove();
                } else {
                    h.run(newHumans);
                }*/
        	}
        	if(c instanceof Vampire){
        		Vampire v = (Vampire) c;
        		v.hunt(newVampires);
                /*if (!v.getAlive()) {
                    it.remove();
                } else {
                    v.hunt(newVampires);
                }*/
        	}
        	if(c instanceof Zombie){
        		Zombie z = (Zombie) c;
        		z.hunt(newZombies);
                /*if (!z.getAlive()) {
                    it.remove();
                } else {
                    z.hunt(newZombies);
                }*/
        	}
        }

        // Add the newly born humans, vampires and zombies to the main lists.
        characterList.addAll(newHumans);
        characterList.addAll(newVampires);
        characterList.addAll(newZombies);

        view.showStatus(step, field);
    }
	
	/**
	 * @return the number of human characters currently in the game
	 */
	public int nbHumansAlive() {
		//Need to iterate through the list of characters
		//and count the number of humans
		int nbHumans = 0;
		for (Character character : characterList) {
			if (character instanceof Human) {
				++nbHumans;
			}
		}
		return nbHumans;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Game initialization
		Simulator sim = new Simulator();
		sim.init();
		System.out.println("Game starts with " + sim.nbHumansAlive() + " humans!");
		//Iterate until no alive human remains
		while (sim.nbHumansAlive() > 0) {
		    sim.simulateOneStep();
		    sim.nextTurn();
		    try{
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
		}
		sim.simulateOneStep();
		System.out.println("All humans have been eaten!");
	}
	/**
	 * Generate a pseudo-random boolean.
	 * @return pseudo-random boolean
	 */
	public static boolean GenerateRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	/**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        /*humans.clear();
        zombies.clear();
        vampires.clear();
        madZombies.clear();*/
        populate();

        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate() {
        Random rand = new Random();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                if (rand.nextInt(6) <= HUMAN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Human h = new Human("Human" + row + col, HP_HUMANS, location, field);
                    characterList.add(h);
                    //donne des armes au hasard aux humainss
                    /*int quantite = 1000000;
                    int puis = 2000;
                    switch (rand.nextInt(3))
                    {
                        case 1 :
                            h.setWeapon(new ShotGun(quantite, puis));
                            break;
                        case 2 :
                            h.setWeapon(new LiquidNitrogen(quantite));
                            break;
                        case 3 :
                            h.setWeapon(new WoodenStake());
                            break;
                    }*/
                } else if (rand.nextInt(6) <= VAMPIRE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Vampire v = new Vampire("Vampire" + row + col, HP_VAMPIRES, location, field);
                    characterList.add(v);
                }
                else if(rand.nextInt(6) <= ZOMBIE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Zombie z = new Zombie("Zombie" + row + col, HP_ZOMBIES, location, field);
                    characterList.add(z);
                }
//                else if(rand.nextDouble() <= HUMAN_CREATION_PROBABILITY) {
//                    Location location = new Location(row, col);
//                    Human human = new Human(true, field, location);
//                    humans.add(human);
//                }
            }
        }
    }
}
