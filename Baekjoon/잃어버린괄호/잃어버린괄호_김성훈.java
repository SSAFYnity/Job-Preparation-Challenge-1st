import java.util.*;
import java.io.*;


public class Main {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuffer sb = new StringBuffer();

    public static void main(String args[])throws IOException {

        String[] splitMinus = br.readLine().split("-");

        int splitMinusLen = splitMinus.length;
        String[][] splitPlus = new String[splitMinusLen][];
        int[] sumSplitMinus = new int[splitMinusLen];

        for (int i = 0; i < splitMinusLen; i++) {
            splitPlus[i] = splitMinus[i].split("\\+");
            for (int j = 0; j < splitPlus[i].length; j++) {
                sumSplitMinus[i] += Integer.parseInt(splitPlus[i][j]);
            }
        }

        int minSum = sumSplitMinus[0];

        for (int i = 0; i < splitMinusLen; i++) {
            minSum -= sumSplitMinus[i];
        }


        System.out.println(minSum);
    }
}