package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import cs414.a5.nithya.common.CustomException;
import cs414.a5.nithya.common.Garage;
import cs414.a5.nithya.common.Ticket;

public class ExitActionListener implements ActionListener{

	private ExitView exitView;
	public ExitActionListener(ExitView ev) {
		
		this.exitView=ev;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		exitView.getExitFrame().dispose();
		String ticketNum= exitView.getTicketNumField().getText();
		String vehicleNum= exitView.getVehicleNumField().getText();
	
		int ticketReferenceNumber= Integer.parseInt(ticketNum);
		exitView.setTicketRefNum(ticketReferenceNumber);
		try {
			
			Ticket submittedTicket= exitView.getGarage().validateTicketForExitingGarage(ticketReferenceNumber, vehicleNum);
			exitView.payParking(submittedTicket.getTotalParkingFee());
			
		
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		} catch (CustomException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
		
		
	}

}
