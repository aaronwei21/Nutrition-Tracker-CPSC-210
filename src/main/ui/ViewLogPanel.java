package ui;

import model.Goals;
import model.Log;
import model.Meal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

// Panel which displays past logs
public class ViewLogPanel extends JPanel {
    private MenuFrame menuFrame;
    private Goals goals;
    private JLabel chooseLabel;
    private JTextField dayField;
    private JButton viewButton;
    private JButton returnButton;
    private ActionListener viewListener;
    private ActionListener returnListener;
    private JTable logTable;
    private JScrollPane logScroll;
    private static final String[] LOG_COL = {"Name", "Calories", "Sugar (g)", "Sodium (mg)", "Protein (g)"};
    private static final String[][] LOG_ROW = {{"", "", "", "", ""}};

    public ViewLogPanel(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        this.goals = menuFrame.getGoals();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        chooseLabel = new JLabel("Please choose a day to view:");
        dayField = new JTextField(20);
        viewButton = new JButton("View");
        returnButton = new JButton("Return");
        viewListenerSetup(this);
        returnListenerSetup(this);
        viewButton.addActionListener(viewListener);
        returnButton.addActionListener(returnListener);
        logScroll = new JScrollPane(new JTable(LOG_ROW, LOG_COL));
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: adds component to this
    public void addComponents() {
        add(chooseLabel);
        add(dayField);
        add(viewButton);
        add(returnButton);
        add(logScroll);
    }

    // EFFECTS: prints log information to GUI table
    public void printLog(Log log) {
        int i = 0;
        int rows = log.getMealList().size() + 2;
        String[][] logData = new String[rows][5];
        for (Meal meal : log.getMealList()) {
            logData[i][0] = meal.getMealName();
            logData[i][1] = "" + meal.getCalories();
            logData[i][2] = "" + meal.getSugar();
            logData[i][3] = "" + meal.getSodium();
            logData[i][4] = "" + meal.getProtein();
            i++;
        }
        logData[i][0] = log.getTotal().getMealName();
        logData[i][1] = "" + log.getTotal().getCalories();
        logData[i][2] = "" + log.getTotal().getSugar();
        logData[i][3] = "" + log.getTotal().getSodium();
        logData[i][4] = "" + log.getTotal().getProtein();
        i++;
        analyseGoals(logData, log.getTotal(), i);
        remove(logScroll);
        logScroll = new JScrollPane(new JTable(logData, LOG_COL));
        add(logScroll);
        menuFrame.getFrame().setVisible(true);
    }

    // MODIFIES: logData
    // EFFECTS: Calculates total meal data and add it to logData
    public void analyseGoals(String[][] logData, Meal total, int i) {
        logData[i][0] = "Goal Difference";
        if (goals.getCalorieGoal() != -2) {
            logData[i][1] = "" + (total.getCalories() - goals.getCalorieGoal());
            logData[i][2] = "" + (total.getSugar() - goals.getSugarGoal());
            logData[i][3] = "" + (total.getSodium() - goals.getSodiumGoal());
            logData[i][4] = "" + (total.getProtein() - goals.getProteinGoal());
        }
    }

    // EFFECTS: sets up listener for "view" button
    public void viewListenerSetup(ViewLogPanel viewLogPanel) {
        ViewLogPanel vlp = viewLogPanel;
        viewListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = parseInt(dayField.getText());
                Log log = menuFrame.getLogList().getLog(day);
                if (log != null) {
                    printLog(log);
                }
            }
        };
    }

    // EFFECTS: sets up listener for "return" button
    public void returnListenerSetup(ViewLogPanel viewLogPanel) {
        ViewLogPanel vlp = viewLogPanel;
        returnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.getFrame().remove(vlp);
                menuFrame.showMenu();
            }
        };
    }
}
