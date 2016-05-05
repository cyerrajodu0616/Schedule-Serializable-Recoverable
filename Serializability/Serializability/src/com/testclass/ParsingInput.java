package com.testclass;
import java.util.Scanner;

public class ParsingInput {
	
	public static ResultObject getTransaction(String input)  throws ParsingInputException,SerializationException 
	{
		
		Integer k=0;
		Integer s=0;
		Integer i=0;
		Integer count=0;
		ResultObject Ro1;
		String output = null;
		String Commit_Abort;
		String Transaction;
		Integer Schedule_Len;
		Integer Comit_Abort_Len;
		//Scanner in = new Scanner(System.in);
		
		//System.out.println("Please enter schedule/history to verify whteher it is serializable or not ");
		//String input = in.nextLine();
		
		//in.close();
		
		Schedule_Len = input.length();
		
		Commit_Abort = "";
		Transaction= "";
		
		if (input.substring(0,1).compareToIgnoreCase(Character.toString('c')) == 0 || 
				input.substring(0,1).compareToIgnoreCase(Character.toString('a')) == 0)
		{
			throw new ParsingInputException("Given Schedule is starting with Commit/abort, Pls give dorrect schedule ");
			}
		
			
		while (s < Schedule_Len)
		{
						
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
		
		
		Schedule_Len = Transaction.length();
		
		if (Schedule_Len%3 != 0)
		{
			throw new ParsingInputException("Given Schedule having incorrect format, Pls check format ");
		}
		
		Comit_Abort_Len = Commit_Abort.length();
			
		Integer transaction[] = new Integer[Transaction.length()/3+1];
		String dataitem[] = new String[Transaction.length()/3+1];
		String operation[] = new String[Transaction.length()/3+1];
		ResultObject ro = null;
		
		for(i=0;i<transaction.length;i++)
		{
			transaction[i]=0;
			dataitem[i]="";
			operation[i]="";
		}
		
		int l=1;
		while ( k < Schedule_Len)
		{	
			
			operation[l] = Transaction.substring(k,k+1);
			k++;
			transaction[l] = Integer.parseInt(Transaction.substring(k,k+1));
			k++;
			dataitem[l] = Transaction.substring(k,k+1);
			k++;
			
			/*
			if ( Transaction.substring(k, k+1).compareToIgnoreCase(Character.toString('r')) == 0 ||
					Transaction.substring(k, k+1).compareToIgnoreCase(Character.toString('w')) == 0)
			{
			operation[l] = Transaction.substring(k,k+1);
			k++;
			}
			transaction[l] = Integer.parseInt(Transaction.substring(k,k+1));
			k++;
			
			if ( Transaction.substring(k, k+1).compareToIgnoreCase(Character.toString('r'))==1 ||
					Transaction.substring(k, k+1).compareToIgnoreCase(Character.toString('w'))==1 ||
							Transaction.substring(k, k+1).compareToIgnoreCase(Character.toString('a'))==1 ||
									Transaction.substring(k, k+1).compareToIgnoreCase(Character.toString('c'))==1)
			{
			dataitem[l] = Transaction.substring(k,k+1);
			k++;
			}*/
			l=l+1;
		}
		
		for ( i=1;i<transaction.length;i++)
		{
			if(count < transaction[i])
				count = transaction[i];
		}

		Integer[][] adjacency_matrix = ConflictOperations.load_Conflict(operation,transaction,dataitem);
		
		try {
			Integer[] topologicalSort = CycleDetection.topologicalSort(adjacency_matrix);
			ro = new ResultObject("This History/Schedule is Serializable",topologicalSort,true, adjacency_matrix);
			
			if ( Comit_Abort_Len > (transaction.length-1)*2 )
			{
				throw new ParsingInputException("Given schedule is Serializable but, No.of commit/abort are greater than no.of transactions");
			}
			
			i=1;
			while(i<Transaction.length())
			{	
				if (Commit_Abort.length() - Commit_Abort.replace(Integer.toString(i), "").length() > 1 )
				{
					throw new ParsingInputException("Given schedule is Serializable but, Schedule is having more than one comit/abort for a transaction: "+i);
				}
				i = i+2;
			}
			
			i=1;
			while(i<count)
			{	
				if (Commit_Abort.length() - Commit_Abort.replace(Integer.toString(i), "").length() == 0 )
				{
					throw new ParsingInputException("Given schedule is Serializable but, Schedule having active transactions and fate of those transactions are unknow: "+i);
				}
				i = i+2;
			}
				
			
			if(Commit_Abort.length() > 0)
			{
				output = Recoverbility.Find_Recoverbility(operation,transaction,dataitem,Commit_Abort,count);
				output = "Given schedule is Serializable" + output;
				ro = new ResultObject(output,topologicalSort,true, adjacency_matrix);
			}	
			else
			{
				throw new ParsingInputException("We don't know the fate of transaction, So we can't find out weather given schedule is recoverable or not");
			}
			
			
			return ro;
		} catch (SerializationException se) {
			ro = new ResultObject(se.getExceptionMessage(),se.getNodes(),false, adjacency_matrix);
			return ro;
		}
		catch (ParsingInputException pe) {
			ro = new ResultObject(pe.getExceptionMessage(),null,false, adjacency_matrix);
			return ro;
			//e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) 
	{
		ResultObject Op = null;
		//String Op = null;
			try {
				Scanner in = new Scanner(System.in);
				
				System.out.println("Please enter schedule/history to verify whteher it is serializable or not ");
				String input = in.nextLine();
				
				in.close();
				Op = getTransaction(input);
				System.out.println(Op.getMessage());
			} catch (ParsingInputException e) {
				System.out.println(e.getExceptionMessage());
				//e.printStackTrace();
			}
		catch (SerializationException se) {
			System.out.println(se.getExceptionMessage());
		}
	}
	
}
