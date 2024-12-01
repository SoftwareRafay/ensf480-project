/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

 package Entity;

 import java.util.ArrayList;
 
 public class Theatre {
   private static int counter_for_theatre = 400;
   private String name_of_theatre;
   private int ID_for_theatre;
   private ArrayList < ScreeningRoom > auditoriums_list;
 
   public Theatre(int ID_for_theatre, String theatreName) {
	 this.name_of_theatre = theatreName;
	 this.ID_for_theatre = ID_for_theatre;
	 if (ID_for_theatre > counter_for_theatre) {
	   counter_for_theatre = ID_for_theatre + 1;
	 } else {
	   counter_for_theatre++;
	 }
	 this.setauditoriums_list(new ArrayList < ScreeningRoom > ());
 
   }
 
   public Theatre(String theatreName, ArrayList < ScreeningRoom > auditoriums_list) {
	 this.name_of_theatre = theatreName;
	 this.ID_for_theatre = counter_for_theatre++;
	 this.setauditoriums_list(auditoriums_list);
 
   }
 
   public Theatre(int ID_for_theatre, String theatreName, ArrayList < ScreeningRoom > auditoriums_list) {
	 this.name_of_theatre = theatreName;
	 this.ID_for_theatre = ID_for_theatre;
	 if (ID_for_theatre > counter_for_theatre) {
	   counter_for_theatre = ID_for_theatre + 1;
	 } else {
	   counter_for_theatre++;
	 }
	 this.setauditoriums_list(auditoriums_list);
 
   }
 
   // Getters and Setters
   public String getname_of_theatre() {
	 return name_of_theatre;
   }
 
   public void setname_of_theatre(String name_of_theatre) {
	 this.name_of_theatre = name_of_theatre;
   }
 
   public int getID_for_theatre() {
	 return ID_for_theatre;
   }
 
   public void setID_for_theatre(int ID_for_theatre) {
	 this.ID_for_theatre = ID_for_theatre;
   }
 
   public ArrayList < ScreeningRoom > getauditoriums_list() {
	 return auditoriums_list;
   }
 
   public void setauditoriums_list(ArrayList < ScreeningRoom > auditoriums_list) {
	 this.auditoriums_list = auditoriums_list;
   }
 };