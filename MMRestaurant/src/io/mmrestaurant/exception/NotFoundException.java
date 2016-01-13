package io.mmrestaurant.exception;

public class NotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7087929588662603459L;

	public NotFoundException(String msg){
		super(msg);
	}
	
	public NotFoundException(String msg, Throwable cause){
		super(msg,cause);
	}
}


