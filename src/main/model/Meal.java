package model;

import org.json.JSONObject;

// This class represents a meal along with its nutrition facts
public class Meal {
    private String mealName;
    private int calories;
    private int sugar;
    private int sodium;
    private int protein;

    // REQUIRES: parameter >= 0
    // EFFECTS: sets initial values of fields
    public Meal(String mealName, Integer calories, Integer sugar, Integer sodium, Integer protein) {
        this.mealName = mealName;
        this.calories = calories;
        this.sugar = sugar;
        this.sodium = sodium;
        this.protein = protein;
    }

    public String getMealName() {
        return mealName;
    }

    public int getCalories() {
        return calories;
    }

    public int getSugar() {
        return sugar;
    }

    public int getSodium() {
        return sodium;
    }

    public int getProtein() {
        return protein;
    }

    // returns representation of this as json object
    public JSONObject mealToJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mealName", mealName);
        jsonObject.put("calories", calories);
        jsonObject.put("sugar", sugar);
        jsonObject.put("sodium", sodium);
        jsonObject.put("protein", protein);
        return jsonObject;
    }

}
