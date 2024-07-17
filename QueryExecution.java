package assignment5;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class QueryExecution {
	/**
	 * Function which gives all the records for the given user_id and the product_status is shipped
	 * 
	 * @param userId checking if the product is in shipped state for the given id
	 * @param con Connection object for making connection
	 */
	public static void getAllOrders(int userId, Connection con) {
		try {
			String query = Queries.order;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				Date date = res.getDate("order_date");
				float price = res.getFloat("total_amount");
				String status = res.getString("status");
				
				System.out.println("Id: "+id+" Date: "+date+" Price: "+price+" Status: "+status);
			}
			System.out.println();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function to insert image for the given id
	 * 
	 * @param img arraylist which contains image 
	 * @param id for which id image to be inserted
	 * @param con Connection object for making connection
	 */
	public static void insertImage(List<String> img, int id, Connection con) {
		try {
			String query = Queries.insertImage;
			PreparedStatement ps = con.prepareStatement(query);
			for(int i=0; i<img.size(); i++) {
				ps.setString(1, img.get(i));
				ps.setInt(2, id);
			}
			
			int res = ps.executeUpdate();
			System.out.print(res+ " Records inserted");
			System.out.println();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Function to Delete the product which are not ordered by user in last 1 year
	 * 
	 * @param con Connection object for making connection
	 */
	public static void deleteProduct(Connection con) {
		try {
			System.out.println("Before Deletion:");
			PreparedStatement ps = con.prepareStatement("SELECT id, name, product_state FROM product");
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String status = res.getString("product_state");
				
				System.out.println("Id: "+id+" name: "+name+" Status: "+status);
			}
			System.out.println();
			
			String query = Queries.deleteProduct;
			Statement stmt = con.createStatement();
			int deleledRows = stmt.executeUpdate(query);
			System.out.println("\nNumber of products deleted: " + deleledRows);
			System.out.println();
			
			System.out.println("After Deletion: ");
			PreparedStatement ps1 = con.prepareStatement("SELECT id, name, product_state FROM product");
			ResultSet res1 = ps1.executeQuery();
			while(res1.next()) {
				int id = res1.getInt("id");
				String name = res1.getString("name");
				String status = res1.getString("product_state");
				
				System.out.println("Id: "+id+" name: "+name+" Status: "+status);
			}
			System.out.println();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function which give the parent category and the count of its children
	 * 
	 * @param con Connection object for making connection
	 */
	public static void findCategoryAndChild(Connection con) {
		try {
			String query = Queries.countChildCategories;
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				String name = res.getString("Top_Category");
				int noOfChild = res.getInt("noOfChild");
				
				System.out.println("Parent name: "+name+" No. of Child: "+noOfChild);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
