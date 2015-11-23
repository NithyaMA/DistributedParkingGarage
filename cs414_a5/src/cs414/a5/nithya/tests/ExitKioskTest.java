package cs414.a5.nithya.tests;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import cs414.a5.nithya.common.ExitKiosk;
import cs414.a5.nithya.common.Ticket;
import cs414.a5.nithya.server.ExitKioskImpl;
import cs414.a5.nithya.server.GarageImpl;
import cs414.a5.nithya.server.Register;

public class ExitKioskTest {

	private ExitKiosk exk;
	private Register register;
	
	@Before
	public void setUp() throws RemoteException
	{
	
		register= new Register();
		exk= new ExitKioskImpl("ex1", register);
		
		
	}

	@Test
	public void testOpenExitGate() throws RemoteException
	{
		exk.openExitGate();
		assertTrue(exk.isExitGate());
	}
	
	@Test
	public void testCloseExitGate() throws RemoteException
	{
		exk.closeExitGate();
		assertFalse(exk.isExitGate());
	}
}
