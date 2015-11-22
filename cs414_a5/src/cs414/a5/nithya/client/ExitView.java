package cs414.a5.nithya.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs414.a5.nithya.common.Garage;

public class ExitView {

	

	private JFrame exitFrame;
	private JPanel mainPanel;
	
	private JLabel ticketNumLabel;
	private JLabel vehicleNumLabel;
	private JLabel parkingFeeLabel;
	 JLabel cashPaid;
	 JLabel exitGateStatus;
	
	
	private JTextField ticketNumField;
	private JTextField vehicleNumField;
	private JTextField cardNumField;
	private JTextField cardExpiryField;
	  JTextField cashField ;
	private JButton addButton;
	JComboBox paymentChoices = null;
	
	private Garage garage;
	private int ticketRefNum;
	
	
		public ExitView(Garage garage)
		{
			this.garage=garage;
		}
		
		
		public void setTicketRefNum(int num)
		{
			this.ticketRefNum=num;
		}
		
		public int getTicketRefNum()
		{
			return this.ticketRefNum;
		}
		public JFrame getExitFrame()
		{
			return exitFrame;
		}
		
		public JTextField getTicketNumField()
		{
			return ticketNumField;
		}
		
		public JTextField getVehicleNumField()
		{
			return vehicleNumField;
		}
		
		public JTextField getCashField()
		{
			return cashField;
		}
		
		public JLabel getExitGateStatusLabel()
		{
			return exitGateStatus;
		}
		
		public JComboBox getPaymentChoices()
		{
			return paymentChoices;
		}
		public Garage getGarage()
		{
			return garage;
		}
		
		
	public void run() {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
		exitFrame = new JFrame("Exit Garage");
		exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		 mainPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Ticket Details"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	
	    ticketNumLabel = new JLabel("TicketNumber: ");
	    vehicleNumLabel= new JLabel("Vehicle Number: ");
	    
	    ticketNumField = new JTextField(3);
	    vehicleNumField= new JTextField(10);
	    
	    addButton = new JButton("Submit");
	    addButton.addActionListener(new ExitActionListener(this));
	    
	    mainPanel.setLayout(new GridBagLayout());

	    GridBagConstraints gc = new GridBagConstraints();

	    // // First column /////////////////////////

	    gc.anchor = GridBagConstraints.LINE_END;
	    gc.weightx = 0.5;
	    gc.weighty = 0.5;

	    gc.gridx = 0;
	    gc.gridy = 0;
	    mainPanel.add(ticketNumLabel, gc);

	    gc.gridx = 0;
	    gc.gridy = 1;
	    mainPanel.add( vehicleNumLabel, gc);
	    
	    // // Second column
	    gc.anchor = GridBagConstraints.LINE_START;

	    gc.gridx = 1;
	    gc.gridy = 0;
	    mainPanel.add(ticketNumField, gc);

	    gc.gridx = 1;
	    gc.gridy = 1;
	    mainPanel.add( vehicleNumField, gc);
	    

	    // Final row
	    gc.weighty = 10;

	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
	    gc.gridx = 1;
	    gc.gridy = 3;
	    mainPanel.add(addButton, gc);
	    
	    exitFrame.setContentPane(mainPanel);
	    exitFrame.pack();
	    exitFrame.setVisible(true);
	    
	}

	public void payParking(Float parkingFee)
	{
		
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		exitFrame = new JFrame("Exit Garage");
		exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		 mainPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Payment Options"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	
	    
	    parkingFeeLabel= new JLabel("Parking Fee");
	    parkingFeeLabel.setText("Parking fee: " + String.format("%.2f", parkingFee) + " $");
	    
	    String [] payChoices= {"Cash", "Card"};
	    paymentChoices= new JComboBox(payChoices);
	    paymentChoices.setSelectedIndex(0);
	    paymentChoices.addActionListener(new ExitActionListener2(this));
	    
	    mainPanel.add(parkingFeeLabel);
	    mainPanel.add(paymentChoices);
	    
	    exitFrame.setContentPane(mainPanel);
	    exitFrame.pack();
	    exitFrame.setVisible(true);
	    
	    
	}
	
	public void payByCash()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		exitFrame = new JFrame("Exit Garage");
		exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		 mainPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Cash Details"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	
	    cashPaid = new JLabel("Cash Provided:  "); 
	    cashField = new JTextField(3);
	   
	    addButton = new JButton("Submit");
	    addButton.addActionListener(new ExitActionListener3(this));
	    
	    mainPanel.setLayout(new GridBagLayout());

	    GridBagConstraints gc = new GridBagConstraints();

	    // // First column /////////////////////////

	    gc.anchor = GridBagConstraints.LINE_END;
	    gc.weightx = 0.5;
	    gc.weighty = 0.5;

	    gc.gridx = 0;
	    gc.gridy = 0;
	    mainPanel.add(cashPaid, gc);

	   
	    
	    // // Second column
	    gc.anchor = GridBagConstraints.LINE_START;

	    gc.gridx = 1;
	    gc.gridy = 0;
	    mainPanel.add(cashField, gc);

	  

	    // Final row
	    gc.weighty = 10;

	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
	    gc.gridx = 1;
	    gc.gridy = 3;
	    mainPanel.add(addButton, gc);
	    
	    exitFrame.setContentPane(mainPanel);
	    exitFrame.pack();
	    exitFrame.setVisible(true);
	    
	
	}
	
	public void showCashBalance(Float balance)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		exitFrame = new JFrame("Exit Garage");
		exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		JPanel displayPanel= new JPanel();
		displayPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Balance Details"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
		
	
	    JLabel balanceLabel = new JLabel("Balance:  "); 
	    JLabel balancee= new JLabel(); 
	    balancee.setText(String.format("%.2f",balance));
	    
	    displayPanel.add(balanceLabel);
	    displayPanel.add(balancee);
	   
	    
	    mainPanel.setLayout(new GridBagLayout());

	    GridBagConstraints gc = new GridBagConstraints();

	    // // First column /////////////////////////

	    gc.anchor = GridBagConstraints.LINE_END;
	    gc.weightx = 0.5;
	    gc.weighty = 0.5;

	    gc.gridx = 0;
	    gc.gridy = 0;
	    displayPanel.add(balanceLabel, gc);

	   
	    
	    // // Second column
	    gc.anchor = GridBagConstraints.LINE_START;

	    gc.gridx = 1;
	    gc.gridy = 0;
	    displayPanel.add(balancee, gc);

	    JPanel gatePanel= new JPanel();
	    gatePanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Exit Gate Status"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));

	   
	    exitGateStatus= new JLabel();
	    addButton = new JButton("Proceed");
	    addButton.addActionListener(new ExitActionListener4(this));
	    
	    gatePanel.add(exitGateStatus);
	    gatePanel.add(addButton);
	    
	    mainPanel.add(displayPanel);
	    mainPanel.add(gatePanel);
	    
	    exitFrame.setContentPane(mainPanel);
	    exitFrame.pack();
	    exitFrame.setVisible(true);
		
	}
	public void payByCard()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		exitFrame = new JFrame("Exit Garage");
		exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		 mainPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Ticket Details"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	
	    JLabel cardNumLabel = new JLabel("Card Number: ");
	    JLabel cardExpiryLabel= new JLabel("Expiry Date (Format: MM/dd/yyyy): ");
	    
	    cardNumField = new JTextField(10);
	    cardExpiryField= new JTextField(10);
	    
	    addButton = new JButton("Submit");
	    addButton.addActionListener(new ExitActionListener5(this));
	    
	    mainPanel.setLayout(new GridBagLayout());

	    GridBagConstraints gc = new GridBagConstraints();

	    // // First column /////////////////////////

	    gc.anchor = GridBagConstraints.LINE_END;
	    gc.weightx = 0.5;
	    gc.weighty = 0.5;

	    gc.gridx = 0;
	    gc.gridy = 0;
	    mainPanel.add(cardNumLabel, gc);

	    gc.gridx = 0;
	    gc.gridy = 1;
	    mainPanel.add( cardExpiryLabel, gc);
	    
	    // // Second column
	    gc.anchor = GridBagConstraints.LINE_START;

	    gc.gridx = 1;
	    gc.gridy = 0;
	    mainPanel.add(cardNumField, gc);

	    gc.gridx = 1;
	    gc.gridy = 1;
	    mainPanel.add( cardExpiryField, gc);
	    

	    // Final row
	    gc.weighty = 10;

	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
	    gc.gridx = 1;
	    gc.gridy = 3;
	    mainPanel.add(addButton, gc);
	    
	    exitFrame.setContentPane(mainPanel);
	    exitFrame.pack();
	    exitFrame.setVisible(true);
	    
	}

	
	public void cardEndView() throws RemoteException
	{
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		exitFrame = new JFrame("Enter Garage");
		exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	
	    
	    mainPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Exit Gate Status"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	   exitGateStatus= new JLabel();
		if(garage.getExitKiosk().isExitGate())
			exitGateStatus.setText("Exit gate " + garage.getExitKiosk().getName() + " is open");
		 addButton = new JButton("Submit");
		    addButton.addActionListener(new ExitActionListener6(this));
		    mainPanel.add(exitGateStatus);
		    mainPanel.add(addButton);
		    exitFrame.setContentPane(mainPanel);
		    exitFrame.pack();
		    exitFrame.setVisible(true);
		    
		
	}

	public JTextField getCardNumField() {
		return cardNumField;
	}


	public JTextField getCardExpiryField() {
		return cardExpiryField;
	}
}
