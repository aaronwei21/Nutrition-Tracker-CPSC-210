package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogListTest {
    LogList testLogList;
    Log logA;
    Log logB;

    @BeforeEach
    void runBefore() {
        testLogList = new LogList();
        logA = new Log(1);
        logB = new Log(2);
    }

    @Test
    void testListOfLog() {
        assertEquals(0, testLogList.getListOfLog().size());
    }

    @Test
    void testAddLog() {
        testLogList.addLog(logA, false);
        assertEquals(1, testLogList.getListOfLog().size());
        assertEquals(logA, testLogList.getListOfLog().get(0));
        testLogList.addLog(logB, false);
        assertEquals(2, testLogList.getListOfLog().size());
        assertEquals(logA, testLogList.getListOfLog().get(0));
        assertEquals(logB, testLogList.getListOfLog().get(1));
    }

    @Test
    void testGetLogDay() {
        testLogList.addLog(logA, false);
        testLogList.addLog(logB, false);
        assertEquals(logA, testLogList.getLog(1));
        assertEquals(logB, testLogList.getLog(2));
        assertEquals(1, testLogList.getLog(1).getDay());
        assertEquals(2, testLogList.getLog(2).getDay());
    }

    @Test
    void testPrintEvents() {
        testLogList.printEvents();
        assertTrue(testLogList.isPrinted());
    }
}
