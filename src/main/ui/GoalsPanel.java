package ui;

import model.Goals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

// Panel displaying current goals, and allows user to change goals
public class GoalsPanel extends JPanel {
    private MenuFrame menuFrame;
    private ActionListener setListener;
    private ActionListener returnListener;
    private JLabel titleLabel;
    private JLabel calGoalLabel;
    private JLabel sugGoalLabel;
    private JLabel sodGoalLabel;
    private JLabel proGoalLabel;
    private JLabel calLabel;
    private JLabel sugLabel;
    private JLabel sodLabel;
    private JLabel proLabel;
    private JTextField calField;
    private JTextField sugField;
    private JTextField sodField;
    private JTextField proField;
    private JButton setButton;
    private JButton returnButton;
    private Goals goals;

    public GoalsPanel(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        calField = new JTextField(20);
        sugField = new JTextField(20);
        sodField = new JTextField(20);
        proField = new JTextField(20);
        titleLabel = new JLabel("Daily Goals");
        calLabel = new JLabel("Calories:");
        sugLabel = new JLabel("Sugar (g):");
        sodLabel = new JLabel("Sodium (mg):");
        proLabel = new JLabel("Protein (g)");
        setButton = new JButton("Set");
        returnButton = new JButton("Return");
        goals = menuFrame.getGoals();
        setListenerSetup(this);
        returnListenerSetup(this);
        setButton.addActionListener(setListener);
        returnButton.addActionListener(returnListener);
        goalSetup();
        addComponents();
    }

    // EFFECTS: displays current goals
    void goalSetup() {
        if (menuFrame.getGoals().getCalorieGoal() == -2) {
            calGoalLabel = new JLabel("Calories: none");
        } else {
            calGoalLabel = new JLabel("Calories: " + menuFrame.getGoals().getCalorieGoal());
        }
        if (menuFrame.getGoals().getSugarGoal() == -2) {
            sugGoalLabel = new JLabel("Sugar (g): none");
        } else {
            sugGoalLabel = new JLabel("Sugar (g): " + menuFrame.getGoals().getSugarGoal());
        }
        if (menuFrame.getGoals().getSodiumGoal() == -2) {
            sodGoalLabel = new JLabel("Sodium (mg): none");
        } else {
            sodGoalLabel = new JLabel("Sodium (mg): " + menuFrame.getGoals().getSodiumGoal());
        }
        if (menuFrame.getGoals().getProteinGoal() == -2) {
            proGoalLabel = new JLabel("Protein (g): none");
        } else {
            proGoalLabel = new JLabel("Protein (g): " + menuFrame.getGoals().getProteinGoal());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds components to this
    void addComponents() {
        add(titleLabel);
        add(calGoalLabel);
        add(sugGoalLabel);
        add(sodGoalLabel);
        add(proGoalLabel);
        add(Box.createRigidArea(new Dimension(5, 10)));
        add(calLabel);
        add(calField);
        add(sugLabel);
        add(sugField);
        add(sodLabel);
        add(sodField);
        add(proLabel);
        add(proField);
        add(setButton);
        add(returnButton);
    }

    // MODIFIES: menuFrame
    // EFFECTS: reads user input in goals fields, then sets new goals
    public void setGoals() {
        int cal = parseInt(calField.getText());
        int sug = parseInt(sugField.getText());
        int sod = parseInt(sodField.getText());
        int pro = parseInt(proField.getText());
        menuFrame.getGoals().setCalorieGoal(cal);
        menuFrame.getGoals().setSugarGoal(sug);
        menuFrame.getGoals().setSodiumGoal(sod);
        menuFrame.getGoals().setProteinGoal(pro);
    }

    // EFFECTS: sets up listener for "set" button
    void setListenerSetup(GoalsPanel goalsPanel) {
        GoalsPanel gp = goalsPanel;
        setListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGoals();
                menuFrame.getFrame().remove(gp);
                menuFrame.showMenu();
            }
        };
    }

    // EFFECTS: sets up listener for "return" button
    public void returnListenerSetup(GoalsPanel goalsPanel) {
        GoalsPanel gp = goalsPanel;
        returnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.getFrame().remove(gp);
                menuFrame.showMenu();
            }
        };
    }

}
