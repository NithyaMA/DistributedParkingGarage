package cs414.a5.nithya.tests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import cs414.a5.nithya.common.*;
import cs414.a5.nithya.server.*;
public class EntryKioskTest {


	private EntryKioskImpl entryKioskImpl;
	private Customer cust1;
	private Register register;

	
	@Before
	public void setUp() throws CustomException
	{
		register= new Register();
		entryKioskImpl= new EntryKioskImpl("en1",register);
		cust1= new Customer("john","1245689752","john@gmail.com","aa11");
		
		
		
	}

	@Test
	public void testGenerateTicketAndOpenGate() throws RemoteException {
		Ticket t=entryKioskImpl.generateTicketandOpenGate(cust1,  1);
		assertTrue(t.getTicketStatus().equals(TicketStatus.pending));
		try
		{
			t.getTimeOfEntry().getTime();
		}
		catch(NullPointerException e)
		{
			fail("should not throw a null pointer exception");
		}
		try
		{
			t.getTimeOfExit().getTime();
			fail("should throw a null pointer exception");
		}
		catch(NullPointerException e)
		{
			
		}
		assertTrue(t.getAssignedParkingLot()==1);
		assertTrue(t.getCustomer().equals(cust1));
		assertTrue(entryKioskImpl.isEntryGate());
	}



}
