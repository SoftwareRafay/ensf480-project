/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */

package Boundary;

import Entity.*;
import database.ReadDB;
import database.UpdateDB;
import Control.VoucherGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;


public class CancelView extends JPanel {

	
	private JLabel cancelButton;
	private JLabel logo;
	
	//Enter ticket number
	private JTextField ticketCodeTextField;
	private JLabel ticketNumberLabel;
	
	//Go back
	private JLabel backButton;

	public CancelView(JFrame frame, Login backend) {

		setLayout(null);

		logo = new JLabel("");
        logo.setIcon(new ImageIcon(LoginView.class.getResource("/logo.png")));
        logo.setBounds(480, 5, 480, 90);
        add(logo);

		// Back Button
		backButton = new JLabel("Go Back");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
backButton.setFont(new Font("Arial", Font.PLAIN, 15));
backButton.setForeground(Color.WHITE); 
backButton.setBounds(30, 30, 150, 50);
backButton.setHorizontalAlignment(SwingConstants.CENTER);
backButton.setBackground(Color.DARK_GRAY); 
backButton.setOpaque(true);
add(backButton);

		//Go back to main page
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView homePage = new MainView(frame, backend);
				frame.setContentPane(homePage);
				frame.revalidate();
			}
		});
		

		ticketCodeTextField = new JTextField();
		ticketCodeTextField.setBounds(554, 350, 254, 28);
		ticketCodeTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		ticketCodeTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		ticketCodeTextField.setForeground(Color.DARK_GRAY);
		ticketCodeTextField.setBackground(Color.WHITE);
		ticketCodeTextField.setOpaque(true);
		ticketCodeTextField.setColumns(10);
		ticketCodeTextField.setOpaque(true);
		add(ticketCodeTextField);

	
		ticketNumberLabel = new JLabel("Ticket Code");
		ticketNumberLabel.setBounds(627, 335, 108, 14);
		ticketNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ticketNumberLabel.setForeground(Color.white);
		ticketNumberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(ticketNumberLabel);

		backButton.setBounds(30, 30, 80, 50);
		add(backButton);
		
		
		cancelButton = new JLabel("Cancel Ticket");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.GRAY);
		cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelButton.setBackground(new Color(255, 140, 0));
		cancelButton.setOpaque(true);
		cancelButton.setHorizontalAlignment(SwingConstants.CENTER);
		cancelButton.setBounds(630, 390, 130, 40);
		add(cancelButton);
		
		
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int ticketId = Integer.parseInt(ticketCodeTextField.getText().trim());
					ReadDB dbReader = new ReadDB(); 
					UpdateDB dbUpdater = new UpdateDB();
		
					if (dbReader.isTicketExists(ticketId)) { 
						dbUpdater.removeTicketFromDatabase(ticketId); 
		
						// Generate a random voucher
						String voucherCode = VoucherGenerator.generateVoucherCode();
						double voucherValue = (backend.getCurrentGuestUser() != null) ? 21.25 : 25.00;
						String userType = (backend.getCurrentGuestUser() != null) ? "Guest" : "Registered";
		
						// Add voucher to database
						dbUpdater.addVoucherToDatabase(voucherCode, voucherValue, userType);
		
						// Show message
						JOptionPane.showMessageDialog(frame, 
							"Your ticket is cancelled. " +
							(backend.getCurrentGuestUser() != null 
								? "Your voucher code is: " + voucherCode + " (Value: $21.25). Use within 1 year."
								: "Your voucher code is: " + voucherCode + " (Value: $25.00). Use within 1 year.")
						);
					} else {
						JOptionPane.showMessageDialog(frame, 
													  "The ticket number entered does not exist.", 
													  "Invalid Ticket", JOptionPane.WARNING_MESSAGE);
					}
		
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, 
												  "Please enter a valid ticket number.", 
												  "Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
				frame.revalidate();
			}
		});
		
		// Set bg image
		JLabel registerBackground = new JLabel("");
		registerBackground.setBounds(-2, -1, 1366, 768);
		registerBackground.setIcon(new ImageIcon(LoginView.class.getResource("/bg2.jpg")));
        registerBackground.setHorizontalAlignment(SwingConstants.CENTER);
		add(registerBackground);

	}

	private static final long serialVersionUID = 1L;
}
