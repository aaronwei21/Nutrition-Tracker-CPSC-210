package ui;

import model.Goals;
import model.LogList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// class containing main JFrame of GUI and data of eating logs and goals
public class MenuFrame {
    private JFrame frame;
    private MenuPanel menuPanel;
    private NewLogPanel newLogPanel;
    private GoalsPanel goalsPanel;
    private ViewLogPanel viewLogPanel;
    private ActionListener accessListener;
    private static final String JSON_STORE = "./data/TrackerMenu.json";
    private int day;
    private LogList logList;
    private Goals goals;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private ImageIcon image;
    private JLabel background;

    public MenuFrame() {
        this.day = 1;
        logList = new LogList();
        goals = new Goals();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        frame = new JFrame();
        listenerSetUp(this);
        closingSetup();
        menuPanel = new MenuPanel(this);
        frameSetUp();
    }

    // EFFECTS: sets up container frame
    private void frameSetUp() {
        frame.setLayout(new BorderLayout(10, 5));
        frame.setTitle("Nutrition Tracker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.add(menuPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // EFFECTS: sets up listener for buttons on main menu
    private void listenerSetUp(MenuFrame menuFrame) {
        MenuFrame mf = menuFrame;

        accessListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == menuPanel.getNewLogButton()) {
                    newLogPanel = new NewLogPanel(mf);
                    openFrame(newLogPanel);
                } else if (e.getSource() ==  menuPanel.getViewLogButton()) {
                    viewLogPanel = new ViewLogPanel(mf);
                    openFrame(viewLogPanel);
                } else if (e.getSource() == menuPanel.getGoalsButton()) {
                    goalsPanel = new GoalsPanel(mf);
                    openFrame(goalsPanel);
                } else if (e.getSource() == menuPanel.getSaveButton()) {
                    saveData();
                } else if (e.getSource() == menuPanel.getLoadButton()) {
                    loadData();
                }
            }
        };
    }

    // EFFECTS: removes menuPanel and replaces it with new panel
    public void openFrame(JPanel panel) {
        frame.remove(menuPanel);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // EFFECTS: removes current panel and displays menuPanel
    public void showMenu() {
        menuPanel = new MenuPanel(this);
        frame.add(menuPanel);
        frame.setVisible(true);
    }

    public int getDay() {
        return day;
    }

    public void nextDay() {
        day++;
    }

    public LogList getLogList() {
        return logList;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Goals getGoals() {
        return goals;
    }

    public ActionListener getAccessListener() {
        return accessListener;
    }

    // Effects: saves logList and goals to JSON file
    // Note: modelled after JsonSerializationDemo
    public void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(logList, goals);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Effects: loads logList and goals from JSON file
    // Note: modelled after JsonSerializationDemo
    public void loadData() {
        try {
            logList = jsonReader.readLogList();
            goals = jsonReader.readGoals();
            day = logList.getListOfLog().size() + 1;
            frame.remove(menuPanel);
            showMenu();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: adds listener for close button
    public void closingSetup() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                logList.printEvents();
            }
        });
    }
}
