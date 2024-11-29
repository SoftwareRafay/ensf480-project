/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Entity;

public class Reservation
{
	private Movie movie_to_book;
	private Showtime showing_time;
	private Seat seat_to_book;
	private int bookingID;
	private static int count_bookings = 0;

	
	public Reservation() {
		movie_to_book = null;
		showing_time = null;
		seat_to_book = null;
		bookingID = -1;

	}

	public Reservation(Movie m, Showtime t, Seat s) {
		movie_to_book = m;
		showing_time = t;
		seat_to_book = s;
		bookingID = count_bookings++;
	}

	// Getters and Setters
	public int getBookingID()
	{
		return bookingID;
	}

	public void setBookingID(int bookingID)
	{
		this.bookingID = bookingID;
	}

	public Seat getseat_to_book() {
		return seat_to_book;
	}

	public void setseat_to_book(Seat seat_to_book) {
		this.seat_to_book = seat_to_book;
	}

	public Showtime getshowing_time() {
		return showing_time;
	}

	public void setshowing_time(Showtime showing_time) {
		this.showing_time = showing_time;
	}

	public Movie getmovie_to_book() {
		return movie_to_book;
	}

	public void setmovie_to_book(Movie movie_to_book) {
		this.movie_to_book = movie_to_book;
	}
}
