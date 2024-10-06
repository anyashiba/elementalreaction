package model;

import java.util.ArrayList;

public class Character {
    private String name;
    private String element;
    private Element eSkill;
    private Element ult;

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: constructs a character with a name and gives it abilties the associated element specified
    public Character(String name, String element, Element eskill, Element ult) {
        this.name = name;
        this.element = element;
        this.eSkill = eskill;
        this.ult = ult;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns list of attributes for the character in the form attribute: trait
    public ArrayList<String> attributes() {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("Name: " + name);
        attributes.add("Element: " + element);
        attributes.add("ESkill: " + eSkill.getName());
        attributes.add("Ult: " + ult.getName());

        return attributes;
    }

    //getters

    public String getName() {
        return name;
    }


    public Element getESkill() {
         //stub
        return eSkill;
    }

    public Element getUlt() {
        return ult;
    }

    public String getElement() {
        return element;
    }
}
