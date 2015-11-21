package cs414.a5.nithya.client;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntryClient {
	
	private JFrame entryFrame;
	private JPanel mainPanel;
	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel vehicleNumLabel;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField vehicleNumField;
	private JButton addButton;
	
		
		
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




}
