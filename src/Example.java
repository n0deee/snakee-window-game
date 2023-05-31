import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Example extends JFrame {
    JPanel mainPanel, formPanel, buttonsPanel;
    JTextField firstNameTextField, lastNameTextField;
    JLabel firstNameLabel, lastNameLabel, welcomeLabel;
    JButton okButton, clearButton;
    
    public Example() {}

    public void initalize() {
        firstNameLabel = new JLabel("First Name");

        lastNameLabel = new JLabel("Last Name");

        firstNameTextField = new JTextField();

        lastNameTextField = new JTextField();

        welcomeLabel = new JLabel();

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                welcomeLabel.setText("Hello, " + firstName + " " + lastName);
            }

        });

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                welcomeLabel.setText("");
            }
            
        });

        buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonsPanel.add(okButton);
        buttonsPanel.add(clearButton);

        formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(4, 1, 5, 5));
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameTextField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameTextField);


        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.setBackground(new Color(128, 128, 255));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(mainPanel);

        setTitle("Welcome");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
