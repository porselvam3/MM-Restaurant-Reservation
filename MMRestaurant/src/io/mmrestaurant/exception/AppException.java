package io.mmrestaurant.exception;

public class AppException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2170016332114754977L;

	/**
	 * 
	 */


	public AppException(String msg){
		super(msg);
	}
	
	public AppException(String msg, Throwable cause){
		super(msg,cause);
	}
}
