
/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Entity;

import java.util.ArrayList;

public class Bank {
	private String name;
	private ArrayList<UserBankInfo> bankAccounts;

	public Bank(String n) {
		setName(n);
		setBankAccounts(new ArrayList<UserBankInfo>());
	}

	// Add bank accounts at Bank
	public void account_addition(UserBankInfo ba)
	{
		bankAccounts.add(ba);
	}
	
	//If the name contains only letters return valid. If card number contains 
	//only numbers return valid.
	public boolean CardInfo_verification(String name, String num) {
		boolean validName = name.matches("[0-9]+");
		boolean validCard= num.matches("[0-9]+"); 
		
		if(validCard &&!validName)  {
			return true;
		}
		else{
			return false;
		}
		
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<UserBankInfo> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(ArrayList<UserBankInfo> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
}


