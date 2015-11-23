package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EntryActionListener2 implements ActionListener {
	
private  EntryView entryView;
	
	public EntryActionListener2(EntryView ev) {
		entryView= ev;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
		entryView.getEntryFrame().dispose();
		 
		  try {
			if(entryView.getGarage().getEntryKiosk().getName().equals("en1"))
				  ParkingGarageEntry1.createAndShowGUI();
			else if (entryView.getGarage().getEntryKiosk().getName().equals("en2"))
				ParkingGarageEntry2.createAndShowGUI();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
		
		  
	}

}
