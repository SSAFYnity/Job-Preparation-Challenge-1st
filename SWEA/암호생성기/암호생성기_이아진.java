import java.io.*;
import java.util.*;

class Solution{
    static final int QUEUE_SIZE=8;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int t=0;

        for(int T=0;T<10;T++){
            t=Integer.parseInt(br.readLine());
            
            StringBuilder sb=new StringBuilder();

            int[] intArray=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] optimizedArray=preOptimize(intArray);

            Queue<Integer> cycleQueue=new LinkedList<>();
            
            // Queue 입력
            for(int i=0;i<QUEUE_SIZE;i++){
                cycleQueue.offer(optimizedArray[i]);
            }

            cycleQueue=cycle(cycleQueue);

            sb.append("#"+t+" ");

            for(int i=0;i<QUEUE_SIZE;i++){
                sb.append(cycleQueue.poll());
                if(i<QUEUE_SIZE-1){
                    sb.append(" ");
                }
            }

            System.out.println(sb);
        }
        
        br.close();
    }

    public static int[] preOptimize(int[] arr){
        int minQuotient=0;

        for(int i=0;i<QUEUE_SIZE;i++){
            minQuotient=Math.min(minQuotient, arr[i]/15);
        }

        for(int i=0;i<QUEUE_SIZE;i++){
            arr[i]-=(minQuotient*15);
        }

        return arr;
    }

    public static Queue<Integer> cycle(Queue<Integer> q){
        int calcResult=0;

        while(true){
            for(int i=1;i<=5;i++){
                calcResult=q.poll()-i;

                if(calcResult>0){
                    q.offer(calcResult);
                }else{
                    q.offer(0);
                    return q;
                }
            }
        }
    }
}