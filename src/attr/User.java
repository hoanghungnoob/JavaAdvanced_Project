package attr;

import java.lang.*;
import java.sql.*;
import attr.*;
import javax.swing.*;
import activity.*;


public abstract class User {
	protected String userName;
	protected String password;
	final protected int status =1;
	
	public User(String userName) {
		if (!userName.isEmpty())
			this.userName = userName;
		else
			throw new IllegalArgumentException("Fill in the User Name");
	}
	
	public abstract void fetch();
	
	public String getUserName() {
		return userName;
	}
	public int getStatus() {
		return status;
	}

	public void setPassword(String passwd) {
		if (!passwd.isEmpty())
			this.password = passwd;
		else
			throw new IllegalArgumentException("Fill in the password");
	}
	
	public static int checkStatus(String uid, String pass) {
		int result = -1;
		String query = "SELECT `userName`, `password`,`status` FROM `customer`;";     
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
                String userName = rs.getString("userName");
                String password = rs.getString("password");
				int status = rs.getInt("status");
				
				if(userName.equals(uid) && password.equals(pass)) {
					result = status;
				}
			}
		}
        catch(Exception ex) {
			System.out.println("Exception : " +ex.getMessage());
			ex.printStackTrace();
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
		return result;
	}
	
//	public void changePassword(ChangePasswordActivity a, String oldPass, String newPass) {
//		String query = "UPDATE `login` SET `password`='"+newPass+"' WHERE (`userId`='"+this.userId+"' AND `password`='"+oldPass+"');";
//		Connection con = null;
//        Statement st = null;
//		System.out.println(query);
//        try {
//			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("driver loaded");
//			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
//			System.out.println("connection done");//connection with database established
//			st = con.createStatement();//create statement
//			System.out.println("statement created");
//			int res = st.executeUpdate(query);//insert
//			System.out.println("data inserted");
//			if (res > 0) {
//				JOptionPane.showMessageDialog(null,"Password Updated!");
//			a.setVisible(false);
//			}
//			else {
//				JOptionPane.showMessageDialog(null,"Password didn't match!");
//			}
//		}
//        catch(Exception ex) {
//			System.out.println("Exception : " +ex.getMessage());
//        }
//        finally {
//            try {
//                if(st!=null)
//					st.close();
//
//                if(con!=null)
//					con.close();
//            }
//            catch(Exception ex) {}
//        }
//	}
}