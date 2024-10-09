package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Character;
import model.Element;
import model.ElementalReaction;
import model.TeamComp;
import model.Enemy;

public class StartGame {
    private Scanner input;
    private TeamComp userTeam;
    private ElementalReaction er;
    private ArrayList<Element> abilitiesCalled;
    private Enemy enemy;
    
    // code taken from BankTellerApp UI
    // EFFECTS: starts game with main menu
    public StartGame() {

        mainMenu();
    }

    // EFFECTS: displays menu options indefinitely until the user quits,
    // does not allow user to proceed further in some options unless they have a team > 0
    private void mainMenu() {
        boolean continueGame = true;
        int command = 0;

        init();
        //TODO create allBaseCharactersAdded();

        while (continueGame) {
            displayMenu();
            command = input.nextInt();

            if (command == 6) {
                continueGame = false;
            } else if (command == 1 && userTeam.getTeam().size() < 1) { 
                System.out.println("Please add characters to your team first!");
            } else if (command == 5 && userTeam.getTeam().size() < 1) { 
                System.out.println("Please add characters to your team first!");
            } else {
                doNext(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes all necessary classes and the scanner object to be used by the user
    private void init() {
        userTeam = new TeamComp();
        er = new ElementalReaction();
        abilitiesCalled = new ArrayList<Element>();
        enemy = new Enemy();
        input = new Scanner(System.in);

        //TODO REMOVE LATER! THIS IS ONLY FOR TESTING
        Element testSkill = new Element("Dendro", "Canopy Hunter: Riding High");
        Element testUlt = new Element("Dendro", "Hail to the Almighty Dragonlord");
        Character testCharacter = new Character("Kinich", "Dendro", testSkill, testUlt);

        Element e1 = new Element("Hydro", "O Tears, I Shall Repay");
        Element e2 = new Element("Hydro", "O Tides, I Have Returned");
        Character testCharacter2 = new Character("Neuvillette", "Hydro", e1, e2);
        
        userTeam.addCharacter(testCharacter);
        userTeam.addCharacter(testCharacter2);
    }

    // EFFECTS: prints out the first user menu
    private void displayMenu() {
        System.out.println("Please select an option by typing the number");
        System.out.println("1: Fight Enemy");
        System.out.println("2: Check Team");
        System.out.println("3: Check Battlelog");
        System.out.println("4: Make and add new character");
        System.out.println("5: Check reactions your team can do");
        System.out.println("6: Quit");
    }

    // EFFECTS: processes user input and if the input is invalid, tells user
    private void doNext(int command) {
        if (command == 1) {
            fightEnemy();
        } else if (command == 2) {
            checkTeam();
        } else if (command == 3) {
            checkBattleLog();
        } else if (command == 4) {
            makeNewCharacter();
        } else if (command == 5) {
            checkReactions();
        } else {
            System.out.println("Input not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to pick a character, pick the ability they want to use,
    // pick a second character, pick second ability, and then damages the enemy,
    // based on the combinations of abilties they called. If the enemy dies or the user
    // quits, the user goes back to the main menu
    private void fightEnemy() {
        boolean continueGame = true;
        int command = 0;

        while (continueGame) {

            fightMenu();
            command = input.nextInt();
            
            if (command == userTeam.getTeam().size() + 1) {

                resetAbilitiesCalled();
                continueGame = false;

            } else if (command <= userTeam.getTeam().size() && command > 0) {

                validCommandForCharacter(command);

            } else {
                System.out.println("invalid input");
            }

            if (enemy.getHP() <= 0) {
                enemyDefeated();
                resetAbilitiesCalled();
                continueGame = false;

            } else {
                System.out.println("Enemy has " + enemy.getHP() + " HP left");
            }
        }
    }

    // EFFECTS: displays fight menu
    private void fightMenu() {
        System.out.println("Please select which character you would like to attack");
        int count = 0;

        for (Character character : userTeam.getTeam()) {
            count++;
            System.out.println(count + ": " + character.getName());
        }

        System.out.println((count + 1) + ": " + "back"); 
    }

    // MODIFIES: this
    // EFFECTS: prints the enemy has died and resets enemy's hp
    private void enemyDefeated() {
        System.out.println("Enemy has died!");
        enemy.resetHP();
    }

    // EFFECTS: selects the character based on command given
    private void validCommandForCharacter(int command) {
        Character selectedCharacter = userTeam.getTeam().get(command - 1);
        whichAbility(selectedCharacter);
    }

    // MODIFIES: this
    // EFFECTS: if abilitiesCalled has only one ability, takes user back
    // to character selection screen to select another character and ability;
    // if abilitesCalled has two abilities in it, damages the enemy
    private void whichAbility(Character character) {
        Boolean continueGame = true;
        int command = 0;

        while (continueGame) {

            displayAbilities(character);

            command = input.nextInt();

            if (command == 3) {
                resetAbilitiesCalled();
                continueGame = false;

            } else if (command > 3) {
                System.out.println("invalid input");
                resetAbilitiesCalled();
                continueGame = false;

            } else {
                selectAbility(command, character);
                System.out.println("ability has been called");

                if (abilitiesCalled.size() == 1) {
                    continueGame = false;
                } else {
                    doDamage();
                    continueGame = false;
                }
            }
        }
    }

    // EFFECTS: displays character's abilities they can call
    private void displayAbilities(Character character) {
        System.out.println("Select the ability you want to use");
        System.out.println(character.attributes());
        System.out.println("3: Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes what abiltiy the user wants to use and adds it to
    // abilitiesCalled
    private void selectAbility(int command, Character character) {
        if (command == 1) {
            abilitiesCalled.add(character.getESkill());
        } else if (command == 2) {
            abilitiesCalled.add(character.getUlt());
        }

    }

    // MODIFIES: this
    // EFFECTS: does damage enemy and resets abilties called so user
    // can call more abilities
    private void doDamage() {
        Element ability1 = abilitiesCalled.get(0);
        Element ability2 = abilitiesCalled.get(1);
        int dmg = er.react(ability1, ability2);

        enemy.damage(dmg);
        System.out.println("you have done " + dmg + " damage to the enemy!");

        resetAbilitiesCalled();
    }

    // MODIFIES: this
    // EFFECTS: resets list of abiltiesCalled
    private void resetAbilitiesCalled() {
        abilitiesCalled.clear();
    }

    // EFFECTS: returns list of characters' names on user's team
    private void checkTeam() {
        ArrayList<String> team = new ArrayList<String>();

        for (Character character : userTeam.getTeam()) {
            team.add(character.getName());
        }

        System.out.println(team);
    }

    // EFFECTS: returns the battle log of what abiltiies were called and how much damage
    // was done
    private void checkBattleLog() {
        System.out.println(er.getLog());
    }

    // MODIFIES: this
    // EFFECTS: prompts user to fill in character attributes to create a new character for their team

    private void makeNewCharacter() {
        String name = null;
        int element = 0;
        String eskillName = null;
        String ultName = null;
        String chooseElement = null;

        createCharacterLoop(name, element, eskillName, ultName, chooseElement);
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs for character attributes and adds character to team
    // if all inputs were valid, if any input was invalid, end character creation 
    private void createCharacterLoop(String name, int element, String eskillName, String ultName, String chooseElement) {
        boolean continueCreate = true;
        while (continueCreate) {
            if (name == null) {
                System.out.println("What would you like your character's name to be?");
                name = input.next();
            } else if (element == 0) {
                elementDisplay();
                element = input.nextInt();
                chooseElement = chooseElement(element);
                continueCreate = elementIsNull(chooseElement);
                input.nextLine();
            } else if (eskillName == null) {
                System.out.println("What would you like their skill to be called?");
                eskillName = input.next();
            } else if (ultName == null) {
                System.out.println("What would you like their ultimate ability to be called?");
                ultName = input.next();
            } else {
                characterCreated(chooseElement, eskillName, ultName, name);
                continueCreate = false;
            }
        }
    }

    // EFFECTS: returns if the input user put for element type was valid or not
    private boolean elementIsNull(String str) {
        return (str != null);
    }

    // MODIFIES: this
    // EFFECTS: instantiates new character with user's given name, element, skill, and ult,
    // adds character to team automatically
    private void characterCreated(String element, String skillName, String ultName, String name) {
        Element newSkill = new Element(element, skillName);
        Element newUlt = new Element(element, ultName);
        Character newCharacter = new Character(name, element, newSkill, newUlt);
        userTeam.addCharacter(newCharacter);
        System.out.println("Your character " + name + " has been made!");
    }

    // EFFECTS: displays what elements user can choose
    private void elementDisplay() {
        System.out.println("What would you like your character's element to be?");
        System.out.println("1: Dendro, 2: Electro, 3: Geo, 4: Cryo, 5: Pyro, 6: Anemo, 7: Hydro");
    }

    // EFFECTS: processes what element user chose based on input, if input was invalid
    // element is null 
    private String chooseElement(int element) {
        if (element == 1) {
            return "Dendro";
        } else if (element == 2) {
            return "Electro";
        } else if (element == 3) {
            return "Geo";
        } else if (element == 4) {
            return "Cryo";
        } else if (element == 5) {
            return "Pyro";
        }  else if (element == 6) {
            return "Anemo";
        } else if (element == 7) {
            return "Hydro";
        } else {
            System.out.println("invalid input, please try again.");
            return null;
        }
    }

    // EFFECTS: prints out what reactions the user can do with the characters in their team
    private void checkReactions() {
        System.out.println(userTeam.viewTeamReactions());
    }

}
