package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalsTest {
    Goals testGoal;
    @Test
    void testGoals() {
        testGoal = new Goals();
        assertEquals(-2, testGoal.getCalorieGoal());
        assertEquals(-2, testGoal.getSugarGoal());
        assertEquals(-2, testGoal.getSodiumGoal());
        assertEquals(-2, testGoal.getProteinGoal());
    }
}
