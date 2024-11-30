
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

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import java.util.ArrayList;


public class CartView extends JPanel {
	

	private JLabel backButton;
	private JLabel logo;
	
	private JLabel checkoutButton;

	public CartView(JFrame frame, Login backend) {
		setLayout(null);

		

		logo = new JLabel("");
        logo.setIcon(new ImageIcon(LoginView.class.getResource("/logo.png")));
        logo.setBounds(480, 5, 480, 90);
        add(logo);

	


		JLabel cartTotalLabel = new JLabel("");
		cartTotalLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cartTotalLabel.setForeground(Color.white);
		cartTotalLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		cartTotalLabel.setBounds(26, 679, 571, 50);
		cartTotalLabel.setText("Total: $" + String.format("%.2f",backend.getCurrentUser().getCart().calculatetotalCost()));
		add(cartTotalLabel);






		checkoutButton = new JLabel("Checkout");
		checkoutButton.setFont(new Font("Arial", Font.PLAIN, 25));
		checkoutButton.setBounds(1082, 679, 254, 50);
		checkoutButton.setToolTipText("Checkout");
		checkoutButton.setForeground(Color.WHITE);
		checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkoutButton.setBackground(new Color(255, 140, 0));
		checkoutButton.setOpaque(true);
		checkoutButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Go to CheckoutView 
		checkoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CheckoutView paymentPanel = new CheckoutView(frame, backend);
				frame.setContentPane(paymentPanel);
				frame.revalidate();
				
			}
		});
		if (backend.getCurrentUser().getCart().getitems().size() == 0){
			checkoutButton.setVisible(false);
		} else{
			checkoutButton.setVisible(true);
		}
		add(checkoutButton);

		// Show list of tickets in cart
		JPanel imagePanels = new JPanel();
		imagePanels.setPreferredSize(new Dimension(1304, 1800));

		ArrayList<Reservation> cartItems = backend.getCurrentUser().getCart().getitems();
		JLabel[] ticketCards = new JLabel[cartItems.size()];
		JLabel[] ticketDetails = new JLabel[cartItems.size()];
		for (int i = 0; i < cartItems.size(); i++) {
			

			ticketDetails[i] = new JLabel("");
			ticketDetails[i].setHorizontalAlignment(SwingConstants.LEFT);
			ticketDetails[i].setText(cartItems.get(i).getshowing_time() + "        " + cartItems.get(i).getseat_to_book()
					+ "        " + cartItems.get(i).getmovie_to_book());
			ticketDetails[i].setForeground(Color.white);
			ticketDetails[i].setFont(new Font("Arial", Font.PLAIN, 20));
			ticketDetails[i].setBounds(60, 70 + (170 * i), 1000, 32);
			imagePanels.add(ticketDetails[i]);

			ticketCards[i] = new JLabel("");
			ticketCards[i].setVerticalAlignment(SwingConstants.TOP);
			ticketCards[i].setHorizontalAlignment(SwingConstants.LEFT);
			ticketCards[i].setBackground(Color.GRAY);
			ticketCards[i].setBounds(0, 5 + (170 * i), 1285, 164);
			
			imagePanels.add(ticketCards[i]);
		}
		add(imagePanels);
		imagePanels.setOpaque(false);

		JScrollPane scrollPane = new JScrollPane(imagePanels);
		imagePanels.setLayout(null);
		scrollPane.setBounds(20, 120, 1304, 520);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		add(scrollPane);

		
		backButton = new JLabel("Go Back");
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
backButton.setFont(new Font("Arial", Font.PLAIN, 15));
backButton.setForeground(Color.WHITE); 
backButton.setBounds(30, 30, 150, 50);
backButton.setHorizontalAlignment(SwingConstants.CENTER);
backButton.setBackground(Color.DARK_GRAY); 
backButton.setOpaque(true);
add(backButton);


		//Go back to main view
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainView homePanel = new MainView(frame, backend);
				frame.setContentPane(homePanel);
				frame.revalidate();
			}
		});
		backButton.setBounds(30, 30, 80, 50);
		add(backButton);

		

		// Set bg image
		JLabel homeBackground = new JLabel("");
		homeBackground.setBounds(-2, -1, 1366, 768);
		homeBackground.setIcon(new ImageIcon(LoginView.class.getResource("/bg2.jpg")));
        homeBackground.setHorizontalAlignment(SwingConstants.CENTER);
		add(homeBackground);
	}

	private static final long serialVersionUID = 1L;
}
