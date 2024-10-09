package model;

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
    public void damage(int dmg) {
        health -= dmg;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: resets enemy HP back to 100
    public void resetHP() {
        health = 100;
    }

    //getters

    public int getHP() {
        return health;
    }

}