package cs414.a5.nithya.tests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import cs414.a5.nithya.common.Garage;
import cs414.a5.nithya.common.Ticket;
import cs414.a5.nithya.server.GarageImpl;
import cs414.a5.nithya.server.Payment;
import cs414.a5.nithya.server.Register;

public class RegisterTest {

	private Garage garage;
	private Register register;
	private Set<Ticket> ticketSet;
	@Before
	public void setUp() throws RemoteException
	{
	
		garage= new GarageImpl();
		register= new Register();
		ticketSet= new HashSet<Ticket>();
		
		
	}

	@Test
	public void testDailyReportWhileGarageIsEmpty() throws RemoteException
	{
		Calendar cal=Calendar.getInstance();
		ticketSet=register.generateReport("daily", cal);
		assertTrue(ticketSet.isEmpty());
		
	}
	@Test
	public void testHourlyReportWhileGarageIsEmpty() throws RemoteException
	{
		Calendar cal=Calendar.getInstance();
		ticketSet=register.generateReport("hourly", cal);
		assertTrue(ticketSet.isEmpty());
		
	}
	
	@Test
	public void testMonthlyReportWhileGarageIsEmpty() throws RemoteException
	{
		Calendar cal=Calendar.getInstance();
		ticketSet=register.generateReport("monthly", cal);
		assertTrue(ticketSet.isEmpty());
		
	}
	
	@Test
	public void testWeeklyReportWhileGarageIsEmpty() throws RemoteException
	{
		Calendar cal=Calendar.getInstance();
		ticketSet=register.generateReport("hourly", cal);
		assertTrue(ticketSet.isEmpty());
		
	}
}
