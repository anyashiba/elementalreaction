package model;

import java.lang.String;
import java.util.ArrayList;

public class Element {
    private String element;
    private String character;
    private String elementalSkill;
    private String ultimateSkill;
    

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: constructs an element with an attached character, element, skill, ult
    public Element(String character, String element, String eskill, String ult) {
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


    public String getElementalSkill() {
         //stub
        return null;
    }

    public String getUlt() {
        return null; //stub
    }


    public String getElement() {
        //stub
        return null;
    }
}
