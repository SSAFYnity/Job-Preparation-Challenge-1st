package Easy;

import java.lang.Math;
import java.lang.Integer;
import java.lang.StringBuilder;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    int answer = 0;
    Queue<String> decimalQueue=new LinkedList<>();
    
    public int solution(int n, int k) {
        String convertedNumber=Integer.toString(n,k)+"0";
        
        for(int i=0;i<convertedNumber.length();i++){
            if(Integer.valueOf(String.valueOf(convertedNumber.charAt(i)))!=0){
                decimalQueue.offer(String.valueOf(convertedNumber.charAt(i)));
            }else{
                if(decimalQueue.isEmpty()){
                    continue;
                }else{
                    if(decimalQueue.size()==1 & Integer.valueOf(decimalQueue.peek())==1){
                        decimalQueue.poll();
                        continue;
                    }
                    
                    int stackSize=decimalQueue.size();
                    StringBuilder decimalCalc=new StringBuilder();
                    
                    for(int j=0;j<stackSize;j++){
                        // decimalCalc+=Integer.valueOf(decimalStack.pop())*((int)(Math.pow(k,j)));
                        decimalCalc.append(decimalQueue.poll());
                    }
                    
                    isPrime(Long.valueOf(decimalCalc.toString()));
                }
            }
        }
        
        return answer;
    }
    
    public void isPrime(Long number){
        if(number==1){
            return;
        }
        
        for(Long i=2L;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return;
            }
        }
        this.answer+=1;
        
        return;
    }
}