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
    //EFFECTS: uses the skill of the character and gives it a damage value
    public void callSkill() {
        //stub
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: uses the ult of the character and gives it a damage value
    public void callUlt() {
        //stub
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns list of attributes for the character
    public static ArrayList<String> attributes() {
        ArrayList<String> list = new ArrayList<>(); //stub

        return list; 
    }

    public String getName() {
        String name = "name"; //stub

        return name;
    }


    public String getElementalSkill() {
        String eSkill = "eSkill"; //stub

        return eSkill;
    }


    public String getElement() {
        String element = "element"; //stub

        return element;
    }
}
