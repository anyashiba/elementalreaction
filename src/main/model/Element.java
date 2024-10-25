package model;

import java.lang.String;

import org.json.JSONObject;

//element class that corresponds to an abiltiy 
public class Element {
    private String element;
    private String name;
    

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: constructs an element with specified element type and the abiltiy associated with element
    public Element(String element, String name) {
        this.element = element;
        this.name = name;
    }

    //getters

    public String getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    // code referenced from JsonSerializationDemo
    // EFFECTS: returns this element as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("element", element);
        json.put("name", name);
        return json;
    }
}
