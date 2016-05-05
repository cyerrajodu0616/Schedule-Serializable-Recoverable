package com.testclass;
public class ResultObject {
	private String message;
	

	public String getMessage() {
		return message;
	}

	private Integer[] nodes;
	public Integer[] getNodes() {
		return nodes;
	}
	
	private boolean flag = false;

	public boolean isFlag() {
		return flag;
	}
	
	private Integer[][] adjacecyMatrix;

	public Integer[][] getAdjacecyMatrix() {
		return adjacecyMatrix;
	}

	ResultObject(){
		
	}
	
	ResultObject(String message, Integer[]nodes,boolean resultFlag, Integer[][] adjacencyMatrix){
		this.message = message;
		this.nodes = nodes;
		this.flag = resultFlag;
		this.adjacecyMatrix = adjacencyMatrix;
	}
}
