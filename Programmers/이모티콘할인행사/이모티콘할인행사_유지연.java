import java.util.*;

class Solution {
    static int [] discount ={10,20,30,40};
    static int discountResult[];
    static int maxUser,maxSales,totalUser,totalSales;
   
    public int[] solution(int[][] users, int[] emoticons) {
           
        discountResult=new int [emoticons.length];       
        reputationPermutation(0,emoticons.length,users,emoticons);
        
        int [] answer = new int[]{maxUser,maxSales};
        return answer;
    }
 
    static void reputationPermutation(int cnt,int n,int[][] users,int[] emoticons)
    {
        
        if(cnt==n)
        {
            for(int i=0; i<users.length;i++)
            {
                int userDC = users[i][0];
                int userPrice = users[i][1];
                int total =0;
                
                for(int j=0; j<emoticons.length;j++)
                {
                    
                    if(userDC<=discountResult[j])
                    {
                        int price=(int)(emoticons[j] * (100-discountResult[j])*0.01);
                        
                        total=total+price;
                    }
                    
                    if(total>=userPrice)
                    {
                        totalUser++;
                        total=0;
                        break;
                    }
                }
                totalSales+=total;
            }
            
            if(totalUser>maxUser)
            {
                maxUser = totalUser;
                maxSales =totalSales;
            }
            else if(totalUser==maxUser)
            {
                if(totalSales>maxSales)
                    maxSales=totalSales;
            }
            
            totalUser=0;
            totalSales=0;
            return;
        }
      
        for(int i=0; i<4;i++)
        {
            discountResult[cnt]= discount[i];
            reputationPermutation(cnt+1,n,users,emoticons);
        }
    }
}