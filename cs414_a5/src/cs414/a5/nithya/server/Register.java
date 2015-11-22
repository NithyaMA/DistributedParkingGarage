package cs414.a5.nithya.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;











import cs414.a5.nithya.common.*;



public class Register implements Serializable {

	private Set <Ticket> tickets= new HashSet<Ticket>();
	
	public boolean addTicketToGeneratedTickets(Ticket ticket)
	{
		tickets.add(ticket);
		return true;
	}
	
	public Ticket validateTicket(int ticketReferenceNumber, String vehicleNumber) throws CustomException, RemoteException
	{	
		boolean flag=false;
		Ticket submittedTicket=null;
		
		for(Ticket ticket: tickets)
		{
			if(ticket.getTicketReferenceNumber()==ticketReferenceNumber)
			{	
				flag=true;
				if(ticket.getCustomer().getvehicleNumber().equals(vehicleNumber))
				{
				     if(ticket.getTicketStatus().equals(TicketStatus.paid))
						throw new CustomException("The vehicle has already exited the garage.");
					
					Calendar cal = Calendar.getInstance();
					ticket.setTimeOfExit(cal);
					ticket.calculateTotalParkingFee();
					submittedTicket=ticket;
				
				break;
				}
				else 
					throw new CustomException("The ticket does not match with the parked vehicle. Ticket is fake");
				
			}
		}
		if(flag==false)
			throw new CustomException("The ticket reference number does not exist. Ticket is fake");
		if (tickets.isEmpty())
			throw new CustomException("There are no vehicles parked inside the garage. Sorry, you have come to the wrong garage.");
		return submittedTicket;
		
	}
	
	public Set<Ticket> generateReport(String choice,Calendar start ) throws RemoteException
	{	
		
		Calendar stop=(Calendar)start.clone();
		
		switch(choice)
		{ case "hourly" :
			
			stop.add(Calendar.HOUR, 1);
			break;
			
		case "daily" :
			
			stop.add(Calendar.HOUR, 24);
			break;
			
		case "weekly" :
			
			stop.add(Calendar.HOUR, 168);
			break;
			
		case "monthly" :
			
			stop.add(Calendar.MONTH, 1);
			break;
		
		}
				
		Set <Ticket> reportTickets= new HashSet<Ticket>();
		
		for(Ticket ticket: tickets)
		{
			Calendar entry= (Calendar)ticket.getTimeOfEntry().clone();
			
			
			if(ticket.getTicketStatus().equals(TicketStatus.pending))
			{
				if( (entry.getTime().before(stop.getTime())) )
					
					reportTickets.add(ticket);
			}
			else
			{
			Calendar exit= (Calendar)ticket.getTimeOfExit().clone();
			
			if( (entry.getTime().after(start.getTime()) && entry.getTime().before(stop.getTime()))  ||  (exit.getTime().after(start.getTime()) && exit.getTime().before(stop.getTime()))  ||  (entry.getTime().before(start.getTime()) && exit.getTime().after(stop.getTime())) )
				{
				reportTickets.add(ticket);
				}
				
			}
		}
		
		return reportTickets;
	}
	
	
	/*public int findBusiestHourOfTheMonth(Calendar startOfMonth) throws RemoteException
	{
		
		
		Calendar stopOfMonth =(Calendar) startOfMonth.clone();
		Calendar cal = (Calendar) startOfMonth.clone();
		
		stopOfMonth.set(Calendar.DATE, 30);
		
		int[] occupancyNumbers= new int[30];
		int dat=0;
		
		for (startOfMonth.getTime(); startOfMonth.getTime().before(stopOfMonth.getTime()); startOfMonth.add(Calendar.DATE, 1)) 
		    
			{
				
				int occupanyNum= (generateReport("daily", startOfMonth)).size();
				occupancyNumbers[dat]= occupanyNum;  
				dat++;
			}
			
		
		
	
			int busiestDay=1;
			
			for(int j=2; j<occupancyNumbers.length; j++)
			{
				if(occupancyNumbers[j]> occupancyNumbers[busiestDay])
				{
					
					busiestDay=j;
				}
			   
			}
			int requireday=busiestDay;
		
			   
			
			   cal.set(Calendar.DATE, requireday);
	
			   int [] occupants= new int[24];
			   int hour=0;
			   for (int hr=0; hr<24; hr++) 
			   {
				   cal.set(Calendar.HOUR, hr);
				  int occupNum= (generateReport("hourly", cal)).size();
					occupants[hour]= occupNum;    
					hour++;
			   }
			   
			   
			   int max=0;
			   int busiestHour = 0;
			   
			   for(int count=0; count < occupants.length ; count++)
			   {
				   
				  if (occupants[count]>max)
				  {
					  max= occupants[count];
					  busiestHour= count;
					  
				  }
			   }
			   return (busiestHour-1);
			   
		}*/
		
		
			   
	
	public int findBusiestHourOfTheMonth(Calendar startOfMonth) throws RemoteException, ParseException
	{
		Calendar reqCal;
		Calendar cal= Calendar.getInstance();
		if (startOfMonth.MONTH ==  cal.MONTH)
		
		{
			Date date= new Date();
			startOfMonth.setTime(date);
			reqCal=startOfMonth;
		}
		else
		{
		     startOfMonth.set(Calendar.DATE, 15);
		     reqCal=startOfMonth;
		}
		
		int [] occupants= new int[25];
		 
		 String[] str={"00:00:00", "01:00:00","02:00:00","03:00:00","04:00:00","05:00:00","06:00:00","07:00:00","08:00:00","09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00","18:00:00","19:00:00","20:00:00","21:00:00","22:00:00","23:00:00"};
		   for (int hr=0; hr<24; hr++) 
		   {
			  
			   Date d= reqCal.getTime();
			   DateFormat df= new SimpleDateFormat("dd-MM-yyyy");
			   String sd= df.format(d);
			    String s= sd + " " + str[hr];
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				Date date=null;
				date=sdf.parse(s);
				reqCal.setTime(date);
			    Set <Ticket> reportTickets=(generateReport("hourly", reqCal));
			   
			    int occupNum= reportTickets.size();
			  occupants[hr]= occupNum;  
			
				
		   }
		   int max=0;
		   for(int i=0; i< 23; i++)
		   {
			   if(occupants[i]<occupants[i+1])
				   max= i+1;
			   
		   }
		   
		   
		   
		return max;
		}
		
		
				
	
	public Set<Ticket> getTickets() {
		return tickets;
	}
	
	public Ticket getSpecificTicket(int ticketRefNum) throws RemoteException
	{
		Ticket specificTicket=null;
		for(Ticket t : tickets)
		{
			if (t.getTicketReferenceNumber()==ticketRefNum)
				specificTicket= t;
		}
		return specificTicket;
	}
	
	
	
}
