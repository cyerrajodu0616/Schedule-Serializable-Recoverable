package com.testclass;
public class SerializationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionMessage;
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	private Integer[] nodes;
	public Integer[] getNodes() {
		return nodes;
	}

	SerializationException(){
		
	}
	
	SerializationException(String message, Integer[]nodes){
		this.exceptionMessage = message;
		this.nodes = nodes;
	}
}
