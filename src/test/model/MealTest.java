package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MealTest {
    Meal testMealA = new Meal("Spaghetti", 1000, 25, 15, 20);
    Meal testMealB = new Meal("Steak", 800, 6, 15, 40);

    @Test
    void testMeal() {
        assertEquals("Spaghetti",testMealA.getMealName());
        assertEquals(1000, testMealA.getCalories());
        assertEquals(25, testMealA.getSugar());
        assertEquals(15, testMealA.getSodium());
        assertEquals(20, testMealA.getProtein());
        assertEquals("Steak", testMealB.getMealName());
        assertEquals(800, testMealB.getCalories());
        assertEquals(6, testMealB.getSugar());
        assertEquals(15, testMealB.getSodium());
        assertEquals(40, testMealB.getProtein());
    }
}
