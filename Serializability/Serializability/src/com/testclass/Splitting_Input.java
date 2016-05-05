package com.testclass;

import java.util.Scanner;

public class Splitting_Input {
	
	public static void getTransaction() {
		//int k=0;
		int s=0;
		//String input;
		String Commit_Abort;
		String Transaction;
		Integer Schedule_Len;
		//Integer Comit_Abort_Len;
		//Integer Transaction_Len;
		//Integer Commit_Index;
		Scanner in = new Scanner(System.in);
		
		Commit_Abort = "";
		Transaction = "";
		
		System.out.println("Please enter schedule/history to verify whteher it is serializable or not ");
		// Taking input from user
		String input = in.nextLine();
		Schedule_Len = input.length();
		
		in.close();
				
		while (s < Schedule_Len)
		{
			//System.out.println(s);
			System.out.println(input.substring(s,s+1));
			s++;
			
			if (input.substring(s,s+1).compareToIgnoreCase(Character.toString('c')) == 0 || 
					input.substring(s,s+1).compareToIgnoreCase(Character.toString('a')) == 0)
					{
						Commit_Abort = Commit_Abort + input.substring(s,s+1);
						s++;
						Commit_Abort = Commit_Abort + input.substring(s,s+1);
						s++;
					}
			else
			{
				Transaction = Transaction + input.substring(s,s+1);
				s++;
			}
		}
		
		System.out.println("Transaction is : " + Transaction);
		System.out.println("Comit is : " + Commit_Abort);
	}
	
	public static void main(String[] args)
	{
		getTransaction();
	}

}
