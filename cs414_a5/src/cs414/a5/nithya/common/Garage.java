package cs414.a5.nithya.common;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;




public interface Garage extends java.rmi.Remote  {

	 Ticket enterGarage(Customer cust) throws java.rmi.RemoteException, CustomException;
	
	 Ticket validateTicketForExitingGarage(int ticketReferenceNumber, String vehicleNumber) throws java.rmi.RemoteException, CustomException;
	
	 float payParkingFeeByCash( int ticketRefNum, float amount) throws java.rmi.RemoteException;
	
	 boolean payParkingFeeByCard(int ticketRefNum, Long cardNumber, Date expiryDate) throws java.rmi.RemoteException;
	
	Set<Ticket>  generateReport(String choice, Calendar start) throws java.rmi.RemoteException;
	
	void updateStatus() throws java.rmi.RemoteException;
	
	public int findBusiestHourOfMonth(Calendar month) throws java.rmi.RemoteException;
	
	public void activateSensor(String choiceOfGate) throws java.rmi.RemoteException;
	
	public boolean authorizeAdmin(String userName, String password) throws java.rmi.RemoteException;
	
	public void stimulateTime(String testingChoice, Calendar cal, int ticketNum) throws java.rmi.RemoteException, CustomException;
	
	public Ticket helpCustomerToReprintTicket(String vehicleNumber) throws java.rmi.RemoteException;
	
	public float lendMoneyToCashlessCustomerToExitGarage(Integer ticketRefNum) throws java.rmi.RemoteException;
	
	public String getName()  throws java.rmi.RemoteException;
	
	public String getGarageStatus() throws java.rmi.RemoteException;
	
	public int getTotalOccupiedSpaces() throws java.rmi.RemoteException;
	
	public int getTotalUnoccupiedSpaces() throws java.rmi.RemoteException;
	
	public EntryKiosk getEntryKiosk() throws java.rmi.RemoteException;
	
	public ExitKiosk getExitKiosk() throws java.rmi.RemoteException;
	
	public boolean isGarageFull() throws java.rmi.RemoteException;
		
	
	
}
