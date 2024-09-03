package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// This class represents a meal log for a given day. Stores information on meals eaten, the day of the log,
// and total consumption.
public class Log {
    private ArrayList<Meal> mealList;
    private int day;
    private Meal total;
    private EventLog eventLog;

    // REQUIRES: day >= 0
    // EFFECTS: instantiates meaList, sets value for day
    public Log(int day) {
        this.day = day;
        mealList = new ArrayList<>();
    }

    // MODIFIES: mealList
    // EFFECT: Adds a new meal to mealList
    public void addMeal(Meal newMeal, boolean loaded) {
        mealList.add(newMeal);
        if (!loaded) {
            eventLog.getInstance().logEvent(new Event("- " + newMeal.getMealName() + " added to log of day " + day));
        }
    }

    // REQUIRES: 1 <= mode <= 4
    // EFFECTS: returns the total quantity consumed for some nutrient/field of meal
    public int sumNutrients(int mode) {
        int sum = 0;

        for (Meal meal: mealList) {
            switch (mode) {
                case 1:
                    sum += meal.getCalories();
                    break;
                case 2:
                    sum += meal.getSugar();
                    break;
                case 3:
                    sum += meal.getSodium();
                    break;
                default:
                    sum += meal.getProtein();
                    break;
            }
        }

        return sum;
    }

    // MODIFIES: total
    // EFFECTS: defines total as a meal with field values equal to the sum of those in mealList
    public void createTotal() {
        int totalCalories = sumNutrients(1);
        int totalSugar = sumNutrients(2);
        int totalSodium = sumNutrients(3);
        int totalProtein = sumNutrients(4);

        total = new Meal("Total", totalCalories, totalSugar, totalSodium, totalProtein);
    }

    public Meal getTotal() {
        return total;
    }

    public int getDay() {
        return day;
    }

    public ArrayList<Meal> getMealList() {
        return mealList;
    }

    // EFFECTS: returns representation of this as json object
    public JSONObject logToJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mealList", mealListToJson());
        jsonObject.put("day", day);
        jsonObject.put("total", total.mealToJson());
        return jsonObject;
    }

    // EFFECT: returns representation of mealList as json array
    public JSONArray mealListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Meal m: mealList) {
            jsonArray.put(m.mealToJson());
        }
        return jsonArray;
    }

}
