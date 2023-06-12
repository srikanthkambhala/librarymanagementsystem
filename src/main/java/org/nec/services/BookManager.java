package org.nec.services;

import org.nec.dao.BookDao;
import org.nec.dao.BookDaoImplementation;

public class BookManager {
	public static BookDao getDao() {
		BookDao dao = new BookDaoImplementation();
		return dao;
		
	}

}
