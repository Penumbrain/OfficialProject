package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.UserController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Riley Bennett
 * @version 0.1
 * OwnerProfileForm JPanel is responsible to create Owner Profile.
 * BorderLayout and GridBagLayout are used in this JPanel.
 */
public class LogInScreen extends JPanel {
    /**
     * State of nameFields
     */
    private JTextField nameField;
    /**
     * State of nameFields
     */
    private JTextField emailArea;

    public LogInScreen(JPanel cardPanel, CardLayout  cardLayout  ){
        setLayout(new BorderLayout());

        // Create and add the application name label
        JLabel appNameLabel = new JLabel("Welcome to FileNtro");
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(appNameLabel, BorderLayout.NORTH);

        // Create the form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 0, 20);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Name:");
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        nameField = new JTextField();
        formPanel.add(nameField, gbc);



        gbc.insets = new Insets(0, 20, 0, 20);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;

        JLabel addressLabel = new JLabel("Email:");
        formPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
         gbc.weighty = 0.5;

        emailArea = new JTextField();
        formPanel.add(emailArea, gbc);

        gbc.insets = new Insets(20, 20, 0, 20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel createAcctTxt = new JLabel("New to FileNtro?");
        formPanel.add(createAcctTxt, gbc);

        gbc.gridx = 1;
        gbc.fill = 0;
        JButton createAcct = new JButton("Create Account");
        createAcct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(new CreateProfile(cardPanel, cardLayout), "CreateProfileScreen");
                nameField.setText("");
                emailArea.setText("");
                cardLayout.show(cardPanel, "CreateProfileScreen");
            }
        });

        formPanel.add(createAcct, gbc);

        // Add the form panel to the center of the UserInfoPanel
        add(formPanel, BorderLayout.CENTER);

        // Create and add the login button
        JButton logInButton = new JButton("Log In");
        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String email = emailArea.getText();
                //Tin Phu
                //Show Error message if one of user inputs isEmty()
                if(name.isEmpty() || email.isEmpty()){
                    JOptionPane.showMessageDialog(LogInScreen.this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                User theUser = UserController.findUser(name, email);

                if (theUser == null) {
                    JOptionPane.showMessageDialog(LogInScreen.this, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Tin Phu
                //AboutScreen is created with new User() as argument
                //cardPanel, cardLayout are passed to AboutScreen, so we can switch back to previous JPanel.

                cardPanel.add(new HomeScreen(theUser, cardPanel, cardLayout), "HomeScreen");

                nameField.setText("");
                emailArea.setText("");
                //Switch Trigger Here

                cardLayout.show(cardPanel, "HomeScreen");

            }
        });
        add(logInButton, BorderLayout.SOUTH);
    }
}
