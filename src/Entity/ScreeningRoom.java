/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Entity;

public class ScreeningRoom {
	private Theatre theatre;
	private int auditoriumID;
	private int seats_available;
	private int number_of_rows;
	private int number_of_columns;

	private static int auditoriumCounter = 300;

	public ScreeningRoom(int aID, int r, int c, Theatre theatre) {
		auditoriumID = aID;
		if(aID > auditoriumCounter){
			auditoriumCounter = aID+1;
		}else {
			auditoriumCounter++;
		}
		number_of_rows = r;
		number_of_columns = c;
		seats_available = r * c;
		this.theatre = theatre;
		auditoriumCounter++;
	}

	public ScreeningRoom(int r, int c, Theatre theatre) {
		auditoriumID = auditoriumCounter++;
		number_of_rows = r;
		number_of_columns = c;
		seats_available = r * c;
		this.theatre = theatre;
	}

	//Getters and Setters
	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public int getAuditoriumID() {
		return this.auditoriumID;
	}

	public int getseats_available() {
		return this.seats_available;
	}

	public int getnumber_of_rows() {
		return this.number_of_rows;
	}

	public int getnumber_of_columns() {
		return this.number_of_columns;
	}

}
