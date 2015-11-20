package cs414.a5.nithya.server;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



import cs414.a5.nithya.common.CustomException;
import cs414.a5.nithya.common.Customer;
import cs414.a5.nithya.common.Garage;
import cs414.a5.nithya.common.Ticket;
import cs414.a5.nithya.common.EntryKiosk;
import cs414.a5.nithya.common.ExitKiosk;




public class GarageImpl extends java.rmi.server.UnicastRemoteObject implements Garage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3183452443490996223L;
	private String name;
	private GarageStatus garageStatus;
	private int totalOccupiedSpaces;
	private int totalUnoccupiedSpaces;
	private Map <Integer, String>parkingLots= new HashMap<Integer, String>();
	private EntryKiosk entryKiosk;
	private ExitKiosk exitKiosk;
	private Register register;
	private Admin admin;
	
	
	
	
	public GarageImpl(String name, int numberOfParkingLots, EntryKiosk entryKiosk, ExitKiosk exitKiosk, Register register, Admin admin) throws RemoteException
	{
		this.name=name;
	
		for(int i=1; i<= numberOfParkingLots; i++)
		{
			parkingLots.put(i, "empty");
		}
		this.totalOccupiedSpaces=0;
        this.totalUnoccupiedSpaces=numberOfParkingLots;
		this.garageStatus= GarageStatus.available;
		this.entryKiosk=entryKiosk;
		this.exitKiosk=exitKiosk;
		this.register=register;
		this.admin=admin;
	}
	
	
	public GarageImpl() throws RemoteException
	{
		Register register= new Register();
		EntryKiosk entryKiosk= new EntryKioskImpl("en1",register);
		ExitKiosk exitKiosk= new ExitKioskImpl("en2", register);
		Admin admin= new Admin("admin", "admin123", register);
		String name= "CS414";
		int numberOfParkingLots= 3;
		
		this.name=name;
		
		for(int i=1; i<= numberOfParkingLots; i++)
		{
			parkingLots.put(i, "empty");
		}
		this.totalOccupiedSpaces=0;
        this.totalUnoccupiedSpaces=numberOfParkingLots;
		this.garageStatus= GarageStatus.available;
		this.entryKiosk=entryKiosk;
		this.exitKiosk=exitKiosk;
		this.register=register;
		this.admin=admin;
	}
	
	@Override
	public Ticket enterGarage(Customer customer) throws RemoteException, CustomException

	{	
		if(garageStatus.equals(GarageStatus.available))
		{
		int assignedParkingLot=0;
		
		for(int key: parkingLots.keySet())
		{
			if(parkingLots.get(key).equals("empty"))
			{ 
				assignedParkingLot= key;
				parkingLots.put(key, "full");
				break;	
			}
			
		}
		
		Ticket generatedTicket= this.entryKiosk.generateTicketandOpenGate(customer, assignedParkingLot);
		totalOccupiedSpaces+=1;
		totalUnoccupiedSpaces-=1;
		updateStatus();
		
		return generatedTicket;
		}
		else
			throw new CustomException("The garage is full");
			
	}
	
	
	
	@Override
	public Ticket validateTicketForExitingGarage(int ticketReferenceNumber, String vehicleNumber) throws RemoteException, CustomException
	{
		return register.validateTicket(ticketReferenceNumber,  vehicleNumber);
	
	}
	
	@Override
	public float payParkingFeeByCash( int ticketReferenceNum, float amount) throws RemoteException
	{
		
		Ticket ticket=register.getSpecificTicket(ticketReferenceNum);
		float fee=ticket.getTotalParkingFee();
		Payment payment= new Payment();
		float balanceDue=payment.makePaymentByCash(fee, amount);
		ticket.setTicketStatus(TicketStatus.paid);
		totalOccupiedSpaces-=1;
		totalUnoccupiedSpaces+=1;
		parkingLots.put(ticket.getAssignedParkingLot(),"empty");
		updateStatus();
		exitKiosk.openExitGate();
		return balanceDue;
	}
	
	@Override
	public boolean payParkingFeeByCard(int ticketReferenceNum, Long cardNumber, Date expiryDate) throws RemoteException
	{
		Ticket ticket=register.getSpecificTicket(ticketReferenceNum);
		Payment payment= new Payment();
		if(payment.makePaymentByCard(cardNumber, expiryDate))
		{
			ticket.setTicketStatus(TicketStatus.paid);
			totalOccupiedSpaces-=1;
			totalUnoccupiedSpaces+=1;
			parkingLots.put(ticket.getAssignedParkingLot(),"empty");
			updateStatus();
			exitKiosk.openExitGate();
			return true;
		}
		
		 return false;
	}
	
	public Set<Ticket> generateReport(String choice, Calendar start) throws RemoteException
	{
		return register.generateReport(choice, start);
	}
	
	public void updateStatus()
	{
		if(parkingLots.containsValue("empty"))
		this.garageStatus= GarageStatus.available;
		else
			this.garageStatus= GarageStatus.full;
	}
	
	public int findBusiestHourOfMonth(Calendar month) throws RemoteException
	{
		return register.findBusiestHourOfTheMonth(month);
	}
	public void activateSensor(String choiceOfGate) throws RemoteException
	{
		if (choiceOfGate.equals("entry"))
		{
			entryKiosk.closeEntryGate();
		}
		else if(choiceOfGate.equals("exit"))
		{
			exitKiosk.closeExitGate();
		}
	}
	
	public boolean authorizeAdmin(String userName, String password)
	{
		return this.admin.authorizeAdmin(userName, password);
	}
	
	public void stimulateTime(String testingChoice, Calendar cal, int ticketNum) throws CustomException, RemoteException
	{
		if (testingChoice.equals("entry"))
		{
			admin.stimulateTimeForEntry(cal, ticketNum);
		}
		else if (testingChoice.equals("exit"))
		{
			admin.stimulateTimeForExit(cal, ticketNum);
		}
		else
			throw new CustomException("Testing choice provided is not correct. Please try again");
	}
	
	public Ticket helpCustomerToReprintTicket(String vehicleNumber) throws RemoteException
	{
		return admin.helpCustomerToRePrintTicket(vehicleNumber);
	}
	
	public float lendMoneyToCashlessCustomerToExitGarage(Integer ticketRefNum) throws RemoteException
	{
		return admin.lendMoneyToCashlessCustomerToExitGarage(ticketRefNum);
	}
	public String getName() {
		return name;
	}
	public String getGarageStatus() {
		return garageStatus.name();
	}
	public int getTotalOccupiedSpaces() {
		return totalOccupiedSpaces;
	}
	public int getTotalUnoccupiedSpaces() {
		return totalUnoccupiedSpaces;
	}
	public EntryKiosk getEntryKiosk() {
		return entryKiosk;
	}
	public ExitKiosk getExitKiosk() {
		return exitKiosk;
	}
	
	public boolean isGarageFull()
	{
		return garageStatus.equals(GarageStatus.full);
	}
}
