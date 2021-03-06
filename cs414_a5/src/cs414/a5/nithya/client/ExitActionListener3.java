package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import cs414.a5.nithya.common.CustomException;

public class ExitActionListener3 implements ActionListener{

	private ExitView exitView;
	
	 public ExitActionListener3(ExitView ev) {
		this.exitView=ev;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cash=exitView.getCashField().getText();
		exitView.getExitFrame().dispose();
		Float cashPaid= Float.parseFloat(cash);
		try {
			Float balance;
			try {
				balance = exitView.getGarage().payParkingFeeByCash(exitView.getTicketRefNum(), cashPaid);
				exitView.showCashBalance(balance);
			} catch (CustomException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
				 try {
						if(exitView.getGarage().getExitKiosk().getName().equals("ex1"))
							  ParkingGarageExit1.createAndShowGUI();
						else if (exitView.getGarage().getExitKiosk().getName().equals("ex2"))
							ParkingGarageExit2.createAndShowGUI();
					} catch (RemoteException er) {
						// TODO Auto-generated catch block
						ExceptionView ev1= new ExceptionView(er.getMessage());
					} catch (NotBoundException enb) {
						// TODO Auto-generated catch block
						ExceptionView ev2= new ExceptionView(enb.getMessage());
					}
			}
			
			if(exitView.getGarage().getExitKiosk().isExitGate())
				exitView.getExitGateStatusLabel().setText("Exit gate " + exitView.getGarage().getExitKiosk().getName() + " is open");

		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
	}

}
