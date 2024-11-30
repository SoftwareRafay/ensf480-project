/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Entity;

import java.util.*;

public class TicketCart
{
	private Payment pay;
	private float totalCost;
	private ArrayList<Reservation> items;

	public TicketCart()
	{
		items = new ArrayList<Reservation>();
		pay = null;
		totalCost = 0;
	}

	public TicketCart(ArrayList<Reservation> items, Payment pay)
	{
		items = new ArrayList<Reservation>();
		for (int i = 0; i < items.size(); i++)
		{
			items.add(items.get(i));
		}
		setPay(pay);
		for (Reservation cartItem : items)
		{
			totalCost += 13.99;
		}
	}

	
	public void addToCart(Reservation item)
	{
		items.add(item);
	}

	
	public void removeFromCart(Reservation item)
	{
		Iterator<Reservation> it = items.iterator();
		while (it.hasNext()) {
			Reservation currentTicket = (Reservation) it.next();
			if (item.getBookingID() == currentTicket.getBookingID()) {
				it.remove();
				return;
			}
		}
		return;
	}

	// Getters and Setters
	public Payment getPay()
	{
		return pay;
	}

	public void setPay(Payment pay)
	{
		this.pay = pay;
	}

	public float gettotalCost()
	{
		return totalCost;
	}

	public float calculatetotalCost()
	{
		totalCost = 0;
		for (Reservation cartItem : items)
		{
			totalCost += 24.99;
		}
		return totalCost;
	}

	public void settotalCost(float totalCost)
	{
		this.totalCost = totalCost;
	}
	public ArrayList<Reservation> getitems()
	{
		return items;
	}

	public void setitems(ArrayList<Reservation> items) 
	{

		this.items = items;
	}
}