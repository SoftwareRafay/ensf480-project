/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Entity;

public class User
{

	private static int userCounter = 0;
	private int ID_of_User;
	private String reg_or_unreg_user;
	private TicketCart cart;

	//Setting the type of User as Registered User or Guest User
	User(String type)
	{
		this.cart = new TicketCart();
		this.ID_of_User = userCounter++;
		this.reg_or_unreg_user = type;
	}

	
	User(int id, String type)
	{
		this.ID_of_User = id;
		if(id > userCounter)
		{
			userCounter = id+1;
		}else
		{
			userCounter++;
		}
		this.reg_or_unreg_user = type;
		this.cart = new TicketCart();
	}

	// Getters and Setters
	public String getreg_or_unreg_user()
	{
		return reg_or_unreg_user;
	}

	public void setreg_or_unreg_user(String reg_or_unreg_user)
	{
		this.reg_or_unreg_user = reg_or_unreg_user;
	}

	public int getID_of_User()
	{
		return ID_of_User; 
	}

	public void setID_of_User(int ID_of_User) 
	{ 
		this.ID_of_User = ID_of_User; 
	}

	public TicketCart getCart()
	{
		return cart;
	}

	public void setCart(TicketCart cart)
	{
		this.cart = cart;
	}
}
