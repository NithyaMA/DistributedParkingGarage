package cs414.a5.nithya.common;

import java.io.Serializable;

import cs414.a5.nithya.common.CustomException;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String phoneNumber;
	private String emailId;
	private String vehicleNumber;
	
	public Customer(String name, String phoneNumber, String emailId, String vehicleNumber) throws CustomException
	{
		this.name=name;
		if(emailId.indexOf('@')==-1)
			throw new CustomException("E mail id is not valid");
		if(emailId.indexOf('.')==-1)
			throw new CustomException("E mail id is not valid");
		this.emailId=emailId;
		if(phoneNumber.length()<6)
			throw new CustomException("Phone number is not valid");
			
		this.phoneNumber=phoneNumber;
		this.vehicleNumber=vehicleNumber;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	
	public String getvehicleNumber() {
		return vehicleNumber;
	}
	
}
