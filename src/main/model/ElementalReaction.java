package model;

import java.util.ArrayList;

public class ElementalReaction {
    private static final int MELT = 20;
    private static final int FROZEN = 12;
    private static final int SUPERCONDUCT = 9;
    private static final int VAPORIZE = 18;
    private static final int BURNING = 5;
    private static final int BLOOMANDQUICKEN = 15;
    private static final int ELECTROCHARGEDANDOVERLOADED = 8;
    private static final int CRYSTALLIZE = 7;
    private static final int SWIRL = 10;
    private static final int BASE = 2; 

    private ArrayList<String> battleLog;

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: constructs a reaction with an empty element list
    public ElementalReaction() {
        this.battleLog = new ArrayList<String>();
    }

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: call abilities using method, adds the abilities to the battlelog
    //how much dmg it did, and it will return the value of damage the abilties did based on
    //their combinations
    @SuppressWarnings("methodlength")
    public int react(Element a1, Element a2) {
        int dmg = 0;

        if (a1.getElement().equals(a2.getElement())) {
            dmg = BASE;
        } else if (bloomOrQuicken(a1, a2)) {
            dmg = BLOOMANDQUICKEN;
        } else if (burning(a1, a2)) {
            dmg = BURNING;
        } else if (vaporize(a1, a2)) {
            dmg = VAPORIZE;
        } else if (melt(a1, a2)) {
            dmg = MELT; 
        } else if (superconduct(a1, a2)) {
            dmg = SUPERCONDUCT;
        } else if (frozen(a1, a2)) {
            dmg = FROZEN;
        } else if (overloadedOrEC(a1, a2)) {
            dmg = ELECTROCHARGEDANDOVERLOADED;
        } else if (swirl(a1, a2)) {
            dmg = SWIRL;
        } else if (crystallize(a1, a2)) {
            dmg = CRYSTALLIZE;
        } else {
            dmg = BASE;
        }

        battleLog.add(a1.getName() + " and " + a2.getName() + " did " + dmg + " damage!");
        return dmg;
    }

    //EFFECTS: returns true if a1 = Pyro or Cryo and a2 = Pyro or Cryo
    private boolean melt(Element a1, Element a2) {
        return 
            (a1.getElement().equals("Pyro") || a1.getElement().equals("Cryo"))
            && (a2.getElement().equals("Cryo") || a2.getElement().equals("Pyro"));
    }

    //EFFECTS: returns true if a1 = Hydro or Cryo and a2 = Hydro or Cryo
    private boolean frozen(Element a1, Element a2) {
        return 
            ((a1.getElement().equals("Cryo") || a1.getElement().equals("Hydro"))
            && (a2.getElement().equals("Hydro") || a2.getElement().equals("Cryo")));
    }

    //EFFECTS: returns true if a1 = Electro or Cryo and a2 = Electro or Cryo
    private boolean superconduct(Element a1, Element a2) {
        return 
            ((a1.getElement().equals("Electro") || a1.getElement().equals("Cryo"))
            && (a2.getElement().equals("Cryo") || a2.getElement().equals("Electro")));
    }

    //EFFECTS: returns true if a1 = Pyro or Hydro and a2 = Pyro or Hydro
    private boolean vaporize(Element a1, Element a2) {
        return 
            ((a1.getElement().equals("Hydro") || a1.getElement().equals("Pyro"))
            && (a2.getElement().equals("Pyro") || a2.getElement().equals("Hydro")));
    }

    //EFFECTS: returns true if a1 = Hydro or Pyro or Electro and a2 = Hydro or Pyro or Electro
    private boolean overloadedOrEC(Element a1, Element a2) {
        return 
            (a1.getElement().equals("Electro") 
            || a1.getElement().equals("Pyro") 
            || a1.getElement().equals("Hydro"))
            && (a2.getElement().equals("Pyro") 
            || a2.getElement().equals("Electro")  
            || a2.getElement().equals("Hydro"));
    }

    //EFFECTS: returns true if a1 = Dendro or Pyro and a2 = Dendro or Pyro
    private boolean burning(Element a1, Element a2) {
        return 
            ((a1.getElement().equals("Dendro") || a1.getElement().equals("Pyro"))
            && (a2.getElement().equals("Pyro") || a2.getElement().equals("Dendro")));
    }

    //EFFECTS: returns true if a1 = Hydro or Dendro and a2 = Hydro or Pyro or Electro or
    //if a1 = Electro or Dendro and a2 = Dendro or Electro
    private boolean bloomOrQuicken(Element a1, Element a2) {
        return 
            ((a1.getElement().equals("Hydro") || a1.getElement().equals("Dendro"))
            && (a2.getElement().equals("Dendro") || a2.getElement().equals("Hydro")))  
            || ((a1.getElement().equals("Electro") || a1.getElement().equals("Dendro"))
            && (a2.getElement().equals("Dendro") || a2.getElement().equals("Electro")));
    }

    //EFFECTS: returns true if a1 and a2 or not Geo or Dendro and at least one of a1 or a2
    // is Anemo
    private boolean swirl(Element a1, Element a2) {
        return 
            (a1.getElement().equals("Anemo") || a2.getElement().equals("Anemo")) 
            && ((!a1.getElement().equals("Dendro") && !a1.getElement().equals("Geo"))
            && (!a2.getElement().equals("Geo")  && !a2.getElement().equals("Dendro")));
    }

    //EFFECTS: returns true if a1 and a2 or not Cryo or Dendro and at least one of a1 or a2
    // is Geo
    private boolean crystallize(Element a1, Element a2) {
        return
            (a1.getElement().equals("Geo")  || a2.getElement().equals("Geo"))
            && ((!a1.getElement().equals("Cryo") && !a1.getElement().equals("Dendro")
            && !a1.getElement().equals("Anemo")))
            && (!a2.getElement().equals("Dendro") && !a2.getElement().equals("Cryo")
            && !a2.getElement().equals("Anemo"));
    }

    //getters

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: returns the battlelog of abilties casted and the damage it dealt
    public ArrayList<String> getLog() {
        return battleLog;
    }

}
