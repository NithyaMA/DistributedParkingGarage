package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cs414.a5.nithya.common.Garage;


public class ParkingGarageAdmin implements ActionListener {
	
	Garage garage= null;
	
	JPanel mainPanel, selectPanel, displayPanel;
    JComboBox garageChoices = null;
    JLabel greetingLabel= null;
    JLabel statusLabel= null;
    static JFrame garageFrame= null;
    
	 
	  public ParkingGarageAdmin() throws RemoteException, NotBoundException
	  {
		  Registry registry= LocateRegistry.getRegistry("localhost");
		  garage = (Garage) 
					registry.lookup("garageServer");
		  
		 
		  
		  mainPanel = new JPanel();
	      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	      
	      displayPanel = new JPanel();
	      selectPanel = new JPanel();
	     
		  
	      displayPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Greetings"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	      
	      selectPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Select Options"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	      
	      addWidgets();
	      
	     
	      mainPanel.add(displayPanel);
	      mainPanel.add(selectPanel);
		  
	  }
	  
	  
	  public void addWidgets() throws RemoteException
	  {
		  
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
		  greetingLabel.setText("Welcome to " + garage.getName() +" Parking Garage");
		  
		  
		 statusLabel = new JLabel();
		 statusLabel.setBorder(BorderFactory.createCompoundBorder(
		            BorderFactory.createLoweredBevelBorder(),
		            BorderFactory.createEmptyBorder(5,5,5,5)));

		 statusLabel.setBorder(BorderFactory.createCompoundBorder(
		            BorderFactory.createEmptyBorder(0,0,10,0),
		 statusLabel.getBorder()));
		 statusLabel.setText("Garage status : " + garage.getGarageStatus());
		  
		 
			  String[] garageOptions={"Administrator options", "Exit from menu"};
			  garageChoices= new JComboBox(garageOptions);
			 
		
		
		 
		  garageChoices.setSelectedIndex(1);
		  garageChoices.addActionListener(this);
		  displayPanel.add(greetingLabel);
		  displayPanel.add(statusLabel);
		  selectPanel.add(garageChoices);
	  }
	public static void createAndShowGUI() throws RemoteException, NotBoundException
	{
		ParkingGarageAdmin pgc= new ParkingGarageAdmin();
		JFrame.setDefaultLookAndFeelDecorated(true);
		garageFrame = new JFrame("Parking Garage");
		
		garageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		garageFrame.setContentPane(pgc.mainPanel);
		garageFrame.pack();
		garageFrame.setVisible(true);
		
		
		
	}
	
	public void actionPerformed(ActionEvent event) {
        if ("comboBoxChanged".equals(event.getActionCommand())) {
           
        	  if (garageChoices.getSelectedItem().equals("Administrator options"))
              {
           	   AdminOptionView adminOptionView= new AdminOptionView(garage);
           	   adminOptionView.run();
           	   garageFrame.dispose();
              }
             
           else if (garageChoices.getSelectedItem().equals("Exit from menu"))
           {
        	  TerminationView terminationView= new TerminationView();
        	  terminationView.run();
        	  garageFrame.dispose();
           }
    }
	}
	public static void main(String[] args) {
		

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					createAndShowGUI();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					ExceptionView ev= new ExceptionView(e.getMessage());
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					ExceptionView ev= new ExceptionView(e.getMessage());
				}
            }
        });
    }
}

