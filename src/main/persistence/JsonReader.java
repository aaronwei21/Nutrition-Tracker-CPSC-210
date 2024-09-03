package persistence;

import model.Goals;
import model.Log;
import model.LogList;
import model.Meal;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Reads class information from a json file
// design modelled after JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: sets source file
    public JsonReader(String source) {
        this.source = source;
    }

    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads LogList object from json file and returns it
    public LogList readLogList() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        return parseLogList(jsonObject);
    }

    // EFFECTS: parses through LogList in json file and returns it
    public LogList parseLogList(JSONObject jsonObject) {
        LogList loglist = new LogList();
        JSONArray jsonArray = jsonObject.getJSONArray("listOfLog");
        for (Object log : jsonArray) {
            JSONObject nextLog = (JSONObject) log;
            loglist.addLog(parseLog(nextLog), true);
        }
        return loglist;
    }

    // EFFECTS: parses through Log in json file and returns it
    public Log parseLog(JSONObject jsonObject) {
        int day = jsonObject.getInt("day");
        Log log = new Log(day);
        JSONArray jsonMealList = jsonObject.getJSONArray("mealList");

        for (Object mealList : jsonMealList) {
            JSONObject nextMeal = (JSONObject) mealList;
            log.addMeal(parseMeal(nextMeal), true);
        }
        log.createTotal();

        return log;
    }

    // EFFECTS: parses through meal in json file and returns it
    public Meal parseMeal(JSONObject jsonObject) {
        String mealName = jsonObject.getString("mealName");
        int calories = jsonObject.getInt("calories");
        int sugar = jsonObject.getInt("sugar");
        int sodium = jsonObject.getInt("sodium");
        int protein = jsonObject.getInt("protein");
        Meal meal = new Meal(mealName, calories, sugar, sodium, protein);
        return meal;
    }

    // EFFECTS: parses through goal in json file and returns it
    public Goals readGoals() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONObject jsonObject = jsonArray.getJSONObject(1);
        return parseRead(jsonObject);
    }

    // EFFECTS: parses through goal's fields in json file, adds
    //          them to goal and it
    private Goals parseRead(JSONObject jsonObject) {
        Goals goal = new Goals();
        int calories = jsonObject.getInt("calorieGoal");
        int sugar = jsonObject.getInt("sugarGoal");
        int sodium = jsonObject.getInt("sodiumGoal");
        int protein = jsonObject.getInt("proteinGoal");

        goal.setCalorieGoal(calories);
        goal.setSugarGoal(sugar);
        goal.setSodiumGoal(sodium);
        goal.setProteinGoal(protein);

        return goal;
    }

}
