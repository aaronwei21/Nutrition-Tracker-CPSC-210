package model;

import org.json.JSONObject;

// This class stores the nutrition goals of the user
public class Goals {
    private int calorieGoal;
    private int sugarGoal;
    private int sodiumGoal;
    private int proteinGoal;

    // EFFECTS: sets initial values of fields
    public Goals() {
        calorieGoal = -2;
        sugarGoal = -2;
        sodiumGoal = -2;
        proteinGoal = -2;
    }

    public void setCalorieGoal(int calorie) {
        calorieGoal = calorie;
    }

    public void setSugarGoal(int sugar) {
        sugarGoal = sugar;
    }

    public void setSodiumGoal(int sodium) {
        sodiumGoal = sodium;
    }

    public void setProteinGoal(int protein) {
        proteinGoal = protein;
    }

    public int getCalorieGoal() {
        return calorieGoal;
    }

    public int getSugarGoal() {
        return sugarGoal;
    }

    public int getSodiumGoal() {
        return sodiumGoal;
    }

    public int getProteinGoal() {
        return proteinGoal;
    }

    // EFFECTS: returns representation of this as json object
    public JSONObject goalsToJson() {
        JSONObject json = new JSONObject();
        json.put("calorieGoal", calorieGoal);
        json.put("sugarGoal", sugarGoal);
        json.put("sodiumGoal", sodiumGoal);
        json.put("proteinGoal", proteinGoal);
        return json;
    }

}
