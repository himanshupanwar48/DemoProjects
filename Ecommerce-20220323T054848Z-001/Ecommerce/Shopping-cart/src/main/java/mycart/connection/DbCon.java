package mycart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
	private static Connection connection=null;
 public static Connection getConnetion() throws ClassNotFoundException, SQLException{
	 if(connection==null) {
		// Class.forName("com.mysql.jdbc.Driver");
		 connection=DriverManager.getConnection("jdbc:msql://localhost:3306/ecommerce_cart","root","1234");
	System.out.println("connected");
	 }
return connection;
 }
 }


