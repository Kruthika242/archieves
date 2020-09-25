package com.ntt.policy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PolicyServices {
	public static Connection con=null;
	public  static Statement stmt=null;
    public static PreparedStatement prepstmt=null;
	public static ResultSet res=null;
	
	public static Connection  openConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lic_policy","root","1234");
			 if(con!=null) {
				 System.out.println(" Database Connection successfull!");
			 }
		} catch (ClassNotFoundException cf) {
			System.out.println(" Database Connection failed !");
			cf.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection() {
		try 
		{
			if(stmt!=null)
				stmt.close();
			if(con!=null)
				con.close();
		}
		catch(Exception et) 
		{
			System.out.println(et.toString());
		}
	}
	
	//Inserts new policy holder details into database
	int newPolicy(PolicyPojo p) {
		int rs = 0;
		
		try 
		{
			openConnection();
			stmt= con.createStatement();
			
			prepstmt = con.prepareStatement("insert into policy_table" 
			+ "(Policy_Name, Policy_Holder_Name, Policy_Start_Date, Premium_Amount, Premium_Type) values \r\n" 
			+ "( ?,?,?,?,?)");
			
			prepstmt.setString(1,p.getPolicyName());
			prepstmt.setString(2,p.getPolicyHolderName());
			prepstmt.setString(3,p.getPolicyStartDate());
			prepstmt.setFloat(4,p.getPremiumAmount());
			prepstmt.setString(5,p.getPremiumType());
			rs = prepstmt.executeUpdate();
		} 
		catch (SQLException sqlexp) 
		{
			sqlexp.printStackTrace();
		}
	     
		finally {
			closeConnection();
		}
		return rs;
	}
	
	//Displays policy holder details by accepting the policy number
	void displayDetails(PolicyPojo p) throws SQLException {
		try 
		{
			openConnection();
			stmt= con.createStatement();
			res= stmt.executeQuery("select * from policy_table where Policy_Number="+ p.getPolicyNumber());
			
			if(res.next())
			{
				res=stmt.executeQuery("select * from policy_table where Policy_Number=\n"+ p.getPolicyNumber());	
			while(res.next()) {
				System.out.println("Policy_Number : "+res.getInt("Policy_Number")+ "\n"+
			    		"\nPolicy_Name : "+res.getString("Policy_Name")+ "\n"+
			    		"\nPolicy_Holder_Name :"+res.getString("Policy_Holder_Name")+"\n"+
			    		"\nPolicy_Start_Date : "+res.getString("Policy_Start_Date")+"\n"+
			    		"\nPremium_Amount : "+res.getFloat("Premium_Amount")+"\n"+
			    		"\nPremium_Type : "+res.getString("Premium_Type") );
				}
			}
			else
			{
				System.out.println("Policy Holder not found....");
			}
		}catch(NullPointerException npexp) {
			npexp.printStackTrace();
		}
		finally {
			closeConnection();
		}			
	}
	
	//Updates the premium amount column in database  by accpeting the policy number 
	int updatePremiumAmount(PolicyPojo p)  {
		int result=0;
		try 
		{
			openConnection();
			stmt= con.createStatement();
			PreparedStatement prepstmt=con.prepareStatement("update policy_table set Premium_Amount=? where Policy_Number=? ");
			
			prepstmt.setFloat(1, p.getPremiumAmount());
			prepstmt.setInt(2, p.getPolicyNumber());
			 
			result=prepstmt.executeUpdate();
			 
		} catch (SQLException sqlexp) {
			sqlexp.printStackTrace();
		}
		finally { 
			closeConnection();
		}
		 return result;
	}
	
	//deletes the policy holder details in database by accepting the policy number
	int deleteHolder(PolicyPojo p) {
		int result=0;
		try 
		{
			openConnection();
			stmt= con.createStatement();
			
			PreparedStatement prepstmt = con.prepareStatement("delete from policy_table where Policy_Number=?");
			prepstmt.setInt(1,p.getPolicyNumber() );
		
			result = prepstmt.executeUpdate();
		} 
		catch (SQLException sqlexp) 
		{
			sqlexp.printStackTrace();
		}
		finally {
			closeConnection();
		}
		return result;		
	}

}
