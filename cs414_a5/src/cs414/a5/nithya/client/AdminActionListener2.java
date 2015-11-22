package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import cs414.a5.nithya.common.Ticket;

public class AdminActionListener2 implements ActionListener {

	
	private AdminOptionView aov;
	public AdminActionListener2(AdminOptionView aov) {
		this.aov= aov;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(aov.getStartField().getText().equals("Starting Hour(dd-M-yyyy HH:mm:ss): "))
		{
			String str= aov.getStartField().getText();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
			Date date=null;
			try {
				date = sdf.parse(str);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e2.getMessage());
			}
			Calendar start= Calendar.getInstance();
			start.setTime(date);
	
			
			Set<Ticket> reportTickets;
			try {
				reportTickets = aov.getGarage().generateReport("hourly", start);
			
			System.out.println("Number of cars during the time : " + reportTickets.size());
			System.out.println("The name of customers and their vehicle numbers are as follows");
			for(Ticket t: reportTickets)
			{
				System.out.println(t.getCustomer().getName()  + ":" + t.getCustomer().getvehicleNumber());
				
			}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
		}
		
	}
}
