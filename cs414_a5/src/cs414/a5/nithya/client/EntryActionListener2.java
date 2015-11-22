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
	
private final EntryView entryView;
	
	public EntryActionListener2(EntryView ev) {
		entryView= ev;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		entryView.getEntryFrame().dispose();
		
		JFrame frame= null;
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("End Frame");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		  JPanel mainPanel, displayPanel;
		  mainPanel = new JPanel();
		  JLabel greetingLabel= null;
	      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	      
	      displayPanel = new JPanel();
	      
	      displayPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Thank you, Have a great Parking!!"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	      mainPanel.add(displayPanel);
	      greetingLabel = new JLabel();
		  greetingLabel.setHorizontalAlignment(JLabel.CENTER);
		  greetingLabel.setVerticalAlignment(JLabel.CENTER);
		  greetingLabel.setVerticalTextPosition(JLabel.CENTER);
		  greetingLabel.setHorizontalTextPosition(JLabel.CENTER);
		  greetingLabel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLoweredBevelBorder(),
	            BorderFactory.createEmptyBorder(5,5,5,5)));

		  greetingLabel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createEmptyBorder(0,0,10,0),
	            greetingLabel.getBorder()));
		  try {
				if(! entryView.getGarage().getEntryKiosk().isEntryGate())
			greetingLabel.setText("Entry gate " + entryView.getGarage().getEntryKiosk().getName() + " is closed");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
		  displayPanel.add(greetingLabel);
		  frame.setContentPane(mainPanel);
		  frame.pack();
		  frame.setVisible(true);
		  
		  try {
			if(entryView.getGarage().getEntryKiosk().getName().equals("en1"))
				  ParkingGarageEntry1.createAndShowGUI();
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
		  
		  
		  
	}

}
