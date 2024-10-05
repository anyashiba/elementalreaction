package model;

import java.util.ArrayList;

public class Character {
    private String name;
    private Element elementalSkill;
    private Element ultimateSkill;

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: constructs a character with a name and gives it abilties the associated element specified
    public Character(String name, String element, Element eskill, Element ult) {
        //stub
    }


    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns list of attributes for the character in the form attribute: trait
    public ArrayList<String> attributes() {
        //stub
        return null; 
    }

    public String getName() {
        //stub
        return null;
    }


    public Element getESkill() {
         //stub
        return null;
    }

    public Element getUlt() {
        return null; //stub
    }
}
