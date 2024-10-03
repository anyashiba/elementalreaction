package model;

public class Enemy {
    private int health;

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: constructs an enemy with 100 hp
    public Enemy() {
        //stub
    }

    //REQUIRES: dmg > 0 and hp cannot be less than 0
    //MODIFIES: this
    //EFFECTS: damages enemy by given int and resets hp when enemy health < 0 
    public void damage(int dmg) {
        //stub
    }

}