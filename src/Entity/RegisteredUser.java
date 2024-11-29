/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Entity;

public class RegisteredUser extends User
{
	String username;
	String password;
	String first_name;
	String last_name;
	String gmail_of_user;
	Date date_of_feeDeposit;
	UserBankInfo bankInfo;
	

	public RegisteredUser() {
		super("Registered");
	}

	public RegisteredUser(String u, String p, String f, String l, String e, UserBankInfo b, Date a) {
		super("Registered");
		username = u;
		password = p;
		first_name = f;
		last_name = l;
		gmail_of_user = e;
		bankInfo = b;
		date_of_feeDeposit = a;
	}
	public RegisteredUser(int id,String u, String p, String f, String l, String e, UserBankInfo b, Date a) {
		super(id,"Registered");
		username = u;
		password = p;
		first_name = f;
		last_name = l;
		gmail_of_user = e;
		bankInfo = b;
		date_of_feeDeposit = a;
	}

	
	//Has RU paid annual fee?
	public boolean isFeePayed() {
		return date_of_feeDeposit.is_before_CurrentDate();
	}

	// Getters and Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String u) {
		this.username = u;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String p) {
		this.password = p;
	}

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String f) {
		this.first_name = f;
	}

	public String getlast_name() {
		return last_name;
	}

	public void setlast_name(String l) {
		this.last_name = l;
	}

	public String getgmail_of_user() {
		return gmail_of_user;
	}

	public void setgmail_of_user(String e) {
		this.gmail_of_user = e;
	}

	public UserBankInfo getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(UserBankInfo b) {
		this.bankInfo = b;
	}

	public Date getdate_of_feeDeposit() {
		return date_of_feeDeposit;
	}

	public void setdate_of_feeDeposit(Date a) {
		this.date_of_feeDeposit = a;
	}

}
