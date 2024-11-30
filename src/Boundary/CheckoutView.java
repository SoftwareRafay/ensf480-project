/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Boundary;

import Entity.*;
import Control.*;
import database.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.time.LocalDate;

public class CheckoutView extends JPanel {

	private JLabel registerButton;
	
	private JLabel titleLabel;
	
	private JTextField emailTextField;
	private JLabel emailLabel;
	
	
	private JTextField payment_card_numberTextField;
	private JLabel payment_card_numberLabel;
	
	private JLabel userStoredBankingButton;
	
	private JTextField nameTextField;
	private JLabel nameLabel;
	
	
	private JLabel backButton;
	public UpdateDB update;

	public CheckoutView(JFrame frame, Login backend) {

		setLayout(null);

		
		titleLabel = new JLabel(
				"YOUR TOTAL: $" + String.format("%.2f", backend.getCurrentUser().getCart().gettotalCost()));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.white);
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		titleLabel.setBounds(530, 175, 300, 30);
		add(titleLabel);

		backButton = new JLabel("Go Back");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
backButton.setFont(new Font("Arial", Font.PLAIN, 15));
backButton.setForeground(Color.WHITE); 
backButton.setBounds(30, 30, 150, 50);
backButton.setHorizontalAlignment(SwingConstants.CENTER);
backButton.setBackground(Color.DARK_GRAY); 
backButton.setOpaque(true);
add(backButton);
		
		
		// back button to return to CartView
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CartView cartPage = new CartView(frame, backend);
				frame.setContentPane(cartPage);
				frame.revalidate();
			}
		});
	
		emailTextField = new JTextField();
		emailTextField.setBounds(561, 235, 254, 28);
		emailTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		emailTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		emailTextField.setForeground(Color.WHITE);
		emailTextField.setBackground(Color.GRAY);
		emailTextField.setOpaque(true);
		emailTextField.setColumns(10);
		emailTextField.setOpaque(true);
		add(emailTextField);

		emailLabel = new JLabel("Email");
		emailLabel.setBounds(634, 220, 108, 14);
		emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emailLabel.setForeground(Color.white);
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(emailLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(561, 295, 254, 28);
		nameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		nameTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		nameTextField.setForeground(Color.WHITE);
		nameTextField.setBackground(Color.GRAY);
		nameTextField.setOpaque(true);
		nameTextField.setColumns(10);
		nameTextField.setOpaque(true);
		add(nameTextField);

		nameLabel = new JLabel("Name");
		nameLabel.setBounds(634, 280, 108, 14);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(Color.white);
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(nameLabel);

		payment_card_numberTextField = new JTextField();
		payment_card_numberTextField.setBounds(561, 350, 254, 28);
		payment_card_numberTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		payment_card_numberTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
		payment_card_numberTextField.setForeground(Color.WHITE);
		payment_card_numberTextField.setBackground(Color.GRAY);
		payment_card_numberTextField.setOpaque(true);
		payment_card_numberTextField.setColumns(10);
		payment_card_numberTextField.setOpaque(true);
		add(payment_card_numberTextField);
	
		payment_card_numberLabel = new JLabel("Card Number");
		payment_card_numberLabel.setBounds(634, 335, 108, 14);
		payment_card_numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		payment_card_numberLabel.setForeground(Color.white);
		payment_card_numberLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		add(payment_card_numberLabel);

	
		JLabel invalidInfoErrorLabel = new JLabel("<html>" + "Please enter valid values!" + "</html>");
		invalidInfoErrorLabel.setBounds(600, 430, 254, 45);
		invalidInfoErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		invalidInfoErrorLabel.setForeground(Color.RED);
		invalidInfoErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		invalidInfoErrorLabel.setVisible(false);
		add(invalidInfoErrorLabel);

		backButton.setBounds(30, 30, 80, 50);
		add(backButton);

		
		userStoredBankingButton = new JLabel("USE YOUR STORED INFO");
		userStoredBankingButton.setHorizontalAlignment(SwingConstants.CENTER);
		userStoredBankingButton.setForeground(Color.white);
		userStoredBankingButton.setFont(new Font("Arial", Font.BOLD, 16));
		userStoredBankingButton.setBounds(510, 502, 350, 14);
		userStoredBankingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		// button to pay with information stored in the databse
		userStoredBankingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean infoValid;
			
				if (backend.getCurrentRegisteredUser() != null) {
					String name = backend.getCurrentRegisteredUser().getBankInfo().getName_of_the_customer();

					String cardNum = backend.getCurrentRegisteredUser().getBankInfo().getpayment_card_number();
					String email = backend.getCurrentRegisteredUser().getgmail_of_user();
					
					invalidInfoErrorLabel.setVisible(false);
					

					if (backend.getDataController().getInst().CardInfo_verification(name, cardNum)) {
						infoValid = true;
					} 
					else {
						infoValid = false;
					}
					
					if (infoValid) {
						double finalTotal = backend.getCurrentUser().getCart().gettotalCost();
						processPayment(frame, backend, finalTotal, backend.getCurrentRegisteredUser().getBankInfo(),email);
						}
						else  {
							invalidInfoErrorLabel.setVisible(true);
						} 
						
					} 
					
				frame.revalidate();
			}
		});
		
		if (backend.getCurrentRegisteredUser() != null) {
			userStoredBankingButton.setVisible(true);
		} else {
			userStoredBankingButton.setVisible(false);
		}
		add(userStoredBankingButton);

		
		registerButton = new JLabel("Pay");
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.setFont(new Font("Arial", Font.BOLD, 23));
		registerButton.setToolTipText("Checkout");
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(new Color(255, 140, 0));
		registerButton.setOpaque(true);
		registerButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		// button to pay using information entered
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				String cardNum = payment_card_numberTextField.getText();
				String name = nameTextField.getText();
				String email = emailTextField.getText();
				
				
				boolean vaildInfo = false;
				invalidInfoErrorLabel.setVisible(false);
				
				if (backend.getDataController().getInst().CardInfo_verification(name, cardNum)) {
					vaildInfo = true;
				} else {
					vaildInfo = false;
				}
				
				if (vaildInfo) {
					double finalTotal = backend.getCurrentUser().getCart().gettotalCost() ;
					
					processPayment(frame, backend, finalTotal,
							new UserBankInfo(name, cardNum),email);
				} 
				else if (vaildInfo == false) {
					invalidInfoErrorLabel.setVisible(true);
				} 
			
			}
		});

		invalidInfoErrorLabel.setVisible(false);
		registerButton.setBounds(658, 390, 80, 50);
		add(registerButton);

		//Set bg image
		JLabel registerBackground = new JLabel("");
		registerBackground.setBounds(-2, -1, 1366, 768);
		registerBackground.setIcon(new ImageIcon(LoginView.class.getResource("/bg2.jpg")));
        registerBackground.setHorizontalAlignment(SwingConstants.CENTER);
		add(registerBackground);
	}

	public void processPayment(JFrame frame, Login backend, double costAmount, UserBankInfo bankingInfo,String email) {
		
		backend.getCurrentUser().getCart().setPay(new Payment(costAmount, bankingInfo));
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		for (int i = 0; i < backend.getCurrentUser().getCart().getitems().size(); i++) {
			Movie movie = backend.getCurrentUser().getCart().getitems().get(i).getmovie_to_book();
			Showtime showtime = backend.getCurrentUser().getCart().getitems().get(i).getshowing_time();
			Seat seat = backend.getCurrentUser().getCart().getitems().get(i).getseat_to_book();
			 
			showtime.bookSeat(seat.getSelected_row(), seat.getSelected_column());
			Ticket t = new Ticket(movie, showtime, seat);
			ticketList.add(t);

			backend.getDataController().addTicket(t);
		}
		
		Date nowDate = new Date(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear());
		Receipt r = new Receipt(backend.getCurrentUser().getCart().getPay(),nowDate,ticketList);
	
		JOptionPane.showMessageDialog(null, "This receipt was emailed to you.\n"+r.receiptToString(),
				"Ticket is Issued.", JOptionPane.INFORMATION_MESSAGE);

				 
    String subject = "Your Ticket Receipt";
    String emailMessage =  
                          "Thank you for your purchase! Here are your ticket details:\n\n" + 
                          r.receiptToString() + 
                          "\n\nEnjoy your movie!\n\nBest regards,\nMovie Booking Team";

    EmailSender.sendEmail(email, subject, emailMessage);


		backend.getCurrentUser().setCart(new TicketCart());
		MainView homePanel = new MainView(frame, backend);
		frame.setContentPane(homePanel);
		frame.revalidate();
	}
	private static final long serialVersionUID = 1L;
}