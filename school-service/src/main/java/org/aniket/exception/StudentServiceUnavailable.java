package org.aniket.exception;

public class StudentServiceUnavailable extends RuntimeException{
	
	 public StudentServiceUnavailable(String s) 
	    { 
	        // Call constructor of parent Exception 
	        super(s); 
	    } 
}
