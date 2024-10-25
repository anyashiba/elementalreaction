package persistence;

import org.json.JSONObject;

// code referenced from JsonSerializationDemo
// interface for objects converted to JSON
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}