package persistence;

import org.json.JSONObject;

// Type of data that can be saved (as JSON file)
public interface Savable {
    // EFFECTS: convert the object to JSONobject
    public JSONObject toJson();
}
