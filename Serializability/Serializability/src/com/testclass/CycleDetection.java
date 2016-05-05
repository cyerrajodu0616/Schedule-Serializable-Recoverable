package com.testclass;
import java.util.Stack;

public class CycleDetection { 
	public static Integer [] topologicalSort(Integer adjacency_matrix[][]) throws SerializationException
    {
		int pos = 1;
        int j ;
		int rootNode=1;
		int element = rootNode;
        int i = rootNode;
		boolean flag = true;
		Stack<Integer> stack = new Stack<Integer>();
		
        Integer nodesCount = adjacency_matrix[rootNode].length ;
        Integer[] sortedNodes = new Integer [nodesCount];
        
        Integer visited[] = new Integer[nodesCount];
        for(int j1=0; j1<nodesCount; j1++)
        {
        	visited[j1]=0;
        }
        visited[rootNode] = 1;
        stack.push(rootNode);
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            while (i < nodesCount)
            {
				if(adjacency_matrix[element][i] == 1){
					if(visited[i]==1){
						if (stack.contains(i)){
							Object []stackNodes =  stack.toArray();
							Integer []cycleNodes = new Integer[stackNodes.length+1];
							System.arraycopy(stackNodes, 0, cycleNodes, 0, stackNodes.length);
							cycleNodes[stackNodes.length] = i;
							SerializationException se = new SerializationException("There is a Cycle in Serialization graph, Hence given schedule is not serializable",cycleNodes);
							throw se;
						}
					}else if(visited[i]==0){
						stack.push(i);
						visited[i] = 1;
						element = i;
						i = 1;
						continue;
					}/*else{
						i++;
						continue;
					}*/
				}
                i++;
            }
            j = stack.pop();
			if(visited[j]==1){
				sortedNodes[pos++] = j;
				visited[j]=2;
			}
            
            i = j++;
        }
        return sortedNodes;
    }

}
