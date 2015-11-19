package cs414.a5.nithya.common;

import java.util.Calendar;

import cs414.a5.nithya.server.TicketStatus;

public interface Ticket extends java.rmi.Remote{

	public Calendar getTimeOfEntry() throws java.rmi.RemoteException;


	public int getAssignedParkingLot() throws java.rmi.RemoteException;
	
	public float getParkingRate() throws java.rmi.RemoteException;


	public  int getTicketReferenceNumber() throws java.rmi.RemoteException;

	public float getTotalParkingFee() throws java.rmi.RemoteException;
	
	public Calendar getTimeOfExit() throws java.rmi.RemoteException;
	
	public void calculateTotalParkingFee() throws java.rmi.RemoteException;
	
	public void setTimeOfEntry(Calendar timeOfEntry)  throws java.rmi.RemoteException;
	
	public void setTimeOfExit(Calendar timeOfExit) throws java.rmi.RemoteException;
	
	public TicketStatus getTicketStatus() throws java.rmi.RemoteException;
	
	public Customer getCustomer() throws java.rmi.RemoteException;
	
	public void setTicketStatus(TicketStatus ticketStatus) throws java.rmi.RemoteException;
}
