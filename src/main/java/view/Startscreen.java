package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Startscreen extends JPanel{
    private JPanel rootPanel;
    private JLabel startLabel;
    private JButton startButton;
    private JTextField nameTextField;
    private JComboBox startClassListBox;
    private JPanel startClassPanel;
    private JTextPane startClassText;

    public Startscreen() {
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String greeting = "Hello " + nameTextField.getText();
                startLabel.setText(greeting);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Startscreen");
        frame.setSize(800,800);
        frame.setContentPane(new Startscreen().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // https://tutorials.tinyappco.com/Java/ComboBoxes
        // TODO: iterate through classes and add them + change on selection
        startClassListBox = new JComboBox();
        startClassListBox.addItem("Fighter");
        startClassListBox.addItem("Rogue");
        startClassListBox.addItem("Cleric");
        startClassListBox.addItem("Wizard");
    }
}
