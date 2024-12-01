/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Entity;

import java.util.ArrayList;

public class Receipt {
	private int ID_of_receipt;
	private static int number_of_receipt = 600;
	private Payment payment;
	private Date date;
	private ArrayList<Ticket> ticket_list;

	public Receipt(int ID_of_receipt, Payment payment, Date date) {
		this.ID_of_receipt = ID_of_receipt;
		if(ID_of_receipt > number_of_receipt){
			number_of_receipt = ID_of_receipt+1;
		}else {
			number_of_receipt++;
		}
		this.payment = payment;
		this.date = date;
		this.ticket_list = new ArrayList<Ticket>();
	}

	public Receipt(Payment payment, Date date,ArrayList<Ticket> ticket_list) {
		this.ID_of_receipt = number_of_receipt++;
		this.payment = payment;
		this.date = date;
		this.ticket_list = ticket_list;
		for(int i = 0; i < ticket_list.size(); i++){
			ticket_list.get(i).setReceipt(this);
		}
	}

	// Getters and Setters
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Ticket> getTicket() {
		return ticket_list;
	}

	public void setTicket(ArrayList<Ticket> ticket_list) {
		this.ticket_list = ticket_list;
	}

	public void addTicket(Ticket t){this.ticket_list.add(t);}

	
	// Print reciept in dialog box
	public String receiptToString() {
		String receipt = "\n";
		for(int i=0; i<ticket_list.size(); i++) {
			receipt += "Ticket ID: " + ticket_list.get(i).getID_of_ticket() + "\nMovie: " + ticket_list.get(i).getmovie().gettitle_of_movie() + "\nTheatre: " +
					ticket_list.get(i).getShowtime().getaudi().getTheatre().getname_of_theatre() + "\nScreening Room: " + ticket_list.get(i).getShowtime().getaudi().getAuditoriumID() + "\nSeat: " + ticket_list.get(i).getSeat_select() +"\n";
		}
		return receipt;
	}

	public void printReceipt() {
		System.out.println(receiptToString());
	}

}
