import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int cntA = 0;
        int [] cnts = new int [2]; // 0 -> a, b -> 1
        // a가 몇개인지 카운트
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'a') cntA++;
        }
        // 슬라이딩 윈도우 초기화
        for (int i = 0; i < cntA; i++) {
            if(s.charAt(i) == 'a') cnts[0]++;
            else if (s.charAt(i) == 'b') cnts[1]++;
        }

        if(cnts[0] == cntA) {
            System.out.println(0);
            return;
        }

        int min_change = Integer.MAX_VALUE;

        for (int i = cntA; i < s.length()*2; i++) {
            int start = (i - cntA)%(s.length());
            int end = i%(s.length());

            if(s.charAt(start)  == 'a') cnts[0]--;
            else cnts[1]--;
            if(s.charAt(end)    == 'a') cnts[0]++;
            else cnts[1]++;

            min_change = Math.min(min_change, cnts[1]);
        }
        System.out.println(min_change);
    }
}