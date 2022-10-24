
//First Iteration not very good
interface Happy {
    String happy = "";
    String howToHappy = "";
    boolean reproduce = false;

    void calcDistToHappy();
    void calcHappy();
}

interface Hunger extends Happy {
    String areHungry = "";
    String eat = "";

    void displayHungerToUser();
    void calcHunger();
}
 
interface Actions extends Hunger {
    boolean walk = false;
    boolean run = false;
    boolean alive = true;

    void calcWalkDist();
    void calcRunDist();
}

class dog implements Actions{
  
    public void calcWalkDist() {
        
    }
    public void calcRunDist(){

    }
    public void displayHungerToUser() {

    }
    public void calcHunger() {

    }
    public void calcDistToHappy() {

    }
    public void calcHappy() {

    }
    public void SetName() {

    }
    public void setBreed() {

    }

}

    class main1 { 
    // Driver Code
    public static void main(String[] args)
    {
        dog d = new dog();
        d.displayHungerToUser();
    }
}
