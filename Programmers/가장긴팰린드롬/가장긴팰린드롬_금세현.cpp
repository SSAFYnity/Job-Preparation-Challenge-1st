import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        for(int i=1;i<s.length()-1;i++){
            int len=1;
            int l=i-1;
            int r=i+1;
            while(l>=0 && r<s.length()){
                if(s.charAt(l) != s.charAt(r)) break;
                len+=2;
                l--;
                r++;
            }
            answer=Math.max(answer,len);
        }
      
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i)==s.charAt(i+1)){
                int len=2;
                int l=i-1;
                int r=i+2;
                while(l>=0 && r<s.length()){
                    if(s.charAt(l) != s.charAt(r)) break;
                    len+=2;
                    l--;
                    r++;
                }
                answer=Math.max(answer,len);
            }
        }
        return answer;
    }
}
