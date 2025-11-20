package persistence;

import model.TeamComp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

// code referenced from JsonSerializationDemo
// Represents a writer that writes JSON representation of TeamComp to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of TeamComp to file
    public void write(TeamComp team) { 
        JSONObject json = team.toJson(); 
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of battleLog to file
    public void writeBattleLog(List<String> battleLog) { 
        JSONArray jsonArray = new JSONArray();

        for (String action : battleLog) {
            jsonArray.put(action);
        }
        saveToFile(jsonArray.toString(TAB));
    }

    public void writeEnemyHP(int hp) {
        saveToFile(Integer.toString(hp));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

