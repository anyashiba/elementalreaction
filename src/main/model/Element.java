package model;

import java.lang.String;

public class Element {
    private String element;
    private String name;
    

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: constructs an element with specified element type and the abiltiy associated with element
    public Element(String element, String skill) {
        this.element = element;
        this.name = skill;
    }

    //getters

    public String getElement() {
        return element;
    }

    public String getName() {
        return name;
    }
}
