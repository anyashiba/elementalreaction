package model;

import java.util.ArrayList;

import org.json.JSONObject;

import persistence.Writable;

// character class that is used to fight enemies
public class Character implements Writable {
    private String name;
    private String element;
    private Element eskill;
    private Element ult;

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: constructs a character with a name and gives it abilties the associated element specified
    public Character(String name, String element, Element eskill, Element ult) {
        this.name = name;
        this.element = element;
        this.eskill = eskill;
        this.ult = ult;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns list of attributes for the character in the form attribute: trait
    public ArrayList<String> attributes() {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("Name: " + name);
        attributes.add("Element: " + element);
        attributes.add("1: ESkill: " + eskill.getName());
        attributes.add("2: Ult: " + ult.getName());

        EventLog.getInstance().logEvent(new Event("Checked attributes for " + this.getName()));

        return attributes;
    }

    //getters

    public String getName() {
        return name;
    }


    public Element getESkill() {
        return eskill;
    }

    public Element getUlt() {
        return ult;
    }

    public String getElement() {
        return element;
    }

    // code referenced from JsonSerializationDemo
    // EFFECTS: returns this character as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("element", element);
        json.put("eskill", eskill.toJson());
        json.put("ult", ult.toJson());
        return json;
    }
}
