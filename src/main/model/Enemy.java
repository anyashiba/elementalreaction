package model;

// enemy class that has hp and loses hp everytime a combination of 
// abilities are called
public class Enemy {
    private int health;

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: constructs an enemy with 100 hp
    public Enemy() {
        this.health = 100;
    }

    //REQUIRES: dmg > 0 and hp cannot be less than 0
    //MODIFIES: this
    //EFFECTS: damages enemy by given int 
    //EFFECTS: damages enemy by given int 
    public void damage(int dmg) {
        health -= dmg;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: resets enemy HP back to 100
    public void resetHP() {
        health = 100;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: sets enemy HP to given value
    public void setHP(int newHP) {
        health = newHP;
    }

    //getters

    public int getHP() {
        return health;
    }

}