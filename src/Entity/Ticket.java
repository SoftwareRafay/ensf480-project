/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Entity;

public class Ticket
{
	private Seat seat_select;
	private int ID_of_ticket;
	private Movie movie;
	private Showtime showtime;
	private Receipt receipt;
	
	private static int ticketCounter = 900;

	public Ticket(Movie mov, Showtime s, Seat s1)
	{
		ID_of_ticket = ticketCounter++;
		movie = mov;
		showtime = s;
		seat_select = s1;
		receipt = null;
	}

	public Ticket(int id, Payment p, Movie m, Showtime s, Seat s1, Receipt r)
	{
		ID_of_ticket = id;
		if(id> ticketCounter){
			ticketCounter = id+1;
		}else {
			ticketCounter++;
		}
		movie = m;
		showtime = s;
		seat_select = s1;
		receipt = r;
		
	}
	
	// Getters and Setters
	public Seat getSeat_select()
	{
		return seat_select;
	}

	public void setSeat_select(Seat seat_select)
	{
		this.seat_select = seat_select;
	}

	public int getID_of_ticket()
	{
		return ID_of_ticket;
	}

	public void setID_of_ticket(int ID_of_ticket)
	{
		this.ID_of_ticket = ID_of_ticket;
	}

	public Movie getmovie()
	{
		return movie;
	}

	public void setmovie(Movie movie)
	{
		this.movie = movie;
	}

	public Showtime getShowtime()
	{
		return showtime;
	}

	public void setShowtime(Showtime showtime)
	{
		this.showtime = showtime;
	}

	public Receipt getReceipt()
	{ 
		return receipt;
	}

	public void setReceipt(Receipt receipt)
	{ 
		this.receipt = receipt; 
	}
}
