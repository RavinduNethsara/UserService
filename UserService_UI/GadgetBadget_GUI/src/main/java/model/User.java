package model;

import java.sql.*; 

public class User {

private static Connection connect() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			
			System.out.println("succsess");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}

	//insert
	public String insertuser(String userType, String userName ,String userEmail, String userPassword , String userPhone, String userAddress) 
	 { 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for inserting."; 
			 } 
		 	 	 // create a prepared statement
			 	 String query = "INSERT INTO user(userType, userName, userEmail, userPassword, userPhone, userAddress) VALUES (?, ?, ?, ?, ?, ?)"; 
			 	 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 					 
				 
				 // binding values
//				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(1, userType);
				 preparedStmt.setString(2, userName);
				 preparedStmt.setString(3, userEmail);
				 preparedStmt.setString(4, userPassword);
				 preparedStmt.setString(5, userPhone);
				 preparedStmt.setString(6, userAddress);
	
				 
				 				 
				 preparedStmt.execute(); 
				 con.close(); 
				 String newUser = readuser(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
				 //output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 //output = "Error while inserting the order.";
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";
			 System.err.println(e.getMessage());
			 System.out.println(e.getMessage());
				System.out.println(e);
				e.printStackTrace();
			
		 } 
	 	return output; 
	 } 
	
	//Read orders
	 public String readuser() 
	 { 
		 String output = ""; 

		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for reading."; 
			 } 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'>"+ "<tr><th>user ID</th>" + 
			 "<th>User Type</th>" + 
			 "<th>User Name</th>" + 
			 "<th>User Email</th>" + 
			 "<th>User Password</th>" +
			 "<th>userPhone</th>" +
			 "<th>User Address</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 
		 
			 
			 String query = "SELECT * FROM user"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String userID = Integer.toString(rs.getInt("userID")); 
				 String userType = rs.getString("userType"); 
				 String userName = rs.getString("userName");  
				 String userEmail = rs.getString("userEmail"); 
				 String userPassword = rs.getString("userPassword");
				 String userPhone = rs.getString("userPhone"); 
				 String userAddress = rs.getString("userAddress");
				 
				 // Add into the html table
				 output += "<td>" + userID + "</td>";
				 output += "<td>" + userType + "</td>";
				 output += "<td>" + userName + "</td>";   
				 output += "<td>" + userEmail + "</td>"; 
				 output += "<td>" + userPassword + "</td>";
				 output += "<td>" + userPhone + "</td>"; 
				 output += "<td>" + userAddress + "</td>"; 					 
				 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ userID +"' >Remove</button></td></tr>";
			 } 
			 	 con.close(); 
			 	 // Complete the html table
			 	 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the order"; 
			 System.err.println(e.getMessage()); 
		 } 
	 	 return output; 
	 } 
	 
	 
		//Update orders
	 public String updateuser(String userID,String userType, String userName , String userEmail, String userPassword , String userPhone, String userAddress)
		{ 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 } 
				 
				 // create a prepared statement
				 String query = "UPDATE user SET userType=? ,userName=? , userEmail=? , userPassword=?,  userPhone=? , userAddress=? WHERE userID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 
				 
				 // binding values
				 preparedStmt.setString(1, userType); 
				 preparedStmt.setString(2, userName); 	  
				 preparedStmt.setString(3, userEmail); 
				 preparedStmt.setString(4, userPassword);
				 preparedStmt.setString(5, userPhone); 
				 preparedStmt.setString(6, userAddress);  
				 preparedStmt.setInt(7, Integer.parseInt(userID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 //output = "Updated successfully"; 
				 String newUser = readuser(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}"; 

			 } 
			 catch (Exception e) 
			 { 
				 //output = "Error while updating the item."; 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the order.\"}"; 
				 System.err.println(e.getMessage()); 
				 System.out.println(e);
			 } 
			 	return output; 
			 } 
		
		
			//Delete orders
			 public String deleteuser(String userID) 
			 { 
				 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 
			 	 // create a prepared statement
				 String query = "DELETE FROM user WHERE userID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(userID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 //output = "Deleted successfully"; 
				 String newOrder = readuser(); output = "{\"status\":\"success\", \"data\": \"" + newOrder + "\"}";

			 } 
			 catch (Exception e) 
			 { 
				 //output = "Error while deleting the order."; 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the order.\"}"; 
				 System.err.println(e.getMessage()); 
				 System.out.println(e);
			 } 
			 return output;
			 }
}
