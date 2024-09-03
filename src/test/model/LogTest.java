package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogTest {
    Log testLog;
    Meal testMealA;
    Meal testMealB;

    @BeforeEach
    void beforeEach() {
        testLog = new Log(1);
        testMealA = new Meal("Spaghetti", 1000, 25, 15, 20);
        testMealB = new Meal("Steak", 800, 6, 15, 40);
    }

    @Test
    void testLog() {
        assertEquals(1, testLog.getDay());
        assertEquals(0, testLog.getMealList().size());
    }

    @Test
    void testAddMeal() {
        testLog.addMeal(testMealA, false);
        testLog.addMeal(testMealB, false);
        assertEquals(2, testLog.getMealList().size());
        assertEquals(testMealA, testLog.getMealList().get(0));
        assertEquals(testMealB, testLog.getMealList().get(1));
    }

    @Test
    void testSumNutrientsOneMeal() {
        testLog.addMeal(testMealA, false);
        assertEquals(testMealA.getCalories(), testLog.sumNutrients(1));
        assertEquals(testMealA.getSugar(), testLog.sumNutrients(2));
        assertEquals(testMealA.getSodium(), testLog.sumNutrients(3));
        assertEquals(testMealA.getProtein(), testLog.sumNutrients(4));
    }

    @Test
    void testSumNutrientsMultipleMeals() {
        testLog.addMeal(testMealA, false);
        testLog.addMeal(testMealB, false);
        assertEquals(testMealA.getCalories() + testMealB.getCalories(), testLog.sumNutrients(1));
        assertEquals(testMealA.getSugar() + testMealB.getSugar(), testLog.sumNutrients(2));
        assertEquals(testMealA.getSodium() + testMealB.getSodium(), testLog.sumNutrients(3));
        assertEquals(testMealA.getProtein() + testMealB.getProtein(), testLog.sumNutrients(4));
    }

    @Test
    void testCreateTotalOneMeal() {
        testLog.addMeal(testMealA, false);
        testLog.createTotal();
        assertEquals("Total", testLog.getTotal().getMealName());
        assertEquals(testMealA.getCalories(), testLog.getTotal().getCalories());
        assertEquals(testMealA.getSugar(), testLog.getTotal().getSugar());
        assertEquals(testMealA.getSodium(), testLog.getTotal().getSodium());
        assertEquals(testMealA.getProtein(), testLog.getTotal().getProtein());
    }

    @Test
    void testCreateTotalMultipleMeals() {
        testLog.addMeal(testMealA, false);
        testLog.addMeal(testMealB, false);
        testLog.createTotal();
        assertEquals("Total", testLog.getTotal().getMealName());
        assertEquals(testMealA.getCalories() + testMealB.getCalories(), testLog.getTotal().getCalories());
        assertEquals(testMealA.getSugar() + testMealB.getSugar(), testLog.getTotal().getSugar());
        assertEquals(testMealA.getSodium() + testMealB.getSodium(), testLog.getTotal().getSodium());
        assertEquals(testMealA.getProtein() + testMealB.getProtein(), testLog.getTotal().getProtein());
    }
}