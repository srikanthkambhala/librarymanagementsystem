package org.nec.services;

public class BookNotFoundException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "No Book Record Found...";
	}
	

}
