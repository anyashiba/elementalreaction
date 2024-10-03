package model;

import java.lang.String;
import java.util.ArrayList;

public class Element {
    static final int baseDamage = 10;
    private String element;
    private String character;
    private String elementalSkill;
    private String ultimateSkill;
    

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: constructs an element with an attached character, element, skill, ult,
    //and starts the character off with 10 base dmg
    public Element(String character, String element, String eSkill, String ult) {
        //stub
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: uses the skill of the character and adds it to the elementalSkill list
    public void callSkill() {
        //stub
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: uses the ult of the character adds it to the elementalSkill list
    public void callUlt() {
        //stub
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns list of attributes for the character
    public static ArrayList<String> attributes() {
        //stub
        return null; 
    }

    public String getName() {
        //stub
        return null;
    }


    public String getElementalSkill() {
         //stub
        return null;
    }


    public String getElement() {
        //stub
        return null;
    }
}
