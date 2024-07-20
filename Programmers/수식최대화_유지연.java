import java.util.*;

class Solution {
    
    static int result[];
    static String expressions;
    static ArrayList<String> operation = new ArrayList<>();
    static LinkedList<String> expressionList = new LinkedList<String>();
    
    static long max = Long.MIN_VALUE;
    public long solution(String expression) {
        long answer = 0;
        
        
        
        StringTokenizer st = new StringTokenizer(expression,"-|*|+",true);
        boolean flag = true;
        
        while(st.countTokens()>0)
        {
            String input = st.nextToken();
            
            if(flag){
                expressionList.add(input);
                flag =false;
            }
            else
            {
                if(!operation.contains(input))
                    operation.add(input);
                
                expressionList.add(input);
                flag= true;
                
            }
            
        }
        
        result= new int[operation.size()];
        
        permutation(0,new boolean[operation.size()]);

        return max;
    }
    
    static void permutation(int cnt, boolean [] visited)
    {
        if(cnt==operation.size())
        {
            calculate(result,expressions);
            return;
        }
        for(int i=0; i<operation.size(); i++)
        {
            if(!visited[i])
            {
                result[cnt]=i;
                visited[i]=true;
                permutation(cnt+1,visited);
                visited[i]=false;
                
            }
        }
    }
    
    static void calculate(int[] priority,String input)
    {
       LinkedList<String> copy = new LinkedList<>(expressionList);

			for (int i = 0; i < operation.size(); i++) {

				String priorOp = operation.get(priority[i]);

				int index = 1;

				while (index < copy.size()) {

					if (copy.get(index).equals(priorOp)) {
						long n1 = Long.parseLong(copy.get(index - 1));
						long n2 = Long.parseLong(copy.get(index + 1));
						long result = operationCalculate(n1, n2, priorOp.charAt(0));

						copy.subList(index - 1, index + 2).clear();
						copy.add(index - 1, Long.toString(result));
					} else {

						index = index + 2;
					}
				}
			}

			max = Long.max(max, Math.abs(Long.parseLong(copy.get(0))));
      
    
    }
              
    
    public static long operationCalculate(long n1,long n2, char op)
    {
        switch(op)
        {
            case '+':
                return n1+n2;
            case '-':
                return n1-n2;
            case '*':
                return n1*n2;
        }
        
        return -1;
    }
    
    
}