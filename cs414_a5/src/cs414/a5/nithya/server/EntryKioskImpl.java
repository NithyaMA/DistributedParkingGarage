package cs414.a5.nithya.server;

import java.io.Serializable;
import java.rmi.RemoteException;

import cs414.a5.nithya.common.*;

public class EntryKioskImpl implements  Serializable, EntryKiosk {
	private String kioskName;
	private boolean entryGate;
	private Register register;
	
	public EntryKioskImpl(String name, Register register)
	{
		kioskName=name;
		entryGate=false;
		this.register=register;
		
	}
	public Ticket generateTicketandOpenGate(Customer customer,int assignedParkingLot)
	{
		Ticket ticket= new TicketImpl(customer, assignedParkingLot, this.register);
		
		this.openEntryGate();
		return ticket;
	}
	
	 public void openEntryGate()
	 {
		 entryGate=true;
	 }
	 
	 public void closeEntryGate()
	 {
		 entryGate=false;
	 }
	 


	public boolean isEntryGate() {
		return entryGate;
	}
	public Register getRegister() {
		return register;
	}
	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return kioskName;
	}
	
	
	

}
