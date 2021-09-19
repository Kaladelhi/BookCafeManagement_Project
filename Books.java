package BookCafe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;


public class Books {
		int alert_val;
		String bookname;
		
		Map<String, Float> cart= new HashMap<>();


		void searchByTitle() {
			Scanner sc= new Scanner(System.in);

			System.out.println("Enter the title");
			String cat=sc.nextLine();
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
				PreparedStatement ps= con.prepareStatement("select * from books where title LIKE ?");
				ps.setString(1, '%'+cat+'%');
				ResultSet rs= ps.executeQuery();
				System.out.println("-------------------------------------------------------------------------------------------");
				System.out.println("|Title                                        | Author                  | Price    | Shelf|");
				System.out.println("-------------------------------------------------------------------------------------------");
				while(rs.next()) 
				{
					System.out.format("%1s%-45s%1s%-25s%1s%-10f%1s%-6s%1s","|",rs.getString(2),"|",rs.getString(3),"|",rs.getFloat(6),"|",rs.getString(8),"|");
					System.out.println("");
				}
				System.out.println("-------------------------------------------------------------------------------------------");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				System.out.println("Some error occurred. Redo operation.");
				return;
			}
		}

		void searchByAuthor() {
			Scanner sc= new Scanner(System.in);
			System.out.println("Write Author name ");
			String cat=sc.nextLine();
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
				PreparedStatement ps= con.prepareStatement("select * from books where author LIKE ?");
				ps.setString(1, '%'+cat+'%');
				ResultSet rs= ps.executeQuery();
				System.out.println("-------------------------------------------------------------------------------------------");
				System.out.println("|Title                                        | Author                  | Price    | Shelf|");
				System.out.println("-------------------------------------------------------------------------------------------");
				while(rs.next()) 
				{
					System.out.format("%1s%-45s%1s%-25s%1s%-10f%1s%-6s%1s","|",rs.getString(2),"|",rs.getString(3),"|",rs.getFloat(6),"|",rs.getString(8),"|");
					System.out.println("");
				}
				System.out.println("-------------------------------------------------------------------------------------------");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				System.out.println("Some error occurred. Redo operation.");
				return;
			}
		}

		void searchByGenre() {
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter the genre.");
			String cat=sc.nextLine();
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
				PreparedStatement ps= con.prepareStatement("select * from books where genre = ?");
				ps.setString(1, cat);
				ResultSet rs= ps.executeQuery();
				System.out.println("-------------------------------------------------------------------------------------------");
				System.out.println("|Title                                        | Author                  | Price    | Shelf|");
				System.out.println("-------------------------------------------------------------------------------------------");
				while(rs.next()) 
				{
					System.out.format("%1s%-45s%1s%-25s%1s%-10f%1s%-6s%1s","|",rs.getString(2),"|",rs.getString(3),"|",rs.getFloat(6),"|",rs.getString(8),"|");
					System.out.println("");
				}
				System.out.println("-------------------------------------------------------------------------------------------");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				System.out.println("Some error occurred. Redo operation.");
				return;
			}
		}


		void addBookToCart() throws NumberFormatException, IOException{
			
			int choice=0;
			String title=null;
			int quantity = 0;
			float price=0;
			String name=null;
			Scanner sc= new Scanner(System.in);

			try {
				Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
				PreparedStatement ps;
				ResultSet rs;
				boolean quit= false;

				do {
					System.out.println("Enter 1 for entering title of the book or enter anything for generate bill or reorder");
					choice= sc.nextInt();
					switch(choice) {
					case 1:
						System.out.println("Enter title of the book: ");
						
						BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
						title=bufferReader.readLine();

						ps= con.prepareStatement("select title, mrp, qty from bookcafe.books where title LIKE ?");
						ps.setString(1,'%'+title+'%');
						rs= ps.executeQuery();
						while(rs.next())
						{
							System.out.println("Want to add book "+rs.getString(1)+" to cart\n1.Yes\n2.No");
							int choose=sc.nextInt();
							if(choose==1)
							{
								System.out.println("Enter quantity:");
								quantity=sc.nextInt();
								if(rs.getInt(3)<=0) {
									System.out.println("OUT OF STOCK.");
									break;
								}
								
								else if(rs.getInt(3)<quantity)
								{
									System.out.println("Quantity of book "+rs.getString(1)+" available is :"+rs.getInt(3));
									System.out.println("Number of books available is less than the quantity you require");
									System.out.println("Do you still want to buy?\n1. Yes\n 2.No");
									int choice_another=sc.nextInt();
									if(choice_another==1)
									{
										name = rs.getString(1);
										price = rs.getFloat(2)*rs.getInt(3);
										cart.put(name, price);
										System.out.println(name+" added to cart");
										System.out.println("New Quantity=0 , RESTOCK!!");
										break;
									}
									else
									{
										quit=true;
										addBookToCart();
									}
								}
								
								else
								{
									name = rs.getString(1);
									price = (rs.getFloat(2)*quantity);
									int alert_val=alertLessThanFive(name);
									if(alert_val<=0) {
										break;
									}
									else {
										cart.put(name, price);
										System.out.println(name+" added to cart");
										break;
									}
								
								}
							}
							else
							{
								continue;
							}
						}


						break;
					default:
						System.out.println("0(generate bill) and 1(reorder): ");
						int ans= sc.nextInt();
						quit=true;
						if(ans==0) 
						{ 
							generateBookBill();
							cart.clear();
							break;
						}
						else {
							cart.clear();
							addBookToCart();
						}
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

		void generateBookBill() {
			
			float total =0; 
			int member_pts=0,choice=0;
			PreparedStatement ps;
			ResultSet rs;
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter PHONE NUMBER: ");
			String phone = sc.next();
			
			try 
			{
				Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
				ps= con.prepareStatement("select mpts from customer where cust_id = ?");
				ps.setString(1, phone);
				rs= ps.executeQuery();
				total=purchasedBookListTotal(phone);
					if(rs.next()) 
					{
						member_pts= rs.getInt(1) ;
							System.out.println("You have "+member_pts+" membership points "+ "\nDo you want to use them ?\n1.Yes\n2.No");
							choice=sc.nextInt();
							purchasedBookList(phone);
							float total_bill= (total-member_pts)>0 ? (total-member_pts) : (member_pts-total);
							if(choice==1)
							{
								System.out.format("%1s%-50s%1s%4d%2s","|","Membership points : "," ",(member_pts),"|\n");
								System.out.println("--------------------------------------------------------");
								System.out.format("%1s%-47s%1s%.4f%2s","|","TOTAL BILL :   "," ",(total_bill),"|\n");
								System.out.println("--------------------------------------------------------");
								
								
								if(total >= member_pts) {
									updateMemberShipPoints(phone,0);
								}
								else {
									int tot= (int) total_bill;
									updateMemberShipPoints(phone,tot);
								}

							}
							else 
							{
								System.out.println("--------------------------------------------------------");
								System.out.format("%1s%-47s%1s%.4f%2s","|","TOTAL BILL :   "," ",(total),"|\n");
								System.out.println("--------------------------------------------------------");
							}				
						}
						
					 else 
					 {
						purchasedBookList(phone);
						System.out.println("--------------------------------------------------------");
						System.out.format("%1s%-47s%1s%.4f%2s","|","TOTAL BILL :   "," ",(total),"|\n");
						System.out.println("--------------------------------------------------------");
					}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			catch(Exception e) {
				System.out.println("Some error occurred. Redo operation.");
				return;
			}
		}


		void updateBookQuantity(String name, float qty_book)
		{
			PreparedStatement ps;
			try
			{
				Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
				ps= con.prepareStatement("Update books set qty= (qty-?) where title = ?");
				ps.setInt(1, (int) qty_book);
				ps.setString(2,name );
				ps.executeUpdate();
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		int alertLessThanFive(String name)
		{
			PreparedStatement ps;
			ResultSet rs;
			bookname=name;
			try {
				
				Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
				ps= con.prepareStatement("select qty from books where title LIKE ?");
				ps.setString(1,'%'+name+'%');
				rs=ps.executeQuery();
				rs.next();
				int alert_val=rs.getInt(1);
				if(alert_val<=0) {
					System.out.println("Out of stock. Can't be added.");
				}
				else if(alert_val<5) {
					System.out.println("Alert: Quantity<5. Needs to be restocked. "+(alert_val-1)+" left.");
				}
				return alert_val;
			}catch(SQLException e)
			{
				e.printStackTrace();
				return 0;
			}

		}

		void updateMemberShipPoints(String phone, int mpoints)
		{
			PreparedStatement ps;
			try
			{
				Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
				ps= con.prepareStatement("Update customer set mpts=? where  cust_id = ?");
				ps.setInt(1,mpoints);
				ps.setString(2,phone );
				ps.executeUpdate();
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		float purchasedBookListTotal(String phone)
		{
			Iterator<Map.Entry<String, Float>> it= cart.entrySet().iterator();
			float total=0;
			
			while(it.hasNext()) {
				Map.Entry<String, Float> entry= it.next();
				
				total+=entry.getValue();
					
			}
			return total;
		}
		void purchasedBookList(String phone) throws Exception
		{
			PreparedStatement ps;
			ResultSet rs;
			int qty_book=0;
			Connection con = DriverManager.getConnection("jdbc:mysql:///bookcafe","root","scss");
			
			Iterator<Map.Entry<String, Float>> it= cart.entrySet().iterator();
			System.out.println("Cust ID: "+phone);		
			System.out.println("----------------------------------------------------------");
			System.out.println("|Item Name                                    |  Price   |");
			System.out.println("----------------------------------------------------------");
			while(it.hasNext()) {
				Map.Entry<String, Float> entry= it.next();
				
				ps= con.prepareStatement("select mrp from books where  title= ?");
				ps.setString(1, entry.getKey());
				rs= ps.executeQuery();
				rs.next();
				qty_book=(int)(entry.getValue()/rs.getFloat(1));
				updateBookQuantity(entry.getKey(),qty_book);
				System.out.format("%1s%-45s%1s%4f%2s","|",entry.getKey(),"|",entry.getValue(),"|\n");
			}
			//return total;
		}
		
}
