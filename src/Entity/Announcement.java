/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Entity;


public class Announcement {

	private int ID_for_announcement;
	private static int counter_for_announcement = 800;
	private Date prAnnounceDate;
	private Date puAnnounceDate;
	private Movie announce_movie;

	public Announcement(Date privateD, Date publicD, Movie mov) {
		ID_for_announcement = counter_for_announcement++;
		prAnnounceDate = privateD;
		puAnnounceDate = publicD;
		announce_movie = mov;
		mov.setMovieAnnouncement(this);
	}
	public Announcement(int id,Date privateD, Date publicD, Movie mov) {
		ID_for_announcement = id;
		if(id > counter_for_announcement){
			counter_for_announcement = id+1;
		}else {
			counter_for_announcement++;
		}
		prAnnounceDate = privateD;
		puAnnounceDate = publicD;
		announce_movie = mov;
		mov.setMovieAnnouncement(this);
	}

	
	public boolean isPrivate(){
		return (!prAnnounceDate.is_before_CurrentDate() && puAnnounceDate.is_before_CurrentDate());
	}

	
	public boolean isPublic(){
		return !puAnnounceDate.is_before_CurrentDate();
	}

	//Getters and Setters
	public Date getPrAnnounceDate() { return prAnnounceDate; }

	public void setPrAnnounceDate(Date privateAnnounceDate) { this.prAnnounceDate = privateAnnounceDate; }

	public Date getPuAnnounceDate() { return puAnnounceDate; }

	public void setPuAnnounceDate(Date publicAnnounceDate) { this.puAnnounceDate = publicAnnounceDate; }
	
	
}
