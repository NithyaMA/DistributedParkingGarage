package cs414.a5.nithya.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import cs414.a5.nithya.common.*;

public class Admin implements Serializable{

	private String userName;
	private String password;
	private Register register;

	
	
	public Admin(String userName, String password, Register register)
	{
		this.userName=userName;
		this.password=password;
		this.register=register;
	}
	
	public boolean authorizeAdmin(String userName, String password)
	{
		if(  (this.userName.equals(userName)) &&  (this.password.equals(password)) )
				return true;
		else 
			throw new CustomException("Username and password does not match. Please try again");
	}
	
	
	public void  stimulateTimeForEntry(Calendar cal, int ticketNum) throws RemoteException
	{
		
		for(Ticket t: this.register.getTickets())
		{
			if(t.getTicketReferenceNumber()==ticketNum)
			{
				t.setTimeOfEntry(cal);
				break;
			}
		}
	
}
	
	public void  stimulateTimeForExit(Calendar cal, int ticketNum) throws RemoteException
	{
		for(Ticket t: this.register.getTickets())
		{
			if(t.getTicketReferenceNumber()==ticketNum)
			{
				t.setTimeOfExit(cal);
				break;
			}
		}
	
}
	
	public Ticket helpCustomerToRePrintTicket(String vehicleNumber) throws RemoteException
	{
		Ticket rePrintedTicket= null;
		for(Ticket t: this.register.getTickets())
		{
			if(t.getCustomer().getvehicleNumber().equals(vehicleNumber))
			{
				rePrintedTicket= t;
				break;
			}
		}
		return rePrintedTicket;
	
	}
	
	public float lendMoneyToCashlessCustomerToExitGarage(Integer ticketRefNum) throws RemoteException
	{
		float loanAmount=(float) 0.0;
		for(Ticket t: this.register.getTickets())
		{
			if(t.getTicketReferenceNumber()==ticketRefNum)
				
			{
				loanAmount=t.getTotalParkingFee();
				
			}
			
		}
		return loanAmount;
	}
	public class CustomException extends RuntimeException
	{
		
		private static final long serialVersionUID = 1L;
		
		public CustomException(String message)
		{
			super(message);
		}
	}
}