package org.nec.controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.nec.dao.AdminDao;
import org.nec.dao.BookDao;
import org.nec.dto.AdminDto;
import org.nec.dto.BookDto;
import org.nec.services.AdminDetailsNotFoundException;
import org.nec.services.BookManager;
import org.nec.services.BookNotFoundException;
import org.nec.services.LoginSuccess;
import org.nec.services.SingletonClass;

public class Librarian extends Thread{
	Scanner sc = new Scanner(System.in);
	private SingletonClass object = SingletonClass.getObject();
    private Connection connnect = object.getDataBaseConnection();
	
	public void performOperation()  throws Exception{
		
		AdminDto adto = new AdminDto();
		String query = "select id,password from teja19.admin";
		try {
			Statement stmt = connnect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Enter Id:");
			 adto.setId(sc.nextInt());
			System.out.println("Enter password");
			adto.setPassword(sc.next());
			if(rs.next()) {
				if (adto.getPassword().equals(rs.getString("password"))) {
					
				
		
		             System.out.println("Enter 1 to add a book....");
		             System.out.println("Enter 2 to search Book based on Title...");
		             System.out.println("Enter 3 to search Book based on author....");
		             System.out.println("Enter 4 to to Update price of a book...");
		             System.out.println("Enter 5 to Delete a Book...");
		             System.out.println("Enter 6 to Get all Id's of a Book....");
		             System.out.println("Enter 7 to Get all the Books Information present in the Library.....");
		     		 System.out.println("Enter 8 to Sort The Books...");
		     		 System.out.println("Enter Your Choice");
		             int choice = sc.nextInt();
		             BookDao dao = BookManager.getDao();
		             BookDto dto = null;
		             switch (choice) {
		     		case 1:
		     		{
		     			dto = new BookDto();
		     			
		     			System.out.println("Enter BookId...");
		     			dto.setBookId(sc.next());
		     			System.out.println("Enter Book Title...");
		     			
		     			dto.setBookName(sc.next());
		     			System.out.println("Enter Author Name...");
		     			dto.setAuthorName(sc.next());
		     			System.out.println("Enter Price");
		     			dto.setPrice(sc.nextDouble());
		     			System.out.println("Enter Pages..");
		     			dto.setPages(sc.nextInt());
		     			dao.addBook(dto);
		     			break;
		     		}
		     		case 2:
		    		{
		    			System.out.println("Enter Book Title:");
		    			sc.nextLine();
		    			String title = sc.nextLine();
		    			ArrayList<BookDto> bookDetails = dao.searchBookTitle(title);
		    			Iterator<BookDto> details = bookDetails.iterator();
		    			while (details.hasNext()) {
		    				dto = (BookDto) details.next();
		    				System.out.println("****************************************");
		    				System.out.println("BookId:"+dto.getBookId());
		    				System.out.println("Book Name:"+dto.getBookName());
		    				System.out.println("Book AuthorName:"+dto.getAuthorName());
		    				System.out.println("Book Price:"+dto.getPrice());
		    				System.out.println("Book pages:"+dto.getPages());
		    				System.out.println("*****************************************");
		    				
		    				
		    			}
		    			break;
		    		}
		     		case 3:
		    		{
		    			System.out.println("Enter Book Author name:");
		    			sc.nextLine();
		    			ArrayList<BookDto> bookDetails = dao.searchBookAuthor(sc.nextLine());
		    			Iterator<BookDto> details = bookDetails.iterator();
		    			while (details.hasNext()) {
		    				dto = (BookDto) details.next();
		    				System.out.println("****************************************");
		    				System.out.println("BookId:"+dto.getBookId());
		    				System.out.println("Book Name:"+dto.getBookName());
		    				System.out.println("Book AuthorName:"+dto.getAuthorName());
		    				System.out.println("Book Price:"+dto.getPrice());
		    				System.out.println("Book pages:"+dto.getPages());
		    				System.out.println("*****************************************");
		    				
		    				
		    			}
		    			break;
		    		}
		     		case 4:{
		    			System.out.println("Enter Book Id:");
		    			String id = sc.next();
		    			System.out.println("Enter Book Price");
		    			double price = sc.nextDouble();
		    			dao.updateBookPrice(id, price);
		    			break;
		    			
		    		}
		    		case 5:{
		    			System.out.println("Enter Book Id:");
		    			boolean status = dao.removeBook(sc.next());
		    			if (status) {
		    				System.out.println("Book Deleted successfully...");
		    				
		    			}
		    			break;
		    		}
		    		case 6:{
		    			ArrayList<String> bookDetails = dao.getIds();
		    			Iterator<String> details = bookDetails.iterator();
		    			while (details.hasNext()) {
		    				String id = (String) details.next();
		    				System.out.println("BookId:"+id);
		    				System.out.println("******************");
		    				
		    				
		    			}
		    			break;
		    		}
		    		case 7:{
		    			ArrayList<BookDto> bookDetails = dao.getAllBooks();
		    			Iterator<BookDto> details = bookDetails.iterator();
		    			while (details.hasNext()) {
		    				dto = (BookDto) details.next();
		    				System.out.println("****************************************");
		    				System.out.println("BookId:"+dto.getBookId());
		    				System.out.println("Book Name:"+dto.getBookName());
		    				System.out.println("Book AuthorName:"+dto.getAuthorName());
		    				System.out.println("Book Price:"+dto.getPrice());
		    				System.out.println("Book pages:"+dto.getPages());
		    				System.out.println("*****************************************");
		    				
		    				
		    			}
		    			break;
		    			
		    		}
		    		case 8:{
		    			System.out.println("Sort BookBy Your Choice...");
		    			System.out.println("Enter 1 By using BookId");
		    			System.out.println("Enter 2 By Using Book title");
		    			System.out.println("Enter 3 By Using Author name");
		    			System.out.println("Enter 4 by Using price");
		    			ArrayList<BookDto> bookDetails = dao.sortBooks(sc.nextInt());
		    			if (bookDetails != null) {
		    				Iterator<BookDto> details = bookDetails.iterator();
		    				while (details.hasNext()) {
		    					dto= (BookDto) details.next();
		    					System.out.println("****************************************");
		    					System.out.println("BookId:"+dto.getBookId());
		    					System.out.println("Book Name:"+dto.getBookName());
		    					System.out.println("Book AuthorName:"+dto.getAuthorName());
		    					System.out.println("Book Price:"+dto.getPrice());
		    					System.out.println("Book pages:"+dto.getPages());
		    					System.out.println("*****************************************");
		    					
		    					
		    				}
		    				
		    			}
		    			break;
		    		}
		    		default:
		    			System.err.println("Sorry Invalid Choice");
		    			break;
		    			
		    		}
				} else {
					throw new AdminDetailsNotFoundException();

				}
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   public void continueOrStop() {
		System.out.println("*****Welcome to Library********");
		String status = "no";
		do {
			try {
				performOperation();
				System.out.println();
				System.out.println("Enter (yes/y) to continue..");
				System.out.println("Enter (No/n) to stop...");
				status = sc.next();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println();
				System.out.println("Enter (yes/y) to continue");
				System.out.println("Enter (No/n) to stop");
				status = sc.next();
			}
			
		} while (status.equalsIgnoreCase("yes")||status.equalsIgnoreCase("y"));
		System.out.println("***********ThankYou, Visit again***********");
	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		continueOrStop();
	}
	
	

}
