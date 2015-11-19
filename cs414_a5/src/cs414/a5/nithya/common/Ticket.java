package cs414.a5.nithya.common;

import java.util.Calendar;



public interface Ticket extends java.rmi.Remote{


	public Calendar getTimeOfEntry() throws java.rmi.RemoteException;


	public int getAssignedParkingLot() throws java.rmi.RemoteException;
	
	public float getParkingRate() throws java.rmi.RemoteException;


	public  int getTicketReferenceNumber() throws java.rmi.RemoteException;


	//public Customer getCustomer() throws java.rmi.RemoteException;

	public float getTotalParkingFee() throws java.rmi.RemoteException;
}
