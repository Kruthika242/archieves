package com.ntt.policy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PolicyServices {
	
	//Inserts new policy holder details into database
	int newPolicy(PolicyPojo p) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lic_policy","root","1234");
			stmt= con.createStatement();
			
			PreparedStatement pt = con.prepareStatement("insert into policy_table" 
			+ "(Policy_Name, Policy_Holder_Name, Policy_Start_Date, Premium_Amount, Premium_Type) values \r\n" 
			+ "( ?,?,?,?,?)");
			
			pt.setString(1,p.getPolicyName());
			pt.setString(2,p.getPolicyHolderName());
			pt.setString(3,p.getPolicyStartDate());
			pt.setFloat(4,p.getPremiumAmount());
			pt.setString(5,p.getPremiumType());
			rs = pt.executeUpdate();
		} 
		catch (ClassNotFoundException exp) 
		{
			exp.printStackTrace();
		}
	     
		finally {
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
		return rs;
	}
	
	//Displays policy holder details by accepting the policy number
	void displayDetails(PolicyPojo p) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lic_policy","root","1234");
			stmt= con.createStatement();
			
			ResultSet rs= stmt.executeQuery("select * from policy_table where Policy_Number="+ p.getPolicyNumber());
			
			if(rs.next())
			{
				rs=stmt.executeQuery("select * from policy_table where Policy_Number=\n"+ p.getPolicyNumber());	
			while(rs.next()) {
				System.out.println("Policy_Number : "+rs.getInt("Policy_Number")+ "\n"+
			    		"\nPolicy_Name : "+rs.getString("Policy_Name")+ "\n"+
			    		"\nPolicy_Holder_Name :"+rs.getString("Policy_Holder_Name")+"\n"+
			    		"\nPolicy_Start_Date : "+rs.getString("Policy_Start_Date")+"\n"+
			    		"\nPremium_Amount : "+rs.getFloat("Premium_Amount")+"\n"+
			    		"\nPremium_Type : "+rs.getString("Premium_Type") );
				}
			}
			else
			{
				System.out.println("Policy Holder not found....");
			}
		}catch(ClassNotFoundException cf) {
			cf.printStackTrace();
		}
		finally {
			try 
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception ex) 
			{
				System.out.println(ex.toString());
			}
		}			
	}
	
	//Updates the premium amount column in database  by accpeting the policy number 
	int updatePremiumAmount(PolicyPojo p) throws SQLException {
		
		Connection con = null;
		Statement stmt = null;
		int result=0;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lic_policy","root","1234");
			stmt= con.createStatement();
			
			PreparedStatement prepstmt=con.prepareStatement("update policy_table set Premium_Amount=? where Policy_Number=? ");
			prepstmt.setInt(2, p.getPolicyNumber());
			prepstmt.setFloat(1, p.getPremiumAmount());
			 
			result=prepstmt.executeUpdate();
			 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally { 
			try 
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}catch(Exception ex) 
			{
				System.out.println(ex.toString());
			}
		}
		 return result;
	}
	
	//deletes the policy holder details in database by accepting the policy number
	int deleteHolder(PolicyPojo p) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		int result=0;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lic_policy","root","1234");
			stmt= con.createStatement();
			
			PreparedStatement prepstmt = con.prepareStatement("delete from policy_table where Policy_Number=?");
			prepstmt.setInt(1,p.getPolicyNumber() );
		
			result = prepstmt.executeUpdate();
		} 
		catch (ClassNotFoundException cf) 
		{
			
			cf.printStackTrace();
		}
		finally {
			try 
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception ex) 
			{
				System.out.println(ex.toString());
			}
		}
		return result;		
	}

}
