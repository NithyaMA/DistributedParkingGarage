package cs414.a5.nithya.common;


public interface EntryKiosk extends java.rmi.Remote {
	
	public boolean isEntryGate() throws java.rmi.RemoteException;
	
	public Ticket generateTicketandOpenGate(Customer customer,int assignedParkingLot) throws java.rmi.RemoteException;

	public void openEntryGate() throws java.rmi.RemoteException;
	
	public void closeEntryGate() throws java.rmi.RemoteException;
	
	public String getName() throws java.rmi.RemoteException;
	
	 
	 
}
