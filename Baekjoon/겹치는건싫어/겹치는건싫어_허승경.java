import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int [] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int [] cnt = new int[100001];

        int start = 0;
        int end = 0;
        int maxLen = 0;

        while(start <= end && end < n){
            if(cnt[arr[end]] + 1 <= k){
                cnt[arr[end]]++;
                end++;
                maxLen = Math.max(maxLen, end-start);
            }else{
                cnt[arr[start]]--;
                start++;
            }
        }

        System.out.print(maxLen);
    }
}
