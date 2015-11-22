package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import cs414.a5.nithya.common.CustomException;
import cs414.a5.nithya.common.Customer;
import cs414.a5.nithya.common.Ticket;

public class EntryActionListener implements ActionListener{

	private final EntryView entryView;
	
	public EntryActionListener(EntryView ev) {
		entryView= ev;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		String name= entryView.getName().getText();
		String phone= entryView.getPhone().getText();
		String email= entryView.getEmail().getText();
		String vehicleNum= entryView.getVehicleNum().getText();
		try {
			entryView.getEntryFrame().dispose();
			Customer customer= new Customer(name,phone,email,vehicleNum);
			Ticket ticket=entryView.getGarage().enterGarage(customer);
			entryView.displayTicket(ticket);
			if(entryView.getGarage().getEntryKiosk().isEntryGate())
				entryView.getEntryGateStatusLabel().setText("Entry gate " + entryView.getGarage().getEntryKiosk().getName() + " is open");

			try{
				Thread.sleep(6000);
				entryView.getGarage().activateSensor("entry");
				}
				catch(InterruptedException ie)
				{
					ExceptionView ev= new ExceptionView(ie.getMessage());
				}
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e.getMessage());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e.getMessage());
		}
	}

	
	
	
}
