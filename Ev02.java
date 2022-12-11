//Second Iteration VERY GOOD, but still needs refinement and a bit of tweaking.
/*
 * Authors:
 * Lucas Bigler
 * Nick Savage
 */
import java.util.ArrayList;
import java.util.Random;

class MainLoop {
    public static void main(String[] args) {
        //user available settings
        World earth = new World(1337);
        int turns = 2000000;
        //turn turns into a user defined input
        //make iterations of dog and snake count. (dogg]Global++)
        //end settings
        
        earth.firstSpawn();

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
    Creature reproduce(float chance, float percentDogs){
        return null;
    }

    void print() {
        return;
    }
}

class World {
    World(long seed){
        rng.setSeed(seed);
    }
    Random rng = new Random();

    float spawnChance = 0.2f;
    ArrayList<Creature> creatures = new ArrayList<Creature>();

    void firstSpawn() {
        Creature first = new Dog( .5f );
        creatures.add(first);
        Creature sec = new Snake( .5f );
        creatures.add(sec);
        System.out.println(creatures.size());
    }

    void createCreature() {
        float chance = rng.nextFloat();
        if(chance < spawnChance){
            //Spawn a creature now
            float percentDogs = percentDogs();
            float createchance = rng.nextFloat();
            if (createchance > spawnChance) {
                Creature spawn = new Dog( percentDogs );
                creatures.add(spawn);
            } else {
                Creature spawn = new Snake( 1 - percentDogs );
                creatures.add(spawn);
            }
                        //Pick type of Creature then spawn it


        } else {
            // Creature first = new Dog();
            // creatures.add(first);

            if( creatures.size() < 1 )
                return;

            int index = rng.nextInt(0,creatures.size());
            Creature c = creatures.get(index);

            Creature baby = c.reproduce(rng.nextFloat(), percentDogs() );
            if(baby != null){
                creatures.add(baby);
            }
        }
    }

    void creatureRussianRoulette() {
        if( creatures.size() < 1 ) 
            return;

        float chance = rng.nextFloat();

        int index = rng.nextInt(0,creatures.size());
        Creature c = creatures.get(index);
        boolean dead = c.die(chance);
        if(dead){
            creatures.remove(index);
        }
    }

    // Returns percentage of Dogs in population.
    float percentDogs() {
        if( creatures.size() < 1 )
            return 0;
        int numDogs = 0;
        int numSnakes = 0;
        for( int i = 0; i < creatures.size(); i++ ) {
            if( creatures.get( i ) instanceof Dog )
                numDogs++;
            else
                numSnakes++;  
        }
        return (float)numDogs/(numDogs + numSnakes);
    }

    void print() {
        System.out.println(percentDogs());
    }
}

class Snake extends Creature {
    Snake( float percentSnakes ){
        dieChance = .2f;
        reproduceChance = .6f * percentSnakes;
    }
    @Override
    Creature reproduce(float chance, float percentDogs){
    if(chance < reproduceChance) {
        return new Snake( 1 - percentDogs );
    } else {
        return null;
        }
    }
    @Override
    void print() {
        System.out.println( "Snake" );
    }
}

class Dog extends Creature {
    Dog( float percentDogs ){
        dieChance = .05f;
        reproduceChance = .1f * percentDogs;
    }
    @Override
    Creature reproduce(float chance, float percentDogs ){
    if(chance < reproduceChance) {
        return new Dog( percentDogs );
    } else {
        return null;
        }
    }
    @Override
    void print() {
        System.out.println( "Dog" );
    }
}



//Adjust spawn rate based on relative population