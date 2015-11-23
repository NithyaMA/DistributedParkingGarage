package cs414.a5.nithya.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import cs414.a5.nithya.common.CustomException;
import cs414.a5.nithya.common.Customer;
import cs414.a5.nithya.common.EntryKiosk;
import cs414.a5.nithya.common.ExitKiosk;
import cs414.a5.nithya.common.Garage;
import cs414.a5.nithya.common.Ticket;
import cs414.a5.nithya.server.Admin;
import cs414.a5.nithya.server.GarageImpl;
import cs414.a5.nithya.server.GarageStatus;
import cs414.a5.nithya.server.Register;

public class GarageTest {

	private Garage garage;
	private EntryKiosk entryKiosk1;
	
	private ExitKiosk exitKiosk1;
	
	
	private Customer cust1;
	private Customer cust2;
	private Customer cust3;
	private Customer cust4;
	
	

	
	@Before
	public void setUp() throws RemoteException, CustomException
	{
		
		cust1= new Customer("john","1245689752","john@gmail.com","aa11");
		cust2= new Customer("jay","1245649765","jay@gmail.com","aa12");
		cust3= new Customer("jung","1245689769","jung@gmail.com","aa13");
		cust4= new Customer("jose","7245689769","jose@gmail.com","aa14");
		
		garage= new GarageImpl();
		garage.setCurrenKiosk("en1");
		
	}
	@Test
	public void testEnterGarage() throws RemoteException, CustomException {
		
		//test for checking the updates status of garage
		
		garage.enterGarage(cust1);
		assertTrue(garage.getGarageStatus().equals(GarageStatus.available.name()));
		assertTrue(garage.getTotalOccupiedSpaces()==1);
		assertTrue(garage.getTotalUnoccupiedSpaces()==2);
	    garage.enterGarage(cust2);
		garage.enterGarage(cust3);
		assertTrue(garage.getGarageStatus().equals(GarageStatus.full.name()));
		
		//test for car entering while garage is full
		try{
		garage.enterGarage(cust4);
		fail("Should throw custom exception");
		}
		catch(CustomException e)
		{
			assertEquals(e.getMessage(), "The garage is full");
		}
		
	}
	
	
	@Test
	public void testValidateTicketForExitingGarage() throws RemoteException, CustomException
	{
		Ticket t=garage.enterGarage(cust1);
		
		int ticketNum= t.getTicketReferenceNumber();
			
		
		//test for a ticket with wrong vehicle number
		try{
			garage.validateTicketForExitingGarage(ticketNum, "aa");
			fail("should throw an exception");
			}
			catch(CustomException e)
			{
				assertEquals(e.getMessage(), "The ticket does not match with the parked vehicle. Ticket is fake");
			}
		
		
		
		//test for a ticket with wrong reference number
				try{
					garage.validateTicketForExitingGarage(00, "aa");
					fail("should throw an exception");
					}
					catch(CustomException e)
					{
						assertEquals(e.getMessage(), "The ticket reference number does not exist. Ticket is fake");
					}
				
				
		//test for a correct ticket
		try{
		garage.validateTicketForExitingGarage(ticketNum, "aa11");
		
		}
		catch(Exception e)
		{
			fail("should not throw an exception");
		}
	
		
	}
	
	@Test
	public void testactivateSensor() throws RemoteException
	{
		
		garage.activateSensor("entry");
		assertFalse(garage.getEntryKiosk().isEntryGate());
		garage.setCurrenKiosk("ex1");
		garage.activateSensor("exit");
		assertFalse(garage.getExitKiosk().isExitGate());
	}


}
