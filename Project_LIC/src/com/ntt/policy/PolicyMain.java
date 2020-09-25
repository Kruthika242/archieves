package com.ntt.policy;

import java.util.Scanner;
import java.sql.SQLException;

public class PolicyMain {

	public static void main(String[] args) throws SQLException {
		PolicyServices policyObj=new PolicyServices();
		PolicyPojo obj= new PolicyPojo();
		int ch=0;
		
		while(ch==0)
		{
		System.out.println("Choose required option");
		System.out.println("1: Create Policy Holder");
		System.out.println("2: Display Policy Holder details");
		System.out.println("3: Update or Modify Policy details");
		System.out.println("4: Delete Policy Holder\n");
		
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);	
		int service = scan.nextInt();
		
		switch(service)
		{
		//To create new policy
		case 1 :{
			System.out.println("Enter the Insurance Policy required !!");
			System.out.println("Insurance Policies Available:\n1] Term Life Insurance\n"
					+ "2] Whole Life Insurance\n"
					+ "3] Universal Life Insurance\n"  );
			
			 int pol = scan.nextInt(); //helps user to choose either of the policy types
			 if(pol==1) 
		         obj.setPolicyName("Term Life Insurance");
			 else if(pol==2) 
			     obj.setPolicyName("Whole Life Insurance");
			 else if(pol==3) 
			     obj.setPolicyName("Universal Life Insurance");
		     
		     System.out.println("Enter Policy_holder_name : ");
		     String holderName = scan.next();
		     obj.setPolicyHolderName(holderName);
		     
		
		     System.out.println("Enter Policy_start_date : ");
		     String startDate = scan.next();
		     obj.setPolicyStartDate(startDate);
		    
		    
		     System.out.println("Enter Premium_amount : ");
		     float amt=scan.nextFloat();
		     obj.setPremiumAmount(amt);
		     
		     System.out.println("Choose Premium_type !!!");
		     System.out.println("Premium Types Available:\n1] Quaterly\n"
						+ "2] Half-Yearly\n"
						+ "3] Yearly\n"  );
		     
		     int type = scan.nextInt(); //helps user to choose either of the premium types
		     if(type==1) 
			     obj.setPremiumType("Quaterly");
			 else if(type==2) 
				 obj.setPremiumType("Half-Yearly");
			 else if(type==3) 
				 obj.setPremiumType("Yearly");
	
			 int src=0;
			 src= policyObj.newPolicy(obj);
			
			     if(src==0)
			    	 System.out.println("Policy User Data  insertion successfull");
			     else
			    	 System.out.println("Policy User Data insertion successfull");
			     break;
		        }
		//To display details of a policy Holder using Policy Number
		case 2 : {
				System.out.println("Enter PolicyNumber");
				obj.setPolicyNumber(scan.nextInt());
	            policyObj.displayDetails(obj);
	            break;
				}
		// To update or modify the Premium Amount using Policy Number
		case 3 : {	
		     System.out.println("To Update Premium amount enter your Policy Number :");
		     obj.setPolicyNumber(scan.nextInt());
		     System.out.println("Enter Premium amount :");
		     obj.setPremiumAmount(scan.nextFloat());

	         int src = policyObj.updatePremiumAmount(obj);
	           if(src>0)
	    	     System.out.println("Premium Amount Updated successfully");
	           else
	    	     System.out.println("Premium Amount Not Updated");
		     break;	
			 }
		// To delete Policy Holder using Policy Number
		case 4 : {
			System.out.println("Enter PolicyNumber to Delete Policy holder");
			obj.setPolicyNumber(scan.nextInt());
		       
	        int src = policyObj.deleteHolder(obj);
	           if(src==0)
	    	    System.out.println("Policy Holder Deletion Unsuccessfull");
	           else
	    	    System.out.println("Policy Holder Deletion successfull");
	           break;
		}
			
	 }
		System.out.println("Press 0 to continue");
		ch = scan.nextInt();
	}
  }		
}

