package model;

import java.util.ArrayList;

public class ElementalReaction {
    private static final int MELT = 20;
    private static final int FROZEN = 12;
    private static final int SUPERCONDUCT = 9;
    private static final int VAPORIZE = 18;
    private static final int OVERLOADED = 8;
    private static final int BURNING = 5;
    private static final int BLOOM = 15;
    private static final int ELECTROCHARGED = 8;
    private static final int QUICKEN = 15;
    private static final int CRYSTALLIZE = 7;
    private static final int SWIRL = 10;
    private static final int BASE = 2;

    private ArrayList<String> battleLog;

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: constructs a reaction with an empty element list
    public ElementalReaction() {
        //stub
    }

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: call abilities using method, adds the abilities to the battlelog
    //how much dmg it did, and it will return the value of damage it did
    public int react(Element ability1, Element ability2) {
        return 1; //stub
    }

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: returns the battlelog of abilties casted and the damage it dealt
    public ArrayList<String> getLog() {
        return null; //stub
    }

}
