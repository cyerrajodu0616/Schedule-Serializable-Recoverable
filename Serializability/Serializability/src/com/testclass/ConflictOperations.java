package com.testclass;
import java.util.Arrays;

public class ConflictOperations {
	public static Integer[][] load_Conflict(String[] operation, Integer[] transaction, String[] dataitem) {

		Integer i = 0;
		Integer j = 0;
		Integer count = 0;
		
		
		for ( i=1;i<transaction.length;i++)
		{
			if(count < transaction[i])
				count = transaction[i];
		}
		
				
		Integer[][] conflict = new Integer[count+1][count+1];
		for(i=0;i<count+1;i++){
			for(j=0;j<count+1;j++){
				conflict[i][j]=0;
			}
		}
		for (i = 1; i < operation.length; i++) {
			for (j = i ; j < operation.length; j++) {
				if (operation[j].compareToIgnoreCase(Character.toString('w'))==0) {
					if (transaction[i] != transaction[j] && 
							dataitem[i].compareToIgnoreCase(dataitem[j] ) == 0) {
						conflict[transaction[i]][transaction[j]] = 1;
					}
				} 
				else {
					if (transaction[i] != transaction[j] && 
							dataitem[i].compareToIgnoreCase(dataitem[j] ) == 0 && 
									operation[j].compareToIgnoreCase(Character.toString('w')) == 0) 
					{
						conflict[transaction[i]][transaction[j]] = 1;
					} 
					
				}

			}

		}
		
		return conflict;
	}

}
