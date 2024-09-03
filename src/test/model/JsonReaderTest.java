package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMenu() {
        JsonReader reader = new JsonReader("./data/TrackerMenuEmpty.json");
        try {
            Goals goals = reader.readGoals();
            LogList logList = reader.readLogList();
            assertTrue(logList.getListOfLog().isEmpty());
            assertEquals(-2, goals.getCalorieGoal());
            assertEquals(-2, goals.getSugarGoal());
            assertEquals(-2, goals.getSodiumGoal());
            assertEquals(-2, goals.getProteinGoal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFilledMenu() {
        JsonReader reader = new JsonReader("./data/TrackerMenuFilled.json");
        try {
            Goals goals = reader.readGoals();
            LogList logList = reader.readLogList();
            // goals
            assertEquals(2500, goals.getCalorieGoal());
            assertEquals(50, goals.getSugarGoal());
            assertEquals(50, goals.getSodiumGoal());
            assertEquals(120, goals.getProteinGoal());
            // log day 1
            assertEquals(2, logList.getListOfLog().size());
            assertEquals(1, logList.getLog(1).getDay());
            assertEquals("Poutine", logList.getLog(1).getMealList().get(0).getMealName());
            assertEquals(750, logList.getLog(1).getMealList().get(0).getCalories());
            assertEquals(2, logList.getLog(1).getMealList().get(0).getSugar());
            assertEquals(25, logList.getLog(1).getMealList().get(0).getSodium());
            assertEquals(15, logList.getLog(1).getMealList().get(0).getProtein());
            assertEquals("Salad", logList.getLog(1).getMealList().get(1).getMealName());
            assertEquals(750, logList.getLog(1).getMealList().get(1).getCalories());
            assertEquals(16, logList.getLog(1).getMealList().get(1).getSugar());
            assertEquals(1, logList.getLog(1).getMealList().get(1).getSodium());
            assertEquals(13, logList.getLog(1).getMealList().get(1).getProtein());
            assertEquals("Total", logList.getLog(1).getTotal().getMealName());
            assertEquals(1500, logList.getLog(1).getTotal().getCalories());
            assertEquals(18, logList.getLog(1).getTotal().getSugar());
            assertEquals(26, logList.getLog(1).getTotal().getSodium());
            assertEquals(28, logList.getLog(1).getTotal().getProtein());
            // log day 2
            assertEquals(2, logList.getLog(2).getDay());
            assertEquals("Spaghetti", logList.getLog(2).getMealList().get(0).getMealName());
            assertEquals(800, logList.getLog(2).getMealList().get(0).getCalories());
            assertEquals(15, logList.getLog(2).getMealList().get(0).getSugar());
            assertEquals(15, logList.getLog(2).getMealList().get(0).getSodium());
            assertEquals(13, logList.getLog(2).getMealList().get(0).getProtein());
            assertEquals("Total", logList.getLog(2).getTotal().getMealName());
            assertEquals(800, logList.getLog(2).getTotal().getCalories());
            assertEquals(15, logList.getLog(2).getTotal().getSugar());
            assertEquals(15, logList.getLog(2).getTotal().getSodium());
            assertEquals(13, logList.getLog(2).getTotal().getProtein());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
