package cs414.a5.nithya.server;

import java.io.Serializable;
import java.util.Calendar;

import cs414.a5.nithya.common.ExitKiosk;

public class ExitKioskImpl implements Serializable, ExitKiosk {
	private String kioskNumber;
	private boolean exitGate;
	private Register register;

	
	
	public ExitKioskImpl(String number, Register register)
	{
		kioskNumber=number;
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
	
	
}
