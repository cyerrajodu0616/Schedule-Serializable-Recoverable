package com.testclass;
public class Recoverbility {
	
	public static String Find_Recoverbility(String[] operation, 
										  Integer[] transaction, 
										  String[] dataitem,
										  String Commit_Abort,
										  Integer count)
	{
		
		Integer i = 0;
		Integer j = 0;
		
		Integer Main_Transaction_Int = 0;
		Integer Dependent_Transaction_Int = 0;
		
		String Main_Transaction_Str;
		String Depen_Transaction_Str;
		
		Integer Main_Transaction_Commit;
		Integer Depen_Transaction_Commit;
		
		Integer[][] Reads_From  = new Integer[count+1] [count+1];
		
		//Integer Recoverable = 0;
		Integer Non_Recoverable =0;
		
		String Result = null;
		
		
		for (i=0;i<Reads_From.length;i++)
		{
			for (j=0;j<Reads_From.length;j++)
			{
					Reads_From[i][j]=0;
			}
		}

		
		// looping to find out reads from relation
		for (i = 1; i < operation.length-1; i++) 
		{	
			for ( j = i; j<operation.length ; j++)
			{
				if (operation[i].compareToIgnoreCase(Character.toString('w'))==0) {
					if (transaction[i] != transaction[j] && 
						dataitem[i].compareToIgnoreCase(dataitem[j]) == 0 &&
						operation[j].compareToIgnoreCase(Character.toString('w'))==0)
							{	
						break;	
					}
					else
					{
						if (transaction[i] != transaction[j] && 
								dataitem[i].compareToIgnoreCase(dataitem[j]) == 0 &&
								operation[j].compareToIgnoreCase(Character.toString('r'))==0)
									{					
							Reads_From[transaction[i]][transaction[j]]=1;
							}	
					} 

		}
	}
}
		

for (i=0;i<Reads_From.length && Non_Recoverable == 0 ;i++)
{
	for (j=0;j<Reads_From.length && Non_Recoverable ==0;j++)
	{
		
		if ( Reads_From[i][j] == 1 )
		{
			Main_Transaction_Int = i;
			Dependent_Transaction_Int =j;
			
			
			Main_Transaction_Commit = Commit_Abort.indexOf(Integer.toString(i));
			Depen_Transaction_Commit = Commit_Abort.indexOf(Integer.toString(j));
			
			Main_Transaction_Str = Commit_Abort.substring(Commit_Abort.indexOf(Integer.toString(i))-1, Commit_Abort.indexOf(Integer.toString(i)));
			Depen_Transaction_Str = Commit_Abort.substring(Commit_Abort.indexOf(Integer.toString(i))-1, Commit_Abort.indexOf(Integer.toString(i)));
			
			if ( Main_Transaction_Commit < Depen_Transaction_Commit)
			{
				if (Main_Transaction_Str.compareToIgnoreCase(Character.toString('a'))==0)
				{
					Non_Recoverable++;
				}
			}
			else
			{
				if (Main_Transaction_Str.compareToIgnoreCase(Character.toString('c'))==0)
				{
					Non_Recoverable++;
				}
			}
			
			
			
		}
		
	}
}

if ( Non_Recoverable != 0)
{
	Result = "Given Schedule is not recoverable";
}
else
{
	Result = "Given Schedule is recoverable";
}

return Result;

}
	
}