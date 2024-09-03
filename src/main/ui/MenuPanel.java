package ui;

import javax.swing.*;
import java.awt.*;

// Panel for main menu of nutrition tracker
public class MenuPanel extends JPanel {
    private MenuFrame frame;
    private JLabel welcome;
    private JButton newLogButton;
    private JButton viewLogButton;
    private JButton goalsButton;
    private JButton saveButton;
    private JButton loadButton;
    private ImageIcon image;
    private JLabel background;

    public MenuPanel(MenuFrame frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        newLogButton = new JButton("New Log");
        viewLogButton = new JButton("View previous Log");
        goalsButton = new JButton("View/set goals");
        saveButton = new JButton("Save progress to file");
        loadButton = new JButton("Load progress from file");
        image = new ImageIcon("./data/IMG_2199.jpeg");
        image.setImage(image.getImage().getScaledInstance(300, 210, Image.SCALE_DEFAULT));
        background = new JLabel(image);
        setBackground(Color.lightGray);
        welcomeMessage();
        add(Box.createRigidArea(new Dimension(5, 15)));
        buttonSetup();
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds welcome Label to this
    public void welcomeMessage() {
        welcome = new JLabel("Eating log of day " + frame.getDay() + ". Please choose an option:");
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        add(welcome);
    }

    // MODIFIES: this
    // EFFECTS: sets up and adds buttons to this
    public void buttonSetup() {
        newLogButton.setAlignmentX(CENTER_ALIGNMENT);
        viewLogButton.setAlignmentX(CENTER_ALIGNMENT);
        goalsButton.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        loadButton.setAlignmentX(CENTER_ALIGNMENT);
        background.setAlignmentX(CENTER_ALIGNMENT);
        newLogButton.addActionListener(frame.getAccessListener());
        viewLogButton.addActionListener(frame.getAccessListener());
        goalsButton.addActionListener(frame.getAccessListener());
        saveButton.addActionListener(frame.getAccessListener());
        loadButton.addActionListener(frame.getAccessListener());
        add(newLogButton);
        add(viewLogButton);
        add(goalsButton);
        add(saveButton);
        add(loadButton);
        add(Box.createRigidArea(new Dimension(5, 10)));
        add(background);
    }

    public JButton getNewLogButton() {
        return newLogButton;
    }

    public JButton getViewLogButton() {
        return viewLogButton;
    }

    public JButton getGoalsButton() {
        return goalsButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }
}
