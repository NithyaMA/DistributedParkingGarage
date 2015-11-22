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
		
		if(aov.getStartLabel().getText().equals("Starting Hour(dd-mm-yyyy HH:mm:ss): "))
		{
			
			String s=null;
			int numOfCars = 0;
			String str= aov.getStartField().getText();
			aov.getGarageFrame().dispose();
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
				
				numOfCars= reportTickets.size();
			
			//System.out.println("Number of cars during the time : " + reportTickets.size());
			//System.out.println("The name of customers and their vehicle numbers are as follows");
			s= "Customers and their Vehicle numbers are ";
			for(Ticket t: reportTickets)
			{
				s= s+ "   " + t.getCustomer().getName()  + " : " + t.getCustomer().getvehicleNumber();
				
			}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
			
			catch (Exception error)
			{
				ExceptionView ev= new ExceptionView(error.getMessage());
			}
			aov.viewReport(numOfCars, s);
		}
		
		else if (aov.getStartLabel().getText().equals("Day (dd-mm-yyyy): "))
		{
			System.out.println("pppp");
			String s=null;
			int numOfCars = 0;
			String str= aov.getStartField().getText();
			aov.getGarageFrame().dispose();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
			Date date= null;
			try {
				date = sdf.parse(str);
				
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e2.getMessage());
			}
			
			Calendar start= Calendar.getInstance();
			start.setTime(date);
			try {
				Set<Ticket> reportTickets=aov.getGarage().generateReport("daily", start);
				numOfCars= reportTickets.size();
				s= "Customers and their Vehicle numbers are ";
				for(Ticket t: reportTickets)
				{
					s= s+ "   " + t.getCustomer().getName()  + " : " + t.getCustomer().getvehicleNumber();
					
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
			aov.viewReport(numOfCars, s);
		}
		
		else if (aov.getStartLabel().getText().equals("Start of Week (dd-mm-yyyy): "))
		{
			System.out.println("rrrrr");
			String s=null;
			int numOfCars = 0;
			String str= aov.getStartField().getText();
			aov.getGarageFrame().dispose();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
			Date date= null;
			try {
				date = sdf.parse(str);
				
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e2.getMessage());
			}
			
			Calendar start= Calendar.getInstance();
			start.setTime(date);
			try {
				Set<Ticket> reportTickets=aov.getGarage().generateReport("weekly", start);
				numOfCars= reportTickets.size();
				s= "Customers and their Vehicle numbers are ";
				for(Ticket t: reportTickets)
				{
					s= s+ "   " + t.getCustomer().getName()  + " : " + t.getCustomer().getvehicleNumber();
					
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
			aov.viewReport(numOfCars, s);
		}
		
		else if (aov.getStartLabel().getText().equals("Month (mm-yyyy): "))
		{
			System.out.println("rrpp");
			String s=null;
			int numOfCars = 0;
			String str= aov.getStartField().getText();
			aov.getGarageFrame().dispose();
			SimpleDateFormat sdf = new SimpleDateFormat("M-yyyy");
			Date date= null;
			try {
				date = sdf.parse(str);
				
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e2.getMessage());
			}
			
			Calendar start= Calendar.getInstance();
			start.setTime(date);
			try {
				Set<Ticket> reportTickets=aov.getGarage().generateReport("monthly", start);
				numOfCars= reportTickets.size();
				s= "Customers and their Vehicle numbers are ";
				for(Ticket t: reportTickets)
				{
					s= s+ "   " + t.getCustomer().getName()  + " : " + t.getCustomer().getvehicleNumber();
					
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
			aov.viewReport(numOfCars, s);
		}
		
		else if (aov.getStartLabel().getText().equals("Provide Month (mm-yyyy): "))
		{
			System.out.println("yyy");
			int hour=0;
			String str= aov.getStartField().getText();
			aov.getGarageFrame().dispose();
			SimpleDateFormat sdf = new SimpleDateFormat("M-yyyy");
			Date date= null;
			try {
				date = sdf.parse(str);
				
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e2.getMessage());
			}
			
			Calendar start= Calendar.getInstance();
			start.setTime(date);
			try {
				 hour=aov.getGarage().findBusiestHourOfMonth(start);
				 System.out.println(hour);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
			aov.viewBusiestHour(hour);
		}
		else if (aov.getStartLabel().getText().equals("Vehicle Number : "))
		{
			Ticket t= null;
			String str= aov.getStartField().getText();
			aov.getGarageFrame().dispose();
			try {
				 t=aov.getGarage().helpCustomerToReprintTicket(str);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
			
			try {
				aov.showPrintedTicket(t.getTicketReferenceNumber());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
		}
		else if (aov.getStartLabel().getText().equals("Ticket Ref Number: "))
		{
			Float loanAmount=(float) 0.0;
			String str= aov.getStartField().getText();
			aov.getGarageFrame().dispose();
			try {
				loanAmount= aov.getGarage().lendMoneyToCashlessCustomerToExitGarage(Integer.parseInt(str));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e1.getMessage());
			}
			aov.showDebtMoney(loanAmount);
		}
		
	}
}
