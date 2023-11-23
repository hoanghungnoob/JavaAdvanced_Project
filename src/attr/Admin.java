package attr;

import java.lang.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import attr.*;
import activity.*;

public class Admin extends User {
	private String admin;
	private String phoneNumber;
	public static String[] columnNames = {"Name", "PhoneNumber"};
	public Admin(String userName) {
		super(userName);
		this.setStatus(0);
	}
	
	private void setStatus(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setAdminName(String name) {
		if (!name.isEmpty())
			this.admin = name;
		else
			throw new IllegalArgumentException("Fill in the name");
	}
	public void setPhoneNumber(int num) {
		this.phoneNumber = "+84"+num;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getAdmin() {
		return this.admin;
	}

	public void fetch() {
		String query = "SELECT `userName`, `customer_name`, `phone_number` FROM `customer` WHERE userName='"+this.userName+"';";     
        Connection con = null;
        Statement st = null;
		ResultSet rs = null;
		System.out.println(query);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
			boolean flag = false;
			while(rs.next()) {
				this.admin = rs.getString("customer_name");
				this.phoneNumber = rs.getString("phone_number");
			}
		}
        catch(Exception ex) {
			System.out.println("Exception : " +ex.getMessage());
        }
        finally {
            try {
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex) {}
        }
	}
	
	public void updateAdmin(String name, int phone) {
		String query = "UPDATE `customer` SET `customer`='"+name+"', `phone_Number`='+84"+phone+"' WHERE `username`='"+this.userName+"';";
		Connection con = null;
        Statement st = null;
		System.out.println(query);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.executeUpdate(query);//insert
			System.out.println("data inserted");
			JOptionPane.showMessageDialog(null,"Information Updated!");
			this.admin = name;
			this.phoneNumber = "+84"+phone;
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to update account!");
			System.out.println("Exception : " +ex.getMessage());
        }
        finally {
            try {
                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex) {}
        }
	}
}