package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Startscreen {
    private JPanel rootPanel;
    private JLabel startLabel;
    private JButton startButton;
    private JTextField nameTextField;

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
        frame.setContentPane(new Startscreen().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
