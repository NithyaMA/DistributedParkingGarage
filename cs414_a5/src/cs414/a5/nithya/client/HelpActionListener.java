package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HelpActionListener  implements ActionListener{

	private HelpView helpView;
	public HelpActionListener(HelpView hw) {
		
		this.helpView=hw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		helpView.getExitFrame().dispose();
		 
		  try {
			if(helpView.getGarage().getExitKiosk().getName().equals("ex1"))
				  ParkingGarageExit1.createAndShowGUI();
			else if(helpView.getGarage().getExitKiosk().getName().equals("ex2"))
				  ParkingGarageExit2.createAndShowGUI();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
		
		
	}

}
