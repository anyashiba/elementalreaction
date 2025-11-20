package ui;

import javax.swing.*;

import model.Element;
import model.Character;
import model.TeamComp;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// class for graphical user interface
public class StartGameGUI extends JPanel {
    private static final String JSON_STORE = "./data/workroom.json";
    private TeamComp userTeam;
    private JLabel imageAsLabel;
    private JTextField inputUser;
    private String name;
    private String element;
    private String ability;
    private String ult;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    
    // code referenced from RobustTrafficLights
    public StartGameGUI(TeamComp userTeam) {
        this.userTeam = userTeam;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        
        setLayout(new BorderLayout());

        JPanel mainMenu = createMainMenu();
        add(mainMenu, BorderLayout.CENTER);
    }

    // EFFECTS: creates the main menu
    private JPanel createMainMenu() {
        JPanel mainMenuPanel = new JPanel(new GridLayout(3, 2));

        // imageAsLabel = new JLabel(new ImageIcon("./images/helloUser.png"), SwingConstants.CENTER);
        // add(imageAsLabel, BorderLayout.NORTH);

        ImageIcon imageIcon = loadImage("./images/helloUser.png");
        imageAsLabel = new JLabel(imageIcon);
        add(imageAsLabel, BorderLayout.NORTH);

        addButtons(mainMenuPanel);
    
        return mainMenuPanel;
    }

    // EFFECTS: returns an error message dialogue if the team is empty, if team not empty switch to next panel
    private void teamEmpty(JPanel panel) {
        if (!userTeam.getTeam().isEmpty()) {
            switchToPanel(panel);
        } else {
            JOptionPane.showMessageDialog(this, "Please add characters to your team");
        }
    }

    // EFFECTS: add the buttons to main menu
    private void addButtons(JPanel menu) {
        JButton checkTeamButton = new JButton("Check Team");
        checkTeamButton.addActionListener(e -> teamEmpty(checkTeamMenu()));
        menu.add(checkTeamButton);
    
        JButton addCharacterButton = new JButton("Add Character");
        addCharacterButton.addActionListener(e -> switchToPanel(setName()));
        menu.add(addCharacterButton);
    
        JButton checkReactionsButton = new JButton("Check reactions your team can do");
        checkReactionsButton.addActionListener(e -> teamEmpty(checkReactionsMenu()));
        menu.add(checkReactionsButton);
    
        JButton attributesButton = new JButton("Check character attributes");
        attributesButton.addActionListener(e -> teamEmpty(checkTeamAttributesMenu()));
        menu.add(attributesButton);

        JButton saveDataButton = createButton("Save Data", e -> handleSave());
        menu.add(saveDataButton);

        JButton loadDataButton = createButton("Load Data", e -> handleLoad());
        menu.add(loadDataButton);
    }

    // EFFECTS: loads in data
    private void handleLoad() {
        JOptionPane.showMessageDialog(this, "Data loaded in!");
        loadTeamComp();
    }

    // EFFECTS: saves data
    private void handleSave() {
        JOptionPane.showMessageDialog(this, "Data Saved!");
        saveTeamComp();
    }

    // EFFECTS: showcases a panel with the userTeam's attributes
    private JPanel checkTeamAttributesMenu() {
        JPanel checkTeamAttributesPanel = new JPanel();
        checkTeamAttributesPanel.setLayout(new BoxLayout(checkTeamAttributesPanel, BoxLayout.Y_AXIS));

        JLabel teamLabel = new JLabel("Attributes of your team:");
        JTextArea teamAttributes = new JTextArea();

        ImageIcon imageIcon = loadImage("./images/SaveTeam.png");
        imageAsLabel = new JLabel(imageIcon);
        checkTeamAttributesPanel.add(imageAsLabel, BorderLayout.NORTH);
    
        StringBuilder reactionsToText = new StringBuilder();
        for (Character character : userTeam.getTeam()) {
            reactionsToText.append(character.attributes())
                    .append("\n");
        }
        teamAttributes.setText(reactionsToText.toString());
    
        checkTeamAttributesPanel.add(teamLabel, BorderLayout.CENTER);
        checkTeamAttributesPanel.add(new JScrollPane(teamAttributes), BorderLayout.CENTER);

        JButton backButton = backButton();
        checkTeamAttributesPanel.add(backButton, BorderLayout.SOUTH);
        
        return checkTeamAttributesPanel;
    }

    // EFFECTS: panel and textfield for user to input their new character's name
    private JPanel setName() {
        JPanel namePanel = createVerticalPanel();
    
        imageAsLabel = new JLabel(loadImage("./images/CreateCharacter.png"));
        namePanel.add(imageAsLabel);
    
        namePanel.add(createCenteredLabel("What would you like your character's name to be?"));
        inputUser = createTextField(10, new Dimension(20, 30));
        namePanel.add(inputUser);
    
        JButton submitButton = createButton("Submit", e -> handleNameSubmit());
        namePanel.add(wrapInPanel(submitButton));
    
        return namePanel;
    }

    // EFFECTS: creates a JPanel with vertical stack
    private JPanel createVerticalPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    // EFFECTS: creates a JLabel with centered alignment
    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        return label;
    }

    // EFFECTS: creates a JTextField with a specified column count and size
    private JTextField createTextField(int columns, Dimension size) {
        JTextField textField = new JTextField(columns);
        textField.setPreferredSize(size);
        return textField;
    }

    // EFFECTS: creates a JButton with the associated action
    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    // EFFECTS: adds a component to the JPanel
    private JPanel wrapInPanel(JComponent component) {
        JPanel panel = new JPanel();
        panel.add(component);
        return panel;
    }

    // EFFECTS: handles the submit button action
    private void handleNameSubmit() {
        String inputName = inputUser.getText().trim();
        if (inputName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid name!");
        } else {
            name = inputName;
            JOptionPane.showMessageDialog(this, "Great name, " + name + "!");
            switchToPanel(setElement());
        }
    }
    
    // EFFECTS: panel and options for user to pick their new character's element
    private JPanel setElement() {
        JPanel setElementPanel = new JPanel(new BorderLayout());
    
        ImageIcon imageIcon = loadImage("./images/whatElement.png");
        imageAsLabel = new JLabel(imageIcon);
        setElementPanel.add(imageAsLabel, BorderLayout.NORTH);
    
        JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
        addElementButtons(buttonPanel); 
    
        setElementPanel.add(buttonPanel, BorderLayout.CENTER);
    
        return setElementPanel;
    }

    // EFFECTS: adds the button actions for setElement
    @SuppressWarnings("methodlength")
    private void addElementButtons(JPanel panel) {
        JButton dendro = new JButton("Dendro");
        dendro.addActionListener(e -> {
            element = "Dendro";
            switchToPanel(setAbility());
        });
        panel.add(dendro);
    
        JButton hydro = new JButton("Hydro");
        hydro.addActionListener(e -> {
            element = "Hydro";
            switchToPanel(setAbility());
        });
        panel.add(hydro);
    
        JButton electro = new JButton("Electro");
        electro.addActionListener(e -> {
            element = "Electro";
            switchToPanel(setAbility());
        });
        panel.add(electro);
    
        JButton anemo = new JButton("Anemo");
        anemo.addActionListener(e -> {
            element = "Anemo";
            switchToPanel(setAbility());
        });
        panel.add(anemo);
    
        JButton pyro = new JButton("Pyro");
        pyro.addActionListener(e -> {
            element = "Pyro";
            switchToPanel(setAbility());
        });
        panel.add(pyro);
    
        JButton cryo = new JButton("Cryo");
        cryo.addActionListener(e -> {
            element = "Cryo";
            switchToPanel(setAbility());
        });
        panel.add(cryo);

        JButton geo = new JButton("Geo");
        geo.addActionListener(e -> {
            element = "Geo"; 
            switchToPanel(setAbility());
        });
        panel.add(geo);
    }

    // EFFECTS: panel and textfield for user to create their new character's ability
    private JPanel setAbility() {
        JPanel abilityPanel = createVerticalPanel();

        imageAsLabel = new JLabel(loadImage("./images/ability1.png"));
        abilityPanel.add(imageAsLabel);

        abilityPanel.add(createCenteredLabel("What would you like the name of your skill to be?"));
        inputUser = createTextField(10, new Dimension(20, 30));
        abilityPanel.add(inputUser);

        JButton submitButton = createButton("Submit", e -> handleAbilitySubmit());
        abilityPanel.add(wrapInPanel(submitButton));

        return abilityPanel;
    }

    // EFFECTS: handles the user input to be the character's new ability
    private void handleAbilitySubmit() {
        String inputAbility = inputUser.getText().trim();
        if (inputAbility.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid name!");
        } else {
            ability = inputAbility;
            JOptionPane.showMessageDialog(this, ability + ", what a cool skill!");
            switchToPanel(setUltimate());
        }
    }

    // EFFECTS: panel and textfield for user to create their new character's ultimate
    private JPanel setUltimate() {
        JPanel ultimatePanel = createVerticalPanel();

        imageAsLabel = new JLabel(loadImage("./images/ability2.png"));
        ultimatePanel.add(imageAsLabel);

        ultimatePanel.add(createCenteredLabel("What would you like the name of your ultimate ability to be?"));
        inputUser = createTextField(10, new Dimension(20, 30));
        ultimatePanel.add(inputUser);

        JButton submitButton = createButton("Submit", e -> handleUltimateSubmit());
        ultimatePanel.add(wrapInPanel(submitButton));
    
        return ultimatePanel;
    }

    // EFFECTS: handles the user input to be the character's new ultimate
    private void handleUltimateSubmit() {
        String inputUltimate = inputUser.getText().trim();
        if (inputUltimate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid name!");
        } else {
            ult = inputUltimate;
            createCharacter();
            JOptionPane.showMessageDialog(this, name + " has been added to your team!");
            switchToPanel(createMainMenu());
        }
    }

    // EFFECTS: creates new character with name, element, ability, and ultimate
    public void createCharacter() {
        Element newAbility = new Element(element, ability);
        Element newUlt = new Element(element, ult);
        Character newChar = new Character(name, element, newAbility, newUlt);
        userTeam.addCharacter(newChar);
    }
    
    // EFFECTS: loads image
    private ImageIcon loadImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getIconWidth() == -1) {
            return null;
        }
        return icon;
    }
    
    // EFFECTS: creates panel to check your team
    private JPanel checkTeamMenu() {
        JPanel checkTeamPanel = new JPanel();
        checkTeamPanel.setLayout(new BoxLayout(checkTeamPanel, BoxLayout.Y_AXIS));

        JLabel teamLabel = new JLabel("Your team:");
        JTextArea teamDetails = new JTextArea();

        ImageIcon imageIcon = loadImage("./images/CheckReactions.png");
        imageAsLabel = new JLabel(imageIcon);
        checkTeamPanel.add(imageAsLabel, BorderLayout.NORTH);
    
        StringBuilder teamToText = new StringBuilder();
        for (Character character : userTeam.checkTeam()) {
            teamToText.append(character.getName())
                    .append("\n");
        }
        teamDetails.setText(teamToText.toString());
    
        checkTeamPanel.add(teamLabel, BorderLayout.CENTER);
        checkTeamPanel.add(new JScrollPane(teamDetails), BorderLayout.CENTER);

        JButton backButton = backButton();
        checkTeamPanel.add(backButton, BorderLayout.SOUTH);
        
        return checkTeamPanel;
    }

    // EFFECTS: creates panel to check the reactions your team can do
    private JPanel checkReactionsMenu() {
        JPanel checkReactionsPanel = new JPanel(new BorderLayout());

        checkReactionsPanel.add(createImagePanel("./images/LoadTeam.png"), BorderLayout.NORTH);
        checkReactionsPanel.add(createReactionsDisplayPanel(), BorderLayout.CENTER);
        checkReactionsPanel.add(backButton(), BorderLayout.SOUTH);

        return checkReactionsPanel;
    }

    // EFFECTS: creates a panel with an image at the top
    private JPanel createImagePanel(String imagePath) {
        JPanel imagePanel = new JPanel();
        ImageIcon imageIcon = loadImage(imagePath);
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel);
        return imagePanel;
    }

    // EFFECTS: creates a panel displaying team reactions
    private JScrollPane createReactionsDisplayPanel() {
        JPanel reactionsPanel = new JPanel();
        reactionsPanel.setLayout(new BoxLayout(reactionsPanel, BoxLayout.Y_AXIS));

        JLabel teamLabel = new JLabel("Reactions your team can do:");
        reactionsPanel.add(teamLabel);

        JTextArea teamReactions = new JTextArea();
        teamReactions.setEditable(false);
        teamReactions.setText(formatReactionsText(userTeam.viewTeamReactions()));

        return new JScrollPane(teamReactions);
    }

    // EFFECTS: formats the list of reactions into a string
    private String formatReactionsText(ArrayList<String> reactions) {
        StringBuilder reactionsToText = new StringBuilder();
        for (String reaction : reactions) {
            reactionsToText.append(reaction).append("\n");
        }
        return reactionsToText.toString();
    }

    // EFFECTS: removes all content, adds the panel, revalidates and repaints
    private void switchToPanel(JPanel panel) {
        removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    // EFFECTS: creates a back button that goes back to the main menu when pressed
    private JButton backButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> switchToPanel(createMainMenu()));
        return backButton;
    }

    // code referenced from JsonSerializationDemo
    // EFFECTS: saves the workroom to file
    private void saveTeamComp() {
        try {
            jsonWriter.open();
            jsonWriter.write(userTeam);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file");
        }
    }
    
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadTeamComp() {
        try {
            userTeam = jsonReader.read();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from files");
        }
    }

}
