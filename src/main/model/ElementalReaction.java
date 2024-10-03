package model;

import java.util.ArrayList;

public class ElementalReaction {

    private ArrayList<Element> battleLog;

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: constructs a reaction with an empty element list
    public ElementalReaction() {
        //stub
    }

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: call abilities using method and it will return the value of damage it did
    public int react(String ability1, String ability2){
        return 1; //stub
    }

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: adds the element to the battle log list 
    public void addElement(Element element) {
        //stub
    }

}
