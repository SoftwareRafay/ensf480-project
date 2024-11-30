/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Entity;

public class Seat {
	private int selected_row;
	private int selected_column;
	private boolean seat_booked_or_not;

	public Seat(int row, int seatNum) {
		this.selected_row = row;
		this.selected_column = seatNum;
		seat_booked_or_not = false;
	}

	public Seat(int row, int seatNum, int booked) {
		this.selected_row = row;
		this.selected_column = seatNum;
		seat_booked_or_not = true;
	}



	public void bookSeat() {
		seat_booked_or_not = true;
	}

	public void vacateSeat() {
		seat_booked_or_not = false;
	}
	
	//Print seat
	@Override
	public String toString() {
		return String.format("Row: " + selected_row + " Seat: " + selected_column);
	}
	// Getters and Setters
	public int getSelected_row() {
		return selected_row;
	}

	public void setSelected_row(int row) {
		this.selected_row = row;
	}

	public int getSelected_column() {
		return selected_column;
	}

	public void setSelected_column(int seatNum) {
		this.selected_column = seatNum;
	}

	public boolean isSeat_booked_or_not() {
		return seat_booked_or_not;
	}

	public void setSeat_booked_or_not(boolean seatTaken) {
		this.seat_booked_or_not = seatTaken;
	}

	
}
