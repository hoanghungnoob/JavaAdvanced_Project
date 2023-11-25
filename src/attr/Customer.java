package attr;

import java.lang.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import attr.*;
import activity.*;

public class Customer extends User {
	private int custormer_id;
	public int getCustormer_id() {
		return custormer_id;
	}
	public void setCustormer_id(int custormer_id) {
		this.custormer_id = custormer_id;
	}

	private String customerName;
	private String phoneNumber;
	private String address;
	private String userName;
	public static String[] columnNames = {"PurchaseID", "ProductID", "ProductName", "Cost", "Total_Amount", "Date"};
	public static String[] columnName = {"CustomerID", "CustomerName", "PhoneNumber", "Address"};
	public Customer(String userName) {
		super(userName);
		this.userName = userName;
	}
	public void setCustomerName(String name) {
		if (!name.isEmpty())
			this.customerName = name;
		else
			throw new IllegalArgumentException("Fill in the name");
	}
	public void setUserName(String userName) {
		if (!userName.isEmpty())
			this.userName = userName;
		else
			throw new IllegalArgumentException("Fill in the userName");
	}
	public String getUserName() {
		return userName;
	}
	public void setPhoneNumber(int num) {
		this.phoneNumber = "+84"+num;
	}
	public void setAddress(String address) {
		if (!address.isEmpty())
			this.address = address;
		else
			throw new IllegalArgumentException("Fill in the address");
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getAddress() {
		return address;
	}

	public void createCustomer(JFrame sa) {
		String query2 = "INSERT INTO customer ( username, password ,customer_name, phone_number, address, status) VALUES ('"+userName+"','"+password+"','"+customerName+"','"+phoneNumber+"','"+address+"','"+getStatus()+"');";
		Connection con = null;
        Statement st = null;
		System.out.println(query2);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.execute(query2);
			System.out.println("data inserted");
			JOptionPane.showMessageDialog(sa,"Account Created!");
			sa.setVisible(false);
			new Login().setVisible(true);
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(sa,"Failed to create account!");
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
	
	public void fetch() {
		String query = "SELECT `username`, `customer_name`, `phone_number`, `address` FROM `customer` WHERE username='"+this.getUserName()+"';";     
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
			
			while(rs.next()) {
				this.customerName = rs.getString("customer_name");
				this.phoneNumber = rs.getString("phone_number");
				this.address = rs.getString("address");
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
	
	public void updateCustomer(String name, int phone, String address) {
		String query = "UPDATE `customer` SET `customer_name`='"+name+"', `phone_number`='"+phone+"', `address`='"+address+"' WHERE `username`='"+this.userName+"';";
        Statement st = null;
        Connection con = null;
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
			this.customerName = name;
			this.phoneNumber = ""+phone;
			this.address = address;
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
	public void deleteCustomer() {
		String query2 = "DELETE FROM `customer` WHERE `username`='"+this.userName+"'AND `status` = '"+this.getStatus()+"' ;";
		Connection con = null;
        Statement st = null;
		System.out.println(query2);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.execute(query2);//delete
			System.out.println("data deleted");
			JOptionPane.showMessageDialog(null,"Account Deleted!");
			this.userName = "";
			this.customerName = "";
			this.phoneNumber = "";
			this.address = "";
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to delete account!");
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
	
	public DefaultTableModel myProduct() {
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		String query = "SELECT purchase.purchase_id, purchase.product_id, product.product_name, purchase.selling_price, purchase.total_amount, purchase.sale_date FROM purchase, product WHERE (`purchase`.`username`='"+this.userName+"' AND `purchase`.`product_id`=`product`.`product_id`) ORDER BY `sale_date` DESC;";     
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
			
			while(rs.next()) {
				String col1 = rs.getString("purchase_id");
				String col2 = rs.getString("product_id");
				String col3 = rs.getString("product_name");
				double col4 = rs.getDouble("selling_price");
				double col5 = rs.getInt("total_amount");
				String col6 = rs.getString("sale_date");
				model.addRow(new Object[]{col1, col2, col3, Double.valueOf(col4), Double.valueOf(col5), col6});
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
		return model;
	}
	
	public static DefaultTableModel searchCustomer(String keyword, String byWhat) {
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnName);
		String query = "SELECT * FROM `customer` WHERE `username` LIKE '%"+keyword+"%' AND `status`=1;";
		if (byWhat.equals("By Name"))
			query = "SELECT * FROM `customer` WHERE `customer_name` LIKE '%"+keyword+"%' AND `status`=1;";
		else {}
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
			
			while(rs.next()) {
				model.addRow(new Object[]{rs.getString("username"), rs.getString("customer_name"), rs.getString("phone_number"), rs.getString("address")});
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
		return model;
	}
}