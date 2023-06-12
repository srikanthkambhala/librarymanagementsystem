package org.nec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.nec.dto.BookDto;
import org.nec.services.BookNotFoundException;
import org.nec.services.SingletonClass;

public class BookDaoImplementation implements BookDao{
	private SingletonClass object = SingletonClass.getObject();
    private Connection connnect = object.getDataBaseConnection();
	PreparedStatement ps = null;

	@Override
	public void addBook(BookDto dto) throws Exception  {
		// TODO Auto-generated method stub
		String query = "insert into teja19.book values(?,?,?,?,?)";
		ps=connnect.prepareStatement(query);
		ps.setString(1, dto.getBookId());
		ps.setString(2, dto.getBookName());
		ps.setString(3, dto.getAuthorName());
		ps.setDouble(4, dto.getPrice());
		ps.setInt(5, dto.getPages());
		ps.executeUpdate();
		System.out.println("Book added successfully..!!");
		
	}

	@Override
	public ArrayList<BookDto> searchBookTitle(String title) throws Exception {
		// TODO Auto-generated method stub
		String query = " select * from teja19.book where bookname=?";
		ArrayList<BookDto> rl = new ArrayList<BookDto>();
		ps = connnect.prepareStatement(query);
		ps.setString(1, title);
		ResultSet rs = ps.executeQuery();
		if (rs.last()) {
			rs.beforeFirst();
			while (rs.next()) {
				BookDto dto = new BookDto();
				dto.setBookId(rs.getString("bookid"));
				dto.setBookName(rs.getString("bookname"));
				dto.setAuthorName(rs.getString("authorname"));
				dto.setPrice(rs.getDouble("price"));
				dto.setPages(rs.getInt("pages"));
				rl.add(dto);
				
			}
			
			return rl;
		}else {
			throw new BookNotFoundException();
			
		}
		
		
		
	}

	@Override
	public ArrayList<BookDto> searchBookAuthor(String authorName) throws Exception {
		// TODO Auto-generated method stub
		String query = "select * from teja19.book where authorname=?";
		ArrayList<BookDto> rl = new ArrayList<BookDto>();
		ps= connnect.prepareStatement(query);
		ps.setString(1, authorName);
		ResultSet rs = ps.executeQuery();
		if (rs.last()) {
			rs.beforeFirst();
			while (rs.next()) {
				BookDto dto = new BookDto();
				dto.setBookId(rs.getString("bookid"));
				dto.setBookName(rs.getString("bookname"));
				dto.setAuthorName(rs.getString("authorname"));
				dto.setPrice(rs.getDouble("price"));
				dto.setPages(rs.getInt("pages"));
				rl.add(dto);
				
				
			}
			
			return rl;
			
		} else {
			throw new BookNotFoundException();

		}
		
	}

	@Override
	public void updateBookPrice(String id, double price) throws Exception {
		// TODO Auto-generated method stub
		String query = "update teja19.book set price=? where bookid=?";
		ps=connnect.prepareStatement(query);
		ps.setDouble(1, price);
		ps.setString(2, id);
		int excuteUpdate=ps.executeUpdate();
		if (excuteUpdate>0) {
			System.out.println("Book Price update successfully....");
			
		} else {
			throw new BookNotFoundException();

		}
	}

	@Override
	public boolean removeBook(String id) throws Exception {
		// TODO Auto-generated method stub
		String query = "delete from teja19.book where bookid=?";
		ps = connnect.prepareStatement(query);
		ps.setString(1, id);
		int executeUpdate = ps.executeUpdate();
		if (executeUpdate>0) {
			return true;
			
		} else {
            throw new BookNotFoundException();
		}
		
	}

	@Override
	public ArrayList<String> getIds() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> resultList = new ArrayList<String>();
		String query = "select bookId from teja19.book";
		Statement stmt = connnect.createStatement();
		ResultSet executeQuery = stmt.executeQuery(query);
		if (executeQuery.last()) {
			executeQuery.beforeFirst();
			while (executeQuery.next()) {
				resultList.add(executeQuery.getString("bookid"));
				
			}
			return resultList;
			
		} else {
            throw new BookNotFoundException();
		}
		
	}

	@Override
	public ArrayList<BookDto> getAllBooks() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<BookDto> rl = new ArrayList<BookDto>();
		String query = "select * from teja19.book";
		Statement stmt = connnect.createStatement();
		ResultSet executeQuery = stmt.executeQuery(query);
		if (executeQuery.last()) {
			executeQuery.beforeFirst();
			while (executeQuery.next()) {
				BookDto dto = new BookDto();
				dto.setBookId(executeQuery.getString("bookid"));
				dto.setBookName(executeQuery.getString("bookname"));
				dto.setAuthorName(executeQuery.getString("authorname"));
				dto.setPrice(executeQuery.getDouble("price"));
				dto.setPages(executeQuery.getInt("pages"));
				rl.add(dto);
				
				
			}
			return rl;
			
		} else {
			throw new BookNotFoundException();

		}
		
	}

	@Override
	public ArrayList<BookDto> sortBooks(int ch) throws Exception {
		// TODO Auto-generated method stub
		switch (ch) {
		case 1:{
			ArrayList<BookDto> rl = new ArrayList<BookDto>();
			String query = "select * from teja19.book order by bookid";
			Statement stmt = connnect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.last()) {
				rs.beforeFirst();
				while (rs.next()) {
					BookDto dto = new BookDto();
					dto.setBookId(rs.getString("bookid"));
					dto.setBookName(rs.getString("bookname"));
					dto.setAuthorName(rs.getString("authorname"));
					dto.setPrice(rs.getDouble("price"));
					dto.setPages(rs.getInt("pages"));
					rl.add(dto);
					
				}
				return rl;
				
			} else {
				throw new BookNotFoundException();

			}
		}
		case 2:
		{
			ArrayList<BookDto> rl = new ArrayList<BookDto>();
			String query = "select * from teja19.book order by bookname";
			Statement stmt = connnect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.last()) {
				rs.beforeFirst();
				while (rs.next()) {
					BookDto dto = new BookDto();
					dto.setBookId(rs.getString("bookid"));
					dto.setBookName(rs.getString("bookname"));
					dto.setAuthorName(rs.getString("authorname"));
					dto.setPrice(rs.getDouble("price"));
					dto.setPages(rs.getInt("pages"));
					rl.add(dto);
					
				}
				return rl;
				
			} else {
				throw new BookNotFoundException();

			}
			
		}
		case 3:
		{
			ArrayList<BookDto> rl = new ArrayList<BookDto>();
			String query = "select * from teja19.book order by authorname";
			Statement stmt = connnect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.last()) {
				rs.beforeFirst();
				while (rs.next()) {
					BookDto dto = new BookDto();
					dto.setBookId(rs.getString("bookid"));
					dto.setBookName(rs.getString("bookname"));
					dto.setAuthorName(rs.getString("authorname"));
					dto.setPrice(rs.getDouble("price"));
					dto.setPages(rs.getInt("pages"));
					rl.add(dto);
					
				}
				return rl;
				
			} else {
				throw new BookNotFoundException();

			}
		}
		case 4:
		{
			ArrayList<BookDto> rl = new ArrayList<BookDto>();
			String query = "select * from teja19.book order by price";
			Statement stmt = connnect.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.last()) {
				rs.beforeFirst();
				while (rs.next()) {
					BookDto dto = new BookDto();
					dto.setBookId(rs.getString("bookid"));
					dto.setBookName(rs.getString("bookname"));
					dto.setAuthorName(rs.getString("authorname"));
					dto.setPrice(rs.getDouble("price"));
					dto.setPages(rs.getInt("pages"));
					rl.add(dto);
					
				}
				return rl;
				
			} else {
				throw new BookNotFoundException();

			}
		}
			
		

		default:
			System.err.println("please Enter Valid Choice.....");
			break;
		}
		return null;
		
	}

}
