/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */


package Entity;

public class Payment {
	private int ID_for_payment;
	private double payment_calculated;
	private UserBankInfo card_info;	
	private static int pCounter = 500;

	public Payment(double a, UserBankInfo c) {
		ID_for_payment = pCounter++;
		payment_calculated = a;
		card_info = c;
	}

	public Payment(int i, double a, UserBankInfo c) {
		ID_for_payment = i;
		payment_calculated = a;
		card_info = c;
		pCounter++;
	}

}
