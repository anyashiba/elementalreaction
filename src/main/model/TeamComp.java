package model;

import java.util.ArrayList;

// TeamComp class has a list of characters that represents your team to use to fight
public class TeamComp {
    private ArrayList<Character> team;

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: constructs an empty team comp
    public TeamComp() {
        this.team = new ArrayList<Character>();
    }

    //REQUIRES: 
    //MODIFIES: this
    //EFFECTS: adds character to the team 
    public void addCharacter(Character character) {
        team.add(character);
    }

    //REQUIRES: 
    //MODIFIES: 
    //EFFECTS: returns the a list of the reactions that you can do with the characters in your team
    public ArrayList<String> viewTeamReactions() {
        ArrayList<String> reactions = new ArrayList<String>();
        ArrayList<String> elements = new ArrayList<String>();
        
        for (Character character : team) {
            elements.add(character.getElement());
        }
        
        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                ArrayList<String> reactionCombo = new ArrayList<>();
                reactionCombo.add(elements.get(i));
                reactionCombo.add(elements.get(j));

                checkAllReactions(reactionCombo, reactions);
            }
        }
    
        return reactions;
    }

    //HELPER FUNCTIONS FOR viewTeamReactions()

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: checks if each element and another element pair in the list can react with one another
    // and adds it to the reactions list only if it has not been added before
    @SuppressWarnings("methodlength")
    private void checkAllReactions(ArrayList<String> elements, ArrayList<String> reactions) {
        if (bloom(elements, reactions)) {
            reactions.add("Bloom");
        }
        if (burning(elements, reactions)) {
            reactions.add("Burning");
        }
        if (quicken(elements, reactions)) {
            reactions.add("Quicken");
        }
        if (vaporize(elements, reactions)) {
            reactions.add("Vaporize");
        }
        if (overloaded(elements, reactions)) {
            reactions.add("Overloaded");
        }
        if (swirl(elements, reactions)) {
            reactions.add("Swirl");
        }
        if (frozen(elements, reactions)) {
            reactions.add("Frozen");
        }
        if (electrocharged(elements, reactions)) {
            reactions.add("Electrocharged");
        }
        if (melt(elements, reactions)) {
            reactions.add("Melt");
        }
        if (crystallize(elements, reactions)) {
            reactions.add("Crystallize");
        }
        if (superconduct(elements, reactions)) {
            reactions.add("Superconduct");
        }
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if bloom reaction can occur
    private Boolean bloom(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Dendro") && elements.contains("Hydro") && !reactions.contains("Bloom"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if burning reaction can occur
    private Boolean burning(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Dendro") && elements.contains("Pyro") && !reactions.contains("Burning"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if quicken reaction can occur
    private Boolean quicken(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Dendro") && elements.contains("Electro") && !reactions.contains("Quicken"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if vaporize reaction can occur
    private Boolean vaporize(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Hydro") && elements.contains("Pyro") && !reactions.contains("Vaporize"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if overloaded reaction can occur
    private Boolean overloaded(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Pyro") && elements.contains("Electro") && !reactions.contains("Overloaded"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if frozen reaction can occur
    private Boolean frozen(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Hydro") && elements.contains("Cryo") && !reactions.contains("Frozen"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if superconduct reaction can occur
    private Boolean superconduct(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Electro") && elements.contains("Cryo") && !reactions.contains("Superconduct"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if electrocharged reaction can occur
    private Boolean electrocharged(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Hydro") && elements.contains("Electro") && !reactions.contains("Electrocharged"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if melt reaction can occur
    private Boolean melt(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Cryo") && elements.contains("Pyro") && !reactions.contains("Melt"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if crystallize reaction can occur
    private Boolean crystallize(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            (elements.contains("Geo") 
            && (elements.contains("Electro") 
            || elements.contains("Hydro") 
            || elements.contains("Pyro"))
            && !reactions.contains("Crystallize"));
    }

    //REQUIRES:
    //MODIFIES: 
    //EFFECTS: returns true if swirl reaction can occur
    private Boolean swirl(ArrayList<String> elements, ArrayList<String> reactions) {
        return 
            elements.contains("Anemo")
            && (elements.contains("Electro")
            || elements.contains("Hydro") 
            || elements.contains("Pyro") 
            || elements.contains("Cryo"))
            && !reactions.contains("Swirl"); 
    }

    //getters

    public ArrayList<Character> getTeam() {
        return team;
    }

}