package cs414.a5.nithya.client;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs414.a5.nithya.common.Garage;
import cs414.a5.nithya.common.Ticket;

public class EntryView {
	
	private JFrame entryFrame;
	private JPanel mainPanel,displayPanel, gatePanel;
	
	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel vehicleNumLabel;
	
	private JLabel ticketNum;
	private JLabel assignedLot;
	private JLabel timeOfEntry;
	private JLabel parkingRate;
	private JLabel entryGateStatus;
	
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField vehicleNumField;
	private JButton addButton;
	private Garage garage;
	
	
		public EntryView(Garage garage)
		{
			this.garage=garage;
		}
		public JTextField getName()
		{
			return nameField;
		}

		public JTextField getPhone()
		{
			return phoneField;
		}

		public JTextField getEmail()
		{
			return emailField;
		}

		public JTextField getVehicleNum()
		{
			return vehicleNumField;
		}
		
		public JLabel getEntryGateStatusLabel()
		{
			return entryGateStatus;
		}
		public Garage getGarage()
		{
			return garage;
		}
		
		public JFrame getEntryFrame()
		{
			return entryFrame;
		}
		public void run()
		
		{
			
		JFrame.setDefaultLookAndFeelDecorated(true);
		entryFrame = new JFrame("Enter Garage");
		entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	
	    nameLabel = new JLabel("Name: ");
	    phoneLabel= new JLabel("Phone: ");
	    emailLabel= new JLabel("Email: ");
	    vehicleNumLabel= new JLabel("Vehicle Number: ");

	    nameField = new JTextField(10);
	    phoneField = new JTextField(10);
	    emailField = new JTextField(10);
	    vehicleNumField= new JTextField(10);

	    addButton = new JButton("Add");
	    addButton.addActionListener(new EntryActionListener(this));

	    //addButton.addActionListener();

       

    mainPanel.setLayout(new GridBagLayout());

    GridBagConstraints gc = new GridBagConstraints();

    // // First column /////////////////////////

    gc.anchor = GridBagConstraints.LINE_END;
    gc.weightx = 0.5;
    gc.weighty = 0.5;

    gc.gridx = 0;
    gc.gridy = 0;
    mainPanel.add(nameLabel, gc);

    gc.gridx = 0;
    gc.gridy = 1;
    mainPanel.add(phoneLabel, gc);
    
    gc.gridx = 0;
    gc.gridy = 2;
    mainPanel.add(emailLabel, gc);
    
    gc.gridx = 0;
    gc.gridy = 3;
    mainPanel.add(vehicleNumLabel, gc);

    // // Second column
    gc.anchor = GridBagConstraints.LINE_START;

    gc.gridx = 1;
    gc.gridy = 0;
    mainPanel.add(nameField, gc);

    gc.gridx = 1;
    gc.gridy = 1;
    mainPanel.add(phoneField, gc);
    
    gc.gridx = 1;
    gc.gridy = 2;
    mainPanel.add(emailField, gc);

    
    gc.gridx = 1;
    gc.gridy = 3;
    mainPanel.add(vehicleNumField, gc);


    // Final row
    gc.weighty = 10;

    gc.anchor = GridBagConstraints.FIRST_LINE_START;
    gc.gridx = 1;
    gc.gridy = 5;
    mainPanel.add(addButton, gc);
    
    entryFrame.setContentPane(mainPanel);
    entryFrame.pack();
	entryFrame.setVisible(true);
}
		
		public void displayTicket(Ticket ticket) throws RemoteException
		{

			
			JFrame.setDefaultLookAndFeelDecorated(true);
			entryFrame = new JFrame("Enter Garage");
			entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			
			mainPanel= new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		      
		    displayPanel= new JPanel();
		    gatePanel= new JPanel();
		    
		    displayPanel.setBorder(BorderFactory.createCompoundBorder(
		              BorderFactory.createTitledBorder("Ticket Details"), 
		              BorderFactory.createEmptyBorder(5,5,5,5)));
		    ticketNum= new JLabel("Ticket Num");
		    assignedLot = new JLabel("Assigned Lot");
		    timeOfEntry = new JLabel("Time of Entry");
		    parkingRate= new JLabel("Rate of Parking");
		    entryGateStatus= new JLabel("Status of entry gate");
		    
		    ticketNum.setText("Ticket Num :    " + ticket.getTicketReferenceNumber());
		    assignedLot.setText("Assigned Parking Lot :    " + ticket.getAssignedParkingLot());
		    timeOfEntry.setText("Time of Entry is :    " + ticket.getTimeOfEntry().getTime());
		    parkingRate.setText("Parking Rate Per Hour is    " + ticket.getParkingRate() + "$");
		    
		    addButton = new JButton("Proceed");
		    addButton.addActionListener(new EntryActionListener2(this));
		    
		    displayPanel.setLayout(new GridBagLayout());

		    GridBagConstraints gc = new GridBagConstraints();

		    // // First column /////////////////////////

		    gc.anchor = GridBagConstraints.LINE_END;
		    gc.weightx = 0.5;
		    gc.weighty = 0.5;

		    gc.gridx = 0;
		    gc.gridy = 0;
		    displayPanel.add(ticketNum, gc);

		    gc.gridx = 0;
		    gc.gridy = 1;
		    displayPanel.add(assignedLot, gc);
		    
		    gc.gridx = 0;
		    gc.gridy = 2;
		    displayPanel.add(timeOfEntry, gc);
		    
		    gc.gridx = 0;
		    gc.gridy = 3;
		    displayPanel.add(parkingRate, gc);

		/*    
		   displayPanel.add(ticketNum);
		   displayPanel.add(assignedLot);
		   displayPanel.add(timeOfEntry);
		   displayPanel.add(parkingRate);
*/
		   gatePanel.setBorder(BorderFactory.createCompoundBorder(
		              BorderFactory.createTitledBorder("Gate Status"), 
		              BorderFactory.createEmptyBorder(5,5,5,5)));
		  gatePanel.add(entryGateStatus);
		  gatePanel.add(addButton);
		    
		  
		  mainPanel.add(displayPanel);
		  mainPanel.add(gatePanel);
		    entryFrame.setContentPane(mainPanel);
		    entryFrame.pack();
			entryFrame.setVisible(true);
		    
			
		    
		    
		    
		    
		}


		
	


}
