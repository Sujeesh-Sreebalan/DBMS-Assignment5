package assignment5;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	
	public static void main(String[] args) throws SQLException {
		
		Scanner sc = new Scanner(System.in);
		String host = "jdbc:mysql://localhost:3306/";
		String dbName = "storefront";
		String mySqlUrl = host+dbName;
		String userId = "root";
		String password = "root";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(mySqlUrl, userId, password);
			
			System.out.print("Enter the id: ");
			int id = sc.nextInt();
			QueryExecution.getAllOrders(id, con);
			
			System.out.println("Enter the id in which image is to be inserted: ");
			int imgId = sc.nextInt();
			List<String> image = new ArrayList<>();
			image.add("img1.jpg");
			image.add("img2.jpg");
			image.add("img3.jpg");
			image.add("img4.jpg");
			image.add("img5.jpg");
			QueryExecution.insertImage(image, imgId, con);
			
			System.out.println("Deleting the products which are not ordered: ");
			QueryExecution.deleteProduct(con);
				
			System.out.println("Finding the Parent and its count of childs: ");
			QueryExecution.findCategoryAndChild(con);
			
			
		}catch(ClassNotFoundException cnfe) {
			System.out.println("Error loading driver: " + cnfe);

		}finally {
			sc.close();
			con.close();
		}
	}

}
