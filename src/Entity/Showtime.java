/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Showtime {
	private int ID_for_showtime;
	private Date Date_of_show;
	private int time_in_hours;
	private int time_in_minutes;
	private ScreeningRoom audi;
	private int number_of_rows;
	private int number_of_columns;
	private Seat[][] seats;
	private int numer_of_seats_available;
	private Movie movie;

	private static int showtimeCounter = 700;

	public Showtime(int id, Movie mov, ScreeningRoom a, Date d , int h, int m) {
		ID_for_showtime = id++;
		if(id> showtimeCounter){
			showtimeCounter = id+1;
		}else {
			showtimeCounter++;
		}
		Date_of_show = d;
		audi = a;
		time_in_hours = h;
		time_in_minutes = m;
		movie = mov;
		number_of_rows = a.getnumber_of_rows();
		number_of_columns = a.getnumber_of_columns();
		seats = new Seat[number_of_rows][number_of_columns];
		for (int i = 0; i < number_of_rows; i++) {
			for (int j = 0; j < number_of_columns; j++) {
				seats[i][j] = new Seat(i, j);
			}
		}
		numer_of_seats_available = number_of_rows * number_of_columns;
	}

	public Showtime(Date d, ScreeningRoom a, Movie mov, int h, int m) {
		ID_for_showtime = showtimeCounter++;
		Date_of_show = d;
		audi = a;
		time_in_hours = h;
		time_in_minutes = m;
		movie = mov;
		number_of_rows = a.getnumber_of_rows();
		number_of_columns = a.getnumber_of_columns();
		seats = new Seat[number_of_rows][number_of_columns];
		for (int i = 0; i < number_of_rows; i++) {
			for (int j = 0; j < number_of_columns; j++) {
				seats[i][j] = new Seat(i, j);
			}
		}
		numer_of_seats_available = number_of_rows * number_of_columns;
	}

	// Seat avaliability, true if taken, false if not
	public Boolean getSeatAvaliability(int r, int c) {
		if (r > number_of_rows || c > number_of_columns) {
			return false;
		}
		return seats[r][c].isSeat_booked_or_not();
	}

	// Reserve seat if empty
	public Boolean bookSeat(int r, int c) {
		if (seats[r][c].isSeat_booked_or_not() == false) {
			seats[r][c].bookSeat();
			numer_of_seats_available--;
			return true;
		} else {
			return false;
		}
	}

	// Make a seat empty 
	public void vacantSeat(int r, int c) {
		if (seats[r][c].isSeat_booked_or_not() == true)
			numer_of_seats_available++;
		seats[r][c].vacateSeat();
	}

	//Getters and Setters
	public int getID_for_showtime() {
		return ID_for_showtime;
	}

	public Date getDate_of_show() {
		return Date_of_show;
	}

	public void setDate_of_show(Date d) {
		Date_of_show = d;
	}

	public ScreeningRoom getaudi() {
		return audi;
	}

	public void setaudi(ScreeningRoom a) {
		audi = a;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie m) {
		movie = m;
	}

	public int getnumber_of_rows() {
		return this.number_of_rows;
	}

	public int getnumber_of_columns() {
		return this.number_of_columns;
	}

	public Seat[][] getSeats() {
		return this.seats;
	}

	public int getnumer_of_seats_available() {
		return this.numer_of_seats_available;
	}

	public int gettime_in_hours() { return time_in_hours; }

	public void settime_in_hours(int time_in_hours) { this.time_in_hours = time_in_hours; }

	public int gettime_in_minutes() { return time_in_minutes; }

	public void settime_in_minutes(int time_in_minutes) { this.time_in_minutes = time_in_minutes; }

	public void setnumer_of_seats_available(int numer_of_seats_available) { this.numer_of_seats_available = numer_of_seats_available; }

	@Override
	public String toString() {
		return String.format(Date_of_show.toString());
	}
}
