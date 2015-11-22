package cs414.a5.nithya.server;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
	private Set<EntryKiosk> entryKioskSet;
	private Set<ExitKiosk> exitKioskSet;
	private EntryKiosk entryKiosk;//current entry kiosk
	private ExitKiosk exitKiosk;// current exit kiosk
	private Register register;
	private Admin admin;
	private EntryKiosk entryKiosk1;
	private EntryKiosk entryKiosk2;
	private ExitKiosk exitKiosk1;
	private ExitKiosk exitKiosk2;
	
	
	
	
	public GarageImpl(String name, int numberOfParkingLots, Set<EntryKiosk> entryKioskGroup, Set<ExitKiosk> exitKioskGroup, Register register, Admin admin) throws RemoteException
	{
		this.name=name;
	
		for(int i=1; i<= numberOfParkingLots; i++)
		{
			parkingLots.put(i, "empty");
		}
		this.totalOccupiedSpaces=0;
        this.totalUnoccupiedSpaces=numberOfParkingLots;
		this.garageStatus= GarageStatus.available;
		for(EntryKiosk entryK: entryKioskGroup)
		{
			this.entryKioskSet.add(entryK);
		}
		for(ExitKiosk exitK: exitKioskGroup)
		{
			this.exitKioskSet.add(exitK);
		}
		this.register=register;
		this.admin=admin;
	}
	
	
	public GarageImpl() throws RemoteException
	{
		Register register= new Register();
		entryKiosk1= new EntryKioskImpl("en1",register);
		entryKiosk2= new EntryKioskImpl("en2",register);
		exitKiosk1= new ExitKioskImpl("ex1", register);
		exitKiosk2= new ExitKioskImpl("ex2", register);
		Set<EntryKiosk> entryKioskGroup= new HashSet<EntryKiosk>();
		entryKioskGroup.add(entryKiosk1);
		entryKioskGroup.add(entryKiosk2);
		Set<ExitKiosk> exitKioskGroup= new HashSet<ExitKiosk>();
		exitKioskGroup.add(exitKiosk1);
		exitKioskGroup.add(exitKiosk2);
		
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
		this.register=register;
		this.admin=admin;
	}
	
	public void setCurrenKiosk(String kioskName) throws RemoteException
	{
		if(kioskName.equals("en1"))
			this.entryKiosk=entryKiosk1;
		if(kioskName.equals("en2"))
			this.entryKiosk=entryKiosk2;
		if(kioskName.equals("ex1"))
			this.exitKiosk=exitKiosk1;
		if(kioskName.equals("ex2"))
			this.exitKiosk=exitKiosk2;
		
		
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
	public float payParkingFeeByCash( int ticketReferenceNum, float amount) throws RemoteException, CustomException
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
	public boolean payParkingFeeByCard(int ticketReferenceNum, Long cardNumber, Date expiryDate) throws RemoteException, CustomException
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
	
	public int findBusiestHourOfMonth(Calendar month) throws RemoteException, ParseException
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
