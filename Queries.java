package assignment5;

public class Queries {
	final public static String order = "SELECT o.id, o.order_date, o.total_amount FROM users u"
			 +"INNER JOIN orders o ON u.id = o.user_id"
			 +"INNER JOIN items i ON i.order_id = o.id"
			 +"WHERE u.id = ?"
			 +"AND i.status = 'Shipped' "
			 +"GROUP BY i.id";
	
	final public static String insertImage = "UPDATE product"
									 + "SET image_urls = JSON_ARRAY_APPEND(image_urls, '$', ?)"
									 + "WHERE id = ?";
	
	final public static String deleteProduct = "DELETE FROM product"
								 +"WHERE id NOT IN "
								 + "(SELECT i.product_id"
								 + "FROM items i"
								 + "INNER JOIN orders o ON o.id = i.order_id"
								 + "WHERE DATEDIFF(CURDATE(), o.order_date) < 365)";
	
	final public static String countChildCategories = "SELECT parent.name as Top_Category, COUNT(*) as noOfChild"
										+"FROM category AS parent"
										+"INNER JOIN category AS child ON child.parent_id = parent.id"
										+"GROUP BY child.parent_id" 
										+"ORDER BY parent.name";
		
}
