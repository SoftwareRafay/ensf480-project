/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Entity;

public class UserBankInfo
{


	private String Name_of_the_customer;
	private int ID_for_bank;
	private static int counter_for_bank = 100;
	private String payment_card_number;

	// Constructor
	public UserBankInfo( String c,  String cn) {
		this.ID_for_bank = counter_for_bank++;
		Name_of_the_customer = c;
		payment_card_number = cn;
		
	}
	
	// Constructor
	public UserBankInfo(int i, String c,  String cn) {
		this.ID_for_bank = i;
		if(i > counter_for_bank){
			counter_for_bank = i+1;
		}else {
			counter_for_bank++;
		}
		Name_of_the_customer = c;
		payment_card_number = cn;
		
	}


	// Getters and Setters of UserBankInfo instance variables
	public String getpayment_card_number() {
		return payment_card_number;
	}

	public void setpayment_card_number(String payment_card_number) {
		this.payment_card_number = payment_card_number;
	}	

	public String getName_of_the_customer() {
		return Name_of_the_customer;
	}

	public void setName_of_the_customer(String Name_of_the_customer) {
		this.Name_of_the_customer = Name_of_the_customer;
	}

	public int getID_for_bank() { return ID_for_bank; }
}
