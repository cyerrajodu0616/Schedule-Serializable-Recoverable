package com.testclass;

public class ParsingInputException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String exceptionMessage;
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	
	ParsingInputException(String message){
		this.exceptionMessage = message;
	}
}
