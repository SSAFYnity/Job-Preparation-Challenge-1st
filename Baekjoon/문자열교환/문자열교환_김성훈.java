import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{

        char[] str = br.readLine().toCharArray();
        int size = str.length;
        int aCnt = 0;
        for (char c : str) {
            if (c == 'a') aCnt++;
        }

        int min = 1001;
        for (int start = 0; start < size; start++) {
            int bCnt = 0;
            for (int i = 0; i < aCnt; i++) {
                int cur = (start + i) % size;
                if (str[cur] == 'b') bCnt++;
            }
            min = Math.min(min, bCnt);
        }
        System.out.println(min);
    }
}