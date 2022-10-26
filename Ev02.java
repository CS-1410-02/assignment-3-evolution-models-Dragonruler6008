//Second Iteration VERY GOOD
import java.util.ArrayList;
import java.util.Random;

class MainLoop {
    public static void main(String[] args) {
        //user available settings
        World earth = new World(1337);
        int turns = 6;
        //end settings

        for(int i = 0; i < turns; i++){
            earth.createCreature();
            earth.creatureRussianRoulette();
        }
        earth.print();
        
    }
    
}

class Creature {
    float dieChance = .05f;
    float reproduceChance = .1f;

    boolean die(float chance){
        if(chance < dieChance) {
            return true;
        } else {
            return false;
        }
    }
    Creature reproduce(float chance){
        return null;
    }
}

class World {
    World(long seed){
        rng.setSeed(seed);
    }
    Random rng = new Random();

    float spawnChance = 0.2f;
    ArrayList<Creature> creatures = new ArrayList<Creature>();

    void createCreature() {
        float chance = rng.nextFloat();
        if(chance < spawnChance){
            //Spawn a creature now
            
            //Pick type of Creature then spawn it


        } else {
            int index = rng.nextInt(0,creatures.size());
            Creature c = creatures.get(index);

            Creature baby = c.reproduce(rng.nextFloat());
            if(baby != null){
                creatures.add(baby);
            }
        }
    }

    void creatureRussianRoulette() {
        float chance = rng.nextFloat();

        int index = rng.nextInt(0,creatures.size());
        Creature c = creatures.get(index);
        boolean dead = c.die(chance);
        if(dead){
            creatures.remove(index);
        }
    }

    void print() {
        for(int i = 0; i < creatures.size(); i++){
            System.out.println(creatures.get(i) instanceof Dog);
        }
    }
}

class Snake extends Creature {
    Snake(){
        dieChance = .2f;
        reproduceChance = .6f;
    }
    @Override
    Creature reproduce(float chance){
    if(chance < reproduceChance) {
        return new Snake();
    } else {
        return null;
        }
    }
}

class Dog extends Creature {
    Dog(){
        dieChance = .05f;
        reproduceChance = .1f;
    }
    @Override
    Creature reproduce(float chance){
    if(chance < reproduceChance) {
        return new Dog();
    } else {
        return null;
        }
    }
}