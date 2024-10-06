package model;

import java.util.ArrayList;

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

    //getters

    public ArrayList<Character> getTeam() {
        return team;
    } 

}
