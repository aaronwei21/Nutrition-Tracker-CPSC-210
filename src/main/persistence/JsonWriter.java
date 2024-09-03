package persistence;

import model.Goals;
import model.LogList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Writes class information to a json file
// design modelled after JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private String destination;
    private PrintWriter writer;

    // EFFECTS: sets destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // EFFECTS: writes loglist to json object
    public JSONObject writeLogList(LogList loglist) {
        JSONObject json = loglist.logListToJson();
        return json;
    }

    // EFFECTS: writes goals to json object
    public JSONObject writeGoals(Goals goals) {
        JSONObject json = goals.goalsToJson();
        return json;
    }

    // EFFECTS: writes loglist and goals to json file
    public void write(LogList loglist, Goals goals) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(writeLogList(loglist));
        jsonArray.put(writeGoals(goals));
        saveToFile(jsonArray.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes string to json file
    public void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

}
