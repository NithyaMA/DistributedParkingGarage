package cs414.a5.nithya.client;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs414.a5.nithya.common.Garage;

public class AdminOptionView {
	
	JComboBox adminChoices;
	private Garage garage;
	JFrame garageFrame;
	JTextField startField;
	JLabel startLabel;
	

	public AdminOptionView(Garage garage) {
		this.garage=garage;
	}
	
	
	public JComboBox getAdminChoices()
	{
		return adminChoices;
		
	}
	

	public JFrame getGarageFrame()
	{
		return garageFrame;
		
	}
	
	public JTextField getStartField()
	{
		return startField;
		
	}
	
	public JLabel getStartLabel()
	{
		return startLabel;
		
	}
	
	public Garage getGarage()
	{
		return garage;
	}
	public void run() {
		
		
		
		
		JPanel mainPanel, selectPanel;
	  
	    
	    JFrame.setDefaultLookAndFeelDecorated(true);
		garageFrame = new JFrame("Parking Garage");
		
		garageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
		  mainPanel = new JPanel();
	      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	      
	      
	      selectPanel = new JPanel();
	      
	      selectPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Select Options"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
		 
			
		  mainPanel.add(selectPanel);
			  
				  String[] adminOptions={"Hourly Report", "Daily Report", "Weekly Report", "Monthly Report","Busiest hour of month","Reprint ticket","Lend Money","Exit"};
				  adminChoices= new JComboBox(adminOptions);
				 
			
			
			 
			  adminChoices.setSelectedIndex(1);
			  adminChoices.addActionListener(new AdminActionListener(this));
			
			  selectPanel.add(adminChoices);
		  
		
			
			garageFrame.setContentPane(mainPanel);
			garageFrame.pack();
			garageFrame.setVisible(true);
				
		
	}
	
	public void  hourlyView()
	{
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		garageFrame = new JFrame("Exit Garage");
		garageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		JPanel mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		 mainPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Enter Hour Details"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	
	    startLabel = new JLabel("Starting Hour(dd-mm-yyyy HH:mm:ss): ");
	   
	    
	    startField = new JTextField(10);
	   
	    
	    JButton addButton = new JButton("Submit");
	    addButton.addActionListener(new AdminActionListener2(this));
	    
	    mainPanel.setLayout(new GridBagLayout());

	    GridBagConstraints gc = new GridBagConstraints();

	    // // First column /////////////////////////

	    gc.anchor = GridBagConstraints.LINE_END;
	    gc.weightx = 0.5;
	    gc.weighty = 0.5;

	    gc.gridx = 0;
	    gc.gridy = 0;
	    mainPanel.add(startLabel, gc);

	   
	    // // Second column
	    gc.anchor = GridBagConstraints.LINE_START;

	    gc.gridx = 1;
	    gc.gridy = 0;
	    mainPanel.add(startField, gc);

	   

	    // Final row
	    gc.weighty = 10;

	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
	    gc.gridx = 1;
	    gc.gridy = 3;
	    mainPanel.add(addButton, gc);
	    
	    garageFrame.setContentPane(mainPanel);
	    garageFrame.pack();
	    garageFrame.setVisible(true);
	    
	}

	
	public void dailyView()
	{
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		garageFrame = new JFrame("Exit Garage");
		garageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		JPanel mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		 mainPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Enter Day Details"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	
	    startLabel = new JLabel("Day (dd-mm-yyyy): ");
	   
	    
	    startField = new JTextField(10);
	   
	    
	    JButton addButton = new JButton("Submit");
	    addButton.addActionListener(new AdminActionListener2(this));
	    
	    mainPanel.setLayout(new GridBagLayout());

	    GridBagConstraints gc = new GridBagConstraints();

	    // // First column /////////////////////////

	    gc.anchor = GridBagConstraints.LINE_END;
	    gc.weightx = 0.5;
	    gc.weighty = 0.5;

	    gc.gridx = 0;
	    gc.gridy = 0;
	    mainPanel.add(startLabel, gc);

	   
	    // // Second column
	    gc.anchor = GridBagConstraints.LINE_START;

	    gc.gridx = 1;
	    gc.gridy = 0;
	    mainPanel.add(startField, gc);

	   

	    // Final row
	    gc.weighty = 10;

	    gc.anchor = GridBagConstraints.FIRST_LINE_START;
	    gc.gridx = 1;
	    gc.gridy = 3;
	    mainPanel.add(addButton, gc);
	    
	    garageFrame.setContentPane(mainPanel);
	    garageFrame.pack();
	    garageFrame.setVisible(true);
	    
	}
		public void viewReport(int numOfCars, String details)
		{
			JPanel mainPanel, displayPanel, buttonPanel;
			  
		    
		    JFrame.setDefaultLookAndFeelDecorated(true);
			garageFrame = new JFrame("Parking Garage");
			
			garageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		    
			  mainPanel = new JPanel();
		      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		      
		      
		      displayPanel = new JPanel();
		      
		     displayPanel.setBorder(BorderFactory.createCompoundBorder(
		              BorderFactory.createTitledBorder("Report"), 
		              BorderFactory.createEmptyBorder(5,5,5,5)));
			 
		     buttonPanel = new JPanel();
		      
		    buttonPanel.setBorder(BorderFactory.createCompoundBorder(
		              BorderFactory.createTitledBorder("Back To Admin Options"), 
		              BorderFactory.createEmptyBorder(5,5,5,5)));
		     
		     	JLabel numCarLabel = new JLabel();
		     	numCarLabel.setText("Number of vehicles: " + String.valueOf(numOfCars));
				JLabel reportLabel = new JLabel();
				reportLabel.setText(details);
				
				
				displayPanel.setLayout(new GridBagLayout());
				    GridBagConstraints gc = new GridBagConstraints();

				    // // First column /////////////////////////

				    gc.anchor = GridBagConstraints.LINE_END;
				    gc.weightx = 0.5;
				    gc.weighty = 0.5;

				    gc.gridx = 0;
				    gc.gridy = 0;
				    displayPanel.add(numCarLabel, gc);

				   
				    // // Second column
				    gc.anchor = GridBagConstraints.LINE_START;

				    gc.gridx = 0;
				    gc.gridy = 2;
				   displayPanel.add(reportLabel, gc);

				
				JButton button = new JButton("Go Back");
				button.addActionListener(new AdminActionListener3(this));
				buttonPanel.add(button);
				
				
				mainPanel.add(displayPanel);
				mainPanel.add(buttonPanel);
			  
				garageFrame.setContentPane(mainPanel);
			    garageFrame.pack();
			    garageFrame.setVisible(true);
				  
		}
	}


