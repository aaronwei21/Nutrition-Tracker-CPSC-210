package ui;

import model.Log;
import model.Meal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

// Panel allowing user to add new meals to a daily log
public class NewLogPanel extends JPanel {
    private MenuFrame menuFrame;
    private JLabel nameLabel;
    private JLabel calLabel;
    private JLabel sugLabel;
    private JLabel sodLabel;
    private JLabel proLabel;
    private JTextField nameField;
    private JTextField calField;
    private JTextField sugField;
    private JTextField sodField;
    private JTextField proField;
    private JButton addButton;
    private JButton finishButton;
    private ActionListener addListener;
    private ActionListener finishListener;
    private Log newLog;

    public NewLogPanel(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        nameField = new JTextField(20);
        calField = new JTextField(20);
        sugField = new JTextField(20);
        sodField = new JTextField(20);
        proField = new JTextField(20);
        nameLabel = new JLabel("Name");
        calLabel = new JLabel("Calories");
        sugLabel = new JLabel("Sugar (g)");
        sodLabel = new JLabel("Sodium (mg)");
        proLabel = new JLabel("Protein (g)");
        addButton = new JButton("Add");
        finishButton = new JButton("Finish");
        newLog = new Log(menuFrame.getDay());
        addListenerSetup(this);
        finishListenerSetup(this);
        addButton.addActionListener(addListener);
        finishButton.addActionListener(finishListener);
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: adds components to this
    public void addComponents() {
        add(nameLabel);
        add(nameField);
        add(calLabel);
        add(calField);
        add(sugLabel);
        add(sugField);
        add(sodLabel);
        add(sodField);
        add(proLabel);
        add(proField);
        add(addButton);
        add(finishButton);
    }

    // EFFECTS: sets up listener for "add" button
    public void addListenerSetup(NewLogPanel newLogPanel) {
        NewLogPanel nlp = newLogPanel;

        addListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cal = parseInt(calField.getText());
                int sug = parseInt(sugField.getText());
                int sod = parseInt(sodField.getText());
                int pro = parseInt(proField.getText());
                Meal newMeal = new Meal(nameField.getText(), cal, sug, sod, pro);
                newLog.addMeal(newMeal, false);
                nameField.setText("");
                calField.setText("");
                sugField.setText("");
                sodField.setText("");
                proField.setText("");
            }
        };
    }

    // EFFECTS: sets up listener for "finish" button
    public void finishListenerSetup(NewLogPanel newLogPanel) {
        NewLogPanel nlp = newLogPanel;

        finishListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newLog.createTotal();
                menuFrame.getLogList().addLog(newLog, false);
                menuFrame.nextDay();
                menuFrame.getFrame().remove(nlp);
                menuFrame.showMenu();
            }
        };
    }
}