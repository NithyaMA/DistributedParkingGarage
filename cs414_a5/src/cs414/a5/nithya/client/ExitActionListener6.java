package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ExitActionListener6 implements ActionListener {

	
private ExitView exitView;
	
	public ExitActionListener6(ExitView ev)
	{
		this.exitView=ev;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
		exitView.getExitFrame().dispose();
		 
		  try {
			if(exitView.getGarage().getExitKiosk().getName().equals("ex1"))
				  ParkingGarageExit1.createAndShowGUI();
		/*	else if(exitView.getGarage().getEntryKiosk().getName().equals("ex2"))
				  ParkingGarageExit2.createAndShowGUI();*/
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
		
		  
	}

}