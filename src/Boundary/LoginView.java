/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-10
 * Authors: Group 5-L01
 * Version: FINAL
 */
package Boundary;

import Entity.*;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginView extends JPanel {

	private JLabel logo;
		
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	JLabel submitLoginButton;
	private JLabel registerButton;
	private JLabel guestButton;

	
	public LoginView(JFrame frame, Login backend) {
		setLayout(null);

		
		logo = new JLabel("");
        logo.setIcon(new ImageIcon(LoginView.class.getResource("/logo.png")));
        logo.setBounds(480, 180, 480, 90);
        add(logo);

			
		registerButton = new JLabel("SIGN UP");
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setForeground(Color.WHITE); // Text color white
        registerButton.setFont(new Font("Arial", Font.BOLD, 15));
        registerButton.setBackground(new Color(255, 140, 0)); // Background color orange
        registerButton.setOpaque(true); // Needed for background color
        registerButton.setBounds(585, 520, 200, 30);
        add(registerButton);
		
		
		registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegistrationView registerPanel = new RegistrationView(frame, backend);
                frame.setContentPane(registerPanel);
                frame.revalidate();
            }
        });

		guestButton = new JLabel("PROCEED AS GUEST");
        guestButton.setHorizontalAlignment(SwingConstants.CENTER);
        guestButton.setForeground(Color.WHITE); // Text color white
        guestButton.setFont(new Font("Arial", Font.BOLD, 15));
        guestButton.setBackground(new Color(255, 140, 0)); // Background color orange
        guestButton.setOpaque(true); // Needed for background color
        guestButton.setBounds(585, 550, 200, 30);
        guestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        guestButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backend.guestUser();
                MainView homePanel = new MainView(frame, backend);
                frame.setContentPane(homePanel);
                frame.revalidate();
            }
        });
        add(guestButton);

		        // Username Text Field
		usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
        usernameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        usernameTextField.setForeground(Color.WHITE);
        usernameTextField.setBackground(Color.GRAY);
        usernameTextField.setBounds(564, 314, 254, 28);
        usernameTextField.setOpaque(true);
        usernameTextField.setColumns(10);
        add(usernameTextField);

		
		// Password Field
        passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 13));
        passwordField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBounds(564, 387, 254, 28);
		passwordField.setBackground(Color.GRAY);
		passwordField.setColumns(10);
        add(passwordField);

        // Username Label
        usernameLabel = new JLabel("Username");
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setForeground(Color.WHITE); // Text color set to white
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setBounds(653, 299, 77, 14);
        add(usernameLabel);


	  // Password Label
		passwordLabel = new JLabel("Password");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setForeground(Color.WHITE); // Text color set to white
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setBounds(653, 372, 77, 14);
        add(passwordLabel);

        // Invalid Login Label
        JLabel invalidLoginErrorLabel = new JLabel("<html>The login info does not match a registered account in the database.</html>");
        invalidLoginErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        invalidLoginErrorLabel.setForeground(Color.RED);
        invalidLoginErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        invalidLoginErrorLabel.setBounds(564, 235, 254, 45);
        invalidLoginErrorLabel.setVisible(false);
        add(invalidLoginErrorLabel);
		
		submitLoginButton = new JLabel("Login");
        submitLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitLoginButton.setHorizontalAlignment(SwingConstants.CENTER);
        submitLoginButton.setFont(new Font("Arial", Font.BOLD, 18));
        submitLoginButton.setForeground(Color.WHITE); // Text color white
        submitLoginButton.setBackground(new Color(255, 140, 0)); // Background color orange
        submitLoginButton.setOpaque(true); // Needed for background color
        submitLoginButton.setBounds(640, 420, 100, 40);
		
		//Login, go to MainView ONLY IF credentials match stored values in DB
		//Other give error message
		submitLoginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = usernameTextField.getText().toLowerCase();
				String pass = String.valueOf(passwordField.getPassword());
				if (backend.login_verification(user, pass) != null) {
					MainView homePanel = new MainView(frame, backend);
					frame.setContentPane(homePanel);
				} else {
					invalidLoginErrorLabel.setVisible(true);
				}
				frame.revalidate();
			}
		});
		
		add(submitLoginButton);

		
		JLabel loginBackground = new JLabel("");
		loginBackground.setBounds(-2, -1, 1366, 768);
		loginBackground.setIcon(new ImageIcon(LoginView.class.getResource("/bg.jpg")));
		add(loginBackground);
	}

	private static final long serialVersionUID = 1L;
}
