package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

// Type of data that can be saved (as JSON file)
public interface Savable {
    // EFFECTS: convert the object to JSONObject
    public JSONObject toJson();
}
