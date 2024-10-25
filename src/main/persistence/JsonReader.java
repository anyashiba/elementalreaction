package persistence;

import model.Element;
import model.TeamComp;
import model.Character;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// code referenced from JsonSerializationDemo
// Represents a reader that reads JSON files of TeamComp
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TeamComp from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TeamComp read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeamComp(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TeamComp from JSON object and returns it
    private TeamComp parseTeamComp(JSONObject jsonObject) {
        TeamComp team = new TeamComp();
        addCharacters(team, jsonObject);
        return team;
    }

    // MODIFIES: TeamComp
    // EFFECTS: parses characters from JSON object and adds them to TeamComp
    private void addCharacters(TeamComp team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("team");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            addCharacter(team, nextCharacter);
        }
    }

    // EFFECTS: parses an Element object from a JSON object and returns it
    private Element parseElement(JSONObject json) {
        String element = json.getString("element");
        String elementName = json.getString("name");
       
        return new Element(element, elementName);
    }

    // MODIFIES: team
    // EFFECTS: parses character from JSON object and adds it to the team
    private void addCharacter(TeamComp team, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String element = jsonObject.getString("element");

        Element eskill = parseElement(jsonObject.getJSONObject("eskill"));
        Element ult = parseElement(jsonObject.getJSONObject("ult"));

        Character character = new Character(name, element, eskill, ult);
       
        team.addCharacter(character);
    }

}
