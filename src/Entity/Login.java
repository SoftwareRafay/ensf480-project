/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Entity;

import database.ControlDB;

import java.io.Serializable;
import java.time.LocalDate;

public class Login implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ControlDB dataController;
	private RegisteredUser currentRegisteredUser;
	private GuestUser currentGuestUser;

	public Login() {
		dataController = ControlDB.getobject();
	}

	// Sets current user to Guest User
	public void guestUser() {
		currentGuestUser = new GuestUser();
		currentRegisteredUser = null;
	}

	// Verifies the login info entered is a valid entry stored on the DB, ie. this is an existing RU 
	public RegisteredUser login_verification(String username, String password) {
		for (int i = 0; i < dataController.getlist_of_users().size(); i++) {
			if (dataController.getlist_of_users().get(i).username.toLowerCase().compareTo(username.toLowerCase()) == 0
					&& dataController.getlist_of_users().get(i).password.compareTo(password) == 0) {
				currentRegisteredUser = dataController.getlist_of_users().get(i);
				currentGuestUser = null;
				return dataController.getlist_of_users().get(i);
			}
		}
		return null;
	}

	// Clears the current user
	public void logout_user() {
		currentGuestUser = null;
		currentRegisteredUser = null;
	}

	public void user_registration(String username, String password, String f_name, String l_name, String email, String name,
		 String cardNum) {
		LocalDate todaysDate = LocalDate.now();
		UserBankInfo bankInfo = new UserBankInfo(name, cardNum);
		dataController.getlist_of_users().add(new RegisteredUser(username, password, f_name, l_name, email, bankInfo,

		new Date(todaysDate.getDayOfMonth(), todaysDate.getMonthValue(), todaysDate.getYear() + 1)));
	}

	public boolean check_if_user_is_existing(String username) {
		for (int i = 0; i < dataController.getlist_of_users().size(); i++) {
			if (dataController.getlist_of_users().get(i).username.compareTo(username) == 0) {
				return true;
			}
		}
		return false;
	}

	// Getters and Setters
	public RegisteredUser getCurrentRegisteredUser() {
		return currentRegisteredUser;
	}

	public void setCurrentRegisteredUser(RegisteredUser currentRegisteredUser) {
		this.currentRegisteredUser = currentRegisteredUser;
	}

	public ControlDB getDataController() {
		return dataController;
	}

	public GuestUser getCurrentGuestUser() {
		return currentGuestUser;
	}

	public User getCurrentUser() {
		if (currentRegisteredUser != null) {
			return currentRegisteredUser;
		} else {
			return currentGuestUser;
		}
	}

	public void setCurrentGuestUser(GuestUser currentGuestUser) {
		this.currentGuestUser = currentGuestUser;
	}
}
