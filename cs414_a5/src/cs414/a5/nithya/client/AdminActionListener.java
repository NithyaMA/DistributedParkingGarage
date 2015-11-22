package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminActionListener implements ActionListener{

	private AdminOptionView aov;
	public AdminActionListener(AdminOptionView aov) {
		this.aov= aov;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if ("comboBoxChanged".equals(event.getActionCommand())) {
	           
      	  if (aov.getAdminChoices().getSelectedItem().equals("Hourly Report"))
            {
         	   aov.getGarageFrame().dispose();
            }
           
         else if (aov.getAdminChoices().getSelectedItem().equals("Weekly Report"))
         {
      	 
        	 aov.getGarageFrame().dispose();
         }
         else if (aov.getAdminChoices().getSelectedItem().equals("Monthly Report"))
         {
      	 
        	 aov.getGarageFrame().dispose();
         }
         else if (aov.getAdminChoices().getSelectedItem().equals("Busiest hour of month"))
         {
      	 
        	 aov.getGarageFrame().dispose();
         }
         else if (aov.getAdminChoices().getSelectedItem().equals("Reprint ticket"))
         {
      	  
        	 aov.getGarageFrame().dispose();
         }
         else if (aov.getAdminChoices().getSelectedItem().equals("Lend Money"))
         {
      	  
        	 aov.getGarageFrame().dispose();
         }
         else if (aov.getAdminChoices().getSelectedItem().equals("Exit"))
         {
        	 aov.getGarageFrame().dispose();
      	  
         }
  }
		
	}

}
