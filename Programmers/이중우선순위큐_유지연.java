import java.util.*;

class Solution {

    public int[] solution(String[] operations) {
        
        int length = operations.length;
        PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        
        for(int i =0; i<length; i++)
        {
            StringTokenizer st = new StringTokenizer(operations[i]);
            char op =st.nextToken().charAt(0);
            int n = Integer.parseInt(st.nextToken());
            
            if(op=='I')
            {
                pq1.offer(n);
                pq2.offer(n);
            }
            else if(op=='D')
            {
                if(!pq1.isEmpty())
                {
                    if(n==1){

                            pq1.remove( pq2.poll());
                        
                    }
                    else if(n==-1)
                    {                       
                            pq2.remove( pq1.poll());

                    }
                }
            }
        }
        
        int [] ans =new int[2];
    
      
        if(pq1.size()>0)
        {
           ans[0]=pq2.poll();
           ans[1]=pq1.poll();
        }
              
        return ans;
    }
}