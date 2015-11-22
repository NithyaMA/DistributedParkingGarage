package cs414.a5.nithya.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;

import cs414.a5.nithya.common.ExitKiosk;

public class ExitKioskImpl implements Serializable, ExitKiosk {
	private String kioskName;
	private boolean exitGate;
	private Register register;

	
	
	public ExitKioskImpl(String name, Register register)
	{
		kioskName=name;
		exitGate=false;
		this.register= register;
		
	}
	
	
	 public void openExitGate()
	 {
		 exitGate=true;
	 }
	 public void closeExitGate()
	 {
		 exitGate=false;
	 }
	 

	


		public boolean isExitGate() {
			return exitGate;
		}


		@Override
		public String getName() throws RemoteException {
			// TODO Auto-generated method stub
			return this.kioskName;
		}
	
	
}
