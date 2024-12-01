/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */
package Boundary;

import Entity.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;



public class RegistrationView extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField usernameTextField;
	private JLabel usernameLabel;

	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	private JTextField emailTextField;
	private JLabel emailLabel;
	
	private JTextField fullNameTextField;
	private JLabel fullNameLabel;

	private JTextField cardNumberTextField;
	private JLabel cardNumberLabel;

	private JLabel registerButton;
	
	private JLabel backButton;
	

	public RegistrationView(JFrame frame, Login backend) {

		setLayout(null);
		backButton = new JLabel("Return to Login");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
backButton.setFont(new Font("Arial", Font.PLAIN, 15));
backButton.setForeground(Color.WHITE); 
backButton.setBounds(30, 30, 150, 50);
backButton.setHorizontalAlignment(SwingConstants.CENTER);
backButton.setBackground(Color.DARK_GRAY); 
backButton.setOpaque(true);
add(backButton);

		//back button to go to login screen
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginView loginPanel = new LoginView(frame, backend);
				frame.setContentPane(loginPanel);
				frame.revalidate();
			}
		});
		
		
		cardNumberTextField = new JTextField();
		cardNumberTextField.setBounds(561, 463, 254, 28);
cardNumberTextField.setToolTipText("Enter your card number.");
cardNumberTextField.setFont(new Font("Arial", Font.PLAIN, 13));
cardNumberTextField.setBorder(new MatteBorder(0, 0, 3, 0, Color.LIGHT_GRAY)); // Set border color to light gray
cardNumberTextField.setForeground(Color.WHITE); // Text color white
cardNumberTextField.setBackground(Color.GRAY); // Background color gray
cardNumberTextField.setOpaque(true);
add(cardNumberTextField);

usernameTextField = new JTextField();
usernameTextField.setBounds(561, 250, 254, 28);
usernameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
usernameTextField.setBorder(new MatteBorder(0, 0, 3, 0, Color.LIGHT_GRAY));
usernameTextField.setForeground(Color.WHITE); // Text color white
usernameTextField.setBackground(Color.GRAY); // Background color gray
usernameTextField.setOpaque(true);
add(usernameTextField);

passwordField = new JPasswordField();
passwordField.setBounds(561, 359, 254, 28);
passwordField.setToolTipText("Enter your password.");
passwordField.setBorder(new MatteBorder(0, 0, 3, 0, Color.LIGHT_GRAY));
passwordField.setForeground(Color.WHITE);
passwordField.setBackground(Color.GRAY);  
passwordField.setOpaque(true);
passwordField.setFont(new Font("Arial", Font.PLAIN, 13));
add(passwordField);

emailTextField = new JTextField();
emailTextField.setBounds(561, 411, 254, 28);
emailTextField.setFont(new Font("Arial", Font.PLAIN, 13));
emailTextField.setBorder(new MatteBorder(0, 0, 3, 0, Color.LIGHT_GRAY));
emailTextField.setForeground(Color.WHITE); // Text color white
emailTextField.setBackground(Color.GRAY); // Background color gray
emailTextField.setOpaque(true);
add(emailTextField);

fullNameTextField = new JTextField();
fullNameTextField.setBounds(561, 307, 254, 28);
fullNameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
fullNameTextField.setBorder(new MatteBorder(0, 0, 3, 0, Color.LIGHT_GRAY));
fullNameTextField.setForeground(Color.WHITE); // Text color white
fullNameTextField.setBackground(Color.GRAY); // Background color gray
fullNameTextField.setOpaque(true);
add(fullNameTextField);
		
fullNameLabel = new JLabel("Full name");
fullNameLabel.setBounds(660, 292, 77, 14);
fullNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
fullNameLabel.setForeground(Color.WHITE); // Set text color to white
fullNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
add(fullNameLabel);

usernameLabel = new JLabel("Username");
usernameLabel.setBounds(660, 236, 77, 14);
usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
usernameLabel.setForeground(Color.WHITE); // Set text color to white
usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
add(usernameLabel);

passwordLabel = new JLabel("Password");
passwordLabel.setBounds(660, 345, 77, 14);
passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
passwordLabel.setForeground(Color.WHITE); // Set text color to white
passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
add(passwordLabel);

emailLabel = new JLabel("Email");
emailLabel.setBounds(667, 397, 50, 14);
emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
emailLabel.setForeground(Color.WHITE); // Set text color to white
emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
add(emailLabel);

cardNumberLabel = new JLabel("Card Number");
cardNumberLabel.setBounds(634, 449, 108, 14);
cardNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
cardNumberLabel.setForeground(Color.WHITE); // Set text color to white
cardNumberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
add(cardNumberLabel);


	
	
		JLabel invalidUsernameErrorLabel = new JLabel("<html>"
				+ "The username you have entered already exists." + "</html>");
				invalidUsernameErrorLabel.setBounds(565, 200, 254, 45);
				invalidUsernameErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
				invalidUsernameErrorLabel.setForeground(Color.white); // Error color red
				invalidUsernameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
				add(invalidUsernameErrorLabel);
				
		
		
		JLabel invalidCardErrorLabel = new JLabel("<html>"
				+ "Card information was declined by bank."+ "</html>");
				invalidCardErrorLabel.setBounds(565, 200, 254, 45);
				invalidCardErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
				invalidCardErrorLabel.setForeground(Color.white); // Error color red
				invalidCardErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
				invalidCardErrorLabel.setVisible(false);
				add(invalidCardErrorLabel);

		
		JLabel invalidNameErrorLabel = new JLabel(
				"<html>" + "Please enter your first AND last name." + "</html>");
				invalidNameErrorLabel.setBounds(565, 200, 254, 45);
				invalidNameErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
				invalidNameErrorLabel.setForeground(Color.white); // Error color red
				invalidNameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
				invalidNameErrorLabel.setVisible(false);
				invalidUsernameErrorLabel.setVisible(false);
				add(invalidNameErrorLabel);

		// submit button
		registerButton = new JLabel("Register");
		registerButton.setToolTipText("Register");
registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
registerButton.setFont(new Font("Arial", Font.BOLD, 20));
registerButton.setForeground(Color.WHITE); // Text color white
registerButton.setBackground(new Color(255, 140, 0)); // Blue background
registerButton.setOpaque(true);
registerButton.setBounds(650, 525, 100, 50); // Positioning the button
registerButton.setHorizontalAlignment(SwingConstants.CENTER);
add(registerButton);
		
		 // Upon clicking register button, data from all the text field is verified
		 // if the information provided is correct, it stores all the information in the database 
		 // and goes back to the login screen
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fullname = fullNameTextField.getText();
				String[] name = fullname.split(" ");
				String user = usernameTextField.getText();
				String pass = String.valueOf(passwordField.getPassword());
				String email = emailTextField.getText();
				String cardNum = cardNumberTextField.getText();
			
	
			

				// removing all error messeges
				invalidCardErrorLabel.setVisible(false);
				invalidNameErrorLabel.setVisible(false);
				invalidUsernameErrorLabel.setVisible(false);				

				// Checking username: make sure it doesn't already exist
				if (!backend.check_if_user_is_existing(user)) {
					
						if (name.length == 2) {							
							if(backend.getDataController().getInst().CardInfo_verification(fullname, cardNum)) {
								backend.user_registration(user, pass, name[0], name[1], email, fullname,  cardNum);
								LoginView loginPanel = new LoginView(frame, backend);
								frame.setContentPane(loginPanel);
							}
							else {
								invalidCardErrorLabel.setVisible(true);
							}							
						} else {
							invalidNameErrorLabel.setVisible(true);
						}
					
				} else {
					invalidUsernameErrorLabel.setVisible(true);
				}
				frame.revalidate();
			}
		});
		

		// set bg image
		JLabel registerBackground = new JLabel("");
		registerBackground.setBounds(-2, -1, 1366, 768);
		registerBackground.setIcon(new ImageIcon(LoginView.class.getResource("/bg2.jpg")));
        registerBackground.setHorizontalAlignment(SwingConstants.CENTER);
		add(registerBackground);
	}
}