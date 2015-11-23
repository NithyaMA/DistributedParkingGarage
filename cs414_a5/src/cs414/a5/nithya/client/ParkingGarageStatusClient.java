package cs414.a5.nithya.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cs414.a5.nithya.common.Garage;

public class ParkingGarageStatusClient {

	static JFrame garageFrame;
	private JPanel mainPanel, displayPanel;
	private JLabel statusLabel;
	private Garage garage;
	public ParkingGarageStatusClient() throws RemoteException, NotBoundException {
		
		 
		  Registry registry= LocateRegistry.getRegistry("localhost");
		  garage = (Garage) 
					registry.lookup("garageServer");
		
		  mainPanel = new JPanel();
	      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	      
	      displayPanel = new JPanel();
	      
	     
		  
	      displayPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Garage Status"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	      
	      statusLabel = new JLabel();
	      statusLabel.setHorizontalAlignment(JLabel.CENTER);
	      statusLabel.setVerticalAlignment(JLabel.CENTER);
	      statusLabel.setVerticalTextPosition(JLabel.CENTER);
	      statusLabel.setHorizontalTextPosition(JLabel.CENTER);
	      statusLabel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLoweredBevelBorder(),
	            BorderFactory.createEmptyBorder(5,5,5,5)));

	      statusLabel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createEmptyBorder(0,0,10,0),
	            statusLabel.getBorder()));
	      displayPanel.add(statusLabel);
	      mainPanel.add(displayPanel);
	      
		
		
		
	}
	public static void main(String args[]) throws InterruptedException, RemoteException, NotBoundException {

		
		ParkingGarageStatusClient pgsc= new ParkingGarageStatusClient();
		JFrame.setDefaultLookAndFeelDecorated(true);
		garageFrame = new JFrame("Parking Garage");
		
		garageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		garageFrame.setContentPane(pgsc.mainPanel);
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(pgsc); // Instantiate SheduledTask class
		time.schedule(st, 0, 1000); // Create Repetitively task for every 1 secs

		garageFrame.pack();
		garageFrame.setVisible(true);
}
	public JLabel getStatusLabel() {
		return statusLabel;
	}
	public Garage getGarage() {
		return garage;
	}
}