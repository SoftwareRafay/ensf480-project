/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Entity;

public class Movie {

	private int ID_of_movie;
	private static int counter_for_movie = 200; //keeps track of IDs when new movies added
	private String title_of_movie;
	private String genre_of_movie;
	private String poster;	
	private double price;	
	private String synopsis;
	private Announcement movieAnnouncement;

	public Movie(String title_of_movie, String genre_of_movie, String poster, double p, String syn) {
		this.ID_of_movie = counter_for_movie++;
		this.title_of_movie = title_of_movie;
		this.genre_of_movie = genre_of_movie;
		this.setPoster(poster);
		this.setPrice(p);
		this.synopsis = syn;
		this.movieAnnouncement = null;
	}

	public Movie(int id,String title_of_movie, String genre_of_movie,String poster, double p, String syn) {
		this.ID_of_movie = id;
		counter_for_movie++;
		this.title_of_movie = title_of_movie;
		this.genre_of_movie = genre_of_movie;
		this.setPoster(poster);
		this.setPrice(p);
		this.synopsis = syn;
		this.movieAnnouncement = null;
	}

	// Getters and Setters
	public int getID_of_movie()
	{ 
		return ID_of_movie; 
	}
	
	public String getgenre_of_movie() {
		return genre_of_movie;
	}

	public void setgenre_of_movie(String genre_of_movie) {
		this.genre_of_movie = genre_of_movie;
	}

	public String gettitle_of_movie() {
		return title_of_movie;
	}

	public void settitle_of_movie(String title_of_movie) {
		this.title_of_movie = title_of_movie;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Announcement getMovieAnnouncement() { return movieAnnouncement; }

	public void setMovieAnnouncement(Announcement movieAnnouncement) { this.movieAnnouncement = movieAnnouncement; }

	@Override
	public String toString() {
		return String.format(title_of_movie);
	}

};
