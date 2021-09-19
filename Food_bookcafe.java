package BookCafe;
import java.sql.*;
import java.util.*;

public class Food_bookcafe {
	void addToCart() {
		Map<String, Integer> cart= new HashMap<>();
		int choice=0;
		int quantity = 0;
		int price=0, totPrice=0;
		String name=null;
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter: \n 1 -> Tea \n 2 -> Coffee \n 3-> Juice");
		System.out.println(" 4 -> Alfredo Pasta \n 5 -> Arrabita Pasta \n 6 -> PiriPiri Pizza \n 7-> Butter Chicken Pizza");
		System.out.println(" 8 -> Pita & Hummus \n 9-> Brownie \n 10 -> Cheesecake \n 0-> TO MOVE ON...");
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
			PreparedStatement ps;
			ResultSet rs;
			boolean quit= false;
			
			do {
				System.out.println("What do you wanna add: ");
				choice= sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 2:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 3:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 4:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 5:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 6:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 7:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 8:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 9:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 10:
				System.out.println("Enter quantity: ");
				quantity= sc.nextInt();
				ps= con.prepareStatement("select itemName,itemPrice from bookcafe.food where itemID = ?");
				ps.setLong(1,choice);
				rs= ps.executeQuery();
				if(rs.next()) {
					name = rs.getString(1);
					price = rs.getInt(2);
				}
				totPrice =price * quantity;
				cart.put(name, totPrice);
				System.out.println(name+" "+totPrice);
				break;
			case 0:
				System.out.println("0(generate bill) and 1(reorder): ");
				int ans= sc.nextInt();
				if(ans==0) 
					{
					quit=true; 
					generateFoodBill(cart);
					break;
					}
				else addToCart();
				break;
			}
			}while(!quit);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Some error occurred. Redo operation.");
			return;
		}
	}
	
	void generateFoodBill(Map<String, Integer> cart) {
		
		Iterator<Map.Entry<String, Integer>> it= cart.entrySet().iterator();
		int total =0,tax=0; 
		int member_pts=0;
		PreparedStatement ps;
		ResultSet rs;
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter PHONE NUMBER: ");
		String phone = sc.next();
		//add clear screen
		System.out.println("Cust ID: "+phone);		
		System.out.println("-------------------------------");
		System.out.println("|Item Name            | Price  |");
		System.out.println("-------------------------------");
		while(it.hasNext()) {
			Map.Entry<String, Integer> entry= it.next();
			System.out.format("%1s%-21s%1s%7d%2s","|",entry.getKey(),"|",entry.getValue(),"|\n");
			total+=entry.getValue();
		}
		tax = (int) (total * (0.09));
		//System.out.println(total * (0.09));
		System.out.format("%1s%-21s%1s%7d%2s","|","SGST : "," ",tax,"|\n");
		System.out.format("%1s%-21s%1s%7d%2s","|","CGST : "," ",tax,"|\n");
		System.out.println("-------------------------------");
		System.out.format("%1s%-21s%1s%7d%2s","|","TOTAL BILL : "," ",(total+tax),"|\n");
		System.out.println("-------------------------------");
		//update the tables in sql
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
			ps= con.prepareStatement("select mpts from bookcafe.customer where cust_id = ?");
			ps.setString(1, phone);
			rs= ps.executeQuery();
			if(rs.next()) {
				member_pts= rs.getInt(1) + (total/50);
				ps= con.prepareStatement("Update customer set mpts= ? where cust_id = ?");
				ps.setInt(1, member_pts);
				ps.setString(2, phone);
				ps.executeUpdate();
				System.out.println("UPDATED");
				System.out.println("Points="+member_pts);
			}
			else {
				member_pts= (total/50);
				ps= con.prepareStatement("Insert into bookcafe.customer (cust_id , mpts)"+" values( ?, ?)");
				ps.setString(1, phone);
				ps.setInt(2, member_pts);				
				ps.executeUpdate();	
				System.out.println("INSERTED");
				System.out.println("Points="+member_pts);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Some error occurred. Redo operation.");
			return;
		}
	}
	
	void displayFood() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
			Statement statement = con.createStatement();
			ResultSet rs= statement.executeQuery("select * from bookcafe.food");
			System.out.println("-------------------------------");
			System.out.println("|Item Name            | Price  |");
			System.out.println("-------------------------------");
			while(rs.next()) 
			{
				System.out.format("%1s%-21s%1s%7d%2s","|",rs.getString(2),"|",rs.getInt(3),"|");
				System.out.println("");
				
			}
			System.out.println("-------------------------------");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Some error occurred. Redo operation.");
			return;
		}
	}
	
	void searchBycategory() {
		Scanner sc= new Scanner(System.in);
		System.out.println("What do you want to search?? ");
		String cat=sc.next();
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
			PreparedStatement ps= con.prepareStatement("select * from bookcafe.food where category = ?");
			ps.setString(1, cat);
			ResultSet rs= ps.executeQuery();
			System.out.println("-------------------------------");
			System.out.println("|Item Name            | Price  |");
			System.out.println("-------------------------------");
			while(rs.next()) 
			{
				System.out.format("%1s%-21s%1s%7d%2s","|",rs.getString(2),"|",rs.getInt(3),"|");
				System.out.println("");
			}
			System.out.println("-------------------------------");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("Some error occurred. Redo operation.");
			return;
		}
	}

}
