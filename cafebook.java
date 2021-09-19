package BookCafe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class cafebook {
	public static void clear() {
		for(int i=0;i<50;i++) {
			System.out.println();
		}
	}
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");        
		Scanner s=new Scanner(System.in);
		int sc;
		int fc;
		int bc;
		do{
			System.out.println("1.Food functions\n2.Book functions\n3.Exit");
			System.out.println("Enter choice:");
			sc=s.nextInt();
			clear();
			switch(sc) {
			case 1:
				Food_bookcafe f=new Food_bookcafe();
				do {
					System.out.println("1.Display food\n2.Search by category\n3.Start an order\n4.Exit food");
					System.out.println("Enter choice:");
					fc=s.nextInt();
					clear();
					switch(fc) {
					case 1:
						f.displayFood();
						break;
					case 2:
						f.searchBycategory();
						break;
					case 3:
						f.addToCart();
						break;
					}
					System.out.println("Press enter to continue");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					br.readLine();
					clear();
				}
				while(fc!=4);
				break;
			case 2:
				Books b= new Books();
				do {
					System.out.println("1.Search by title\n2.Search by author\n3.Search by genre\n4.Start an order\n5.Exit books");
					System.out.println("Enter choice:");
					bc=s.nextInt();
					clear();
					switch(bc) {
					case 1:
						b.searchByTitle();
						break;
					case 2:
						b.searchByAuthor();
						break;
					case 3:
						b.searchByGenre();
						break;
					case 4:
						b.addBookToCart();
						break;
					}
					System.out.println("Press Enter to continue");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					br.readLine();
					clear();
				}
				while(bc!=5);
				break;
			}
		}
		while(sc!=3);
	}
}