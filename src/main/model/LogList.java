package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

// This class stores adds and retrieves from a list of logs
public class LogList {
    private ArrayList<Log> listOfLog;
    private EventLog eventLog;
    private boolean printed;

    // EFFECTS: instantiates listOfLog
    public LogList() {
        listOfLog = new ArrayList<>();
        printed = false;
    }

    // MODIFIES: listOfLog
    // EFFECTS: add a new log to listOfLog;
    public void addLog(Log log, boolean loaded) {
        listOfLog.add(log);
        if (!loaded) {
            eventLog.getInstance().logEvent(new Event("- Log of day " + log.getDay() + " added to log list"));
        }
    }

    // REQUIRES: day <= number of elements in array
    // EFFECTS: returns a log with day field equal to day parameter
    public Log getLog(int day) {
        return listOfLog.get(day - 1);
    }

    public ArrayList<Log> getListOfLog() {
        return listOfLog;
    }

    public boolean isPrinted() {
        return printed;
    }

    public void printEvents() {
        Iterator<Event> logIterator = eventLog.getInstance().iterator();
        while (logIterator.hasNext()) {
            System.out.println(logIterator.next().getDescription());
        }
        printed = true;
    }

    // EFFECTS: returns representation of this as json array
    public JSONObject logListToJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Log l : listOfLog) {
            jsonArray.put(l.logToJson());
        }

        json.put("listOfLog", jsonArray);
        return json;
    }
}
