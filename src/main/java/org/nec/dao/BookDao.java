package org.nec.dao;



import java.util.ArrayList;

import org.nec.dto.BookDto;

public interface BookDao {
	void addBook(BookDto dto) throws Exception;
	ArrayList<BookDto> searchBookTitle( String title) throws Exception;
	ArrayList<BookDto> searchBookAuthor(String authorName) throws Exception;
    void updateBookPrice(String id,double price) throws Exception;
    boolean removeBook(String id) throws Exception;
    ArrayList<String> getIds() throws Exception;
    ArrayList<BookDto> getAllBooks() throws Exception;
    ArrayList<BookDto> sortBooks(int ch) throws Exception;
}
