package model;

import java.util.ArrayList;

public class ElementalReaction {

    private ArrayList<String> battleLog;

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: constructs a reaction with an empty element list
    public ElementalReaction() {
        //stub
    }

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: call abilities using method, adds the abilities to the battlelog,
    // and it will return the value of damage it did
    public int react(String ability1, String ability2){
        return 1; //stub
    }

    public ArrayList<String> getLog() {
        return null; //stub
    }

}
