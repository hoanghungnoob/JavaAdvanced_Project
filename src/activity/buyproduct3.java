package activity;
import javax.swing.*;

import attr.Customer;
import attr.Database;
import attr.Theme;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import attr.*;
public class buyproduct3 extends JFrame implements ActionListener {
    private JLabel lblID;
    private JLabel lblProductName;
    private JLabel lblUnitPrice;
    private JLabel lblQuantityInStock;
    private JLabel lblQuantity;
    private JTextField txtQuantity;
    private JLabel lblTotalPrice;
    private JTextField txtTotalPrice;
    private JButton btnBuy;
    private JLabel lblUnitPriceValue;
    JLabel lblQuantityInStockValue;
    private Connection connection;
    private Customer customer;
    private JFrame activity; 
    private String username;
    // thêm biến cho các trường dữ liệu
    private String productID;
    private String productName;
    private String unitPrice;
    private String quantityInStock;
    public buyproduct3(JFrame prev, Customer customer,String productID, String productName, String unitPrice, String quantityInStock)   {
		this.activity = prev;
		this.customer = customer;
		// lấy username
		
		this.username = customer.getUserName();
		 
		setTitle("Buy Product");
        setSize(400, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2));

        lblID = new JLabel("ID:");
        lblProductName = new JLabel("Product Name:");
        lblUnitPrice = new JLabel("Unit Price:");
        lblQuantityInStock = new JLabel("Quantity in Stock:");
        lblQuantity = new JLabel("Quantity:");
        txtQuantity = new JTextField();
        
        lblTotalPrice = new JLabel("Total Price:");
        txtTotalPrice = new JTextField();
        txtTotalPrice.setEditable(false);
        lblUnitPriceValue = new JLabel(unitPrice);
       
        
        btnBuy = new JButton("Buy");
        this.productID = productID;
        this.productName = productName;
        this.unitPrice =unitPrice;
        this.quantityInStock = quantityInStock;
        JLabel lblProductIDValue = new JLabel(productID);
        JLabel lblProductNameValue = new JLabel(productName);
        JLabel lblUnitPriceValue = new JLabel(unitPrice);
        JLabel lblQuantityInStockValue = new JLabel(quantityInStock);
        add(lblID);
        add(lblProductIDValue);
        add(lblProductName);
        add(lblProductNameValue);
        add(lblUnitPrice);
        add(lblUnitPriceValue);
        add(lblQuantityInStock);
        add(lblQuantityInStockValue);
        add(lblQuantity);
        add(txtQuantity);
         // Empty label for spacing
        
        add(lblTotalPrice);
        add(txtTotalPrice);
        add(new JLabel());
        add(btnBuy);
        
        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyProduct();
            }
        });
        setVisible(true);
		
		}
    public void updateProduct(String name, double price, int quantity) {
		String query = "UPDATE `product` SET `product_name`='"+name+"', `selling_price`="+price+", `quantity`="+quantity+" WHERE `product_id`='"+this.productID+"';";
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
			JOptionPane.showMessageDialog(null,"Done!");
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed!");
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
    private void buyProduct() {
    	System.out.println("day la user name: "+username);
        String quantityText = txtQuantity.getText();
        int quantity = Integer.parseInt(quantityText);
		double unitPrice = Double.parseDouble(lblUnitPriceValue.getText());
        double totalPrice = unitPrice * quantity;
        txtTotalPrice.setText(String.valueOf(totalPrice));
        
        
        int confirmResult = JOptionPane.showConfirmDialog(this, "Do you want to make the purchase?", "Confirmation", JOptionPane.YES_NO_OPTION);
      
        if (confirmResult == JOptionPane.YES_OPTION) {
        	String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    		String query = "INSERT INTO `purchase` (`username`, `product_id`, `selling_price`, `quantity`, `sale_date`,`total_amount`) VALUES "
    				+ "('"+username+"','"+this.productID+"',"+unitPrice+","+quantity+", '"+date+"', "+totalPrice+");";
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
    			st.execute(query);//insert
    			System.out.println("data inserted");
    			updateProduct(this.productName, Double.parseDouble(this.unitPrice), Integer.parseInt(quantityInStock) - quantity);
    		}
            catch(Exception ex) {
    			JOptionPane.showMessageDialog(null,"Customer doesn't exist!"); 
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
    	 
    	  
          JOptionPane.showMessageDialog(this, "Purchase successful!");
          // Close the current frame or perform any other necessary actions
          dispose();
        }
    }
    
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
	
	
}