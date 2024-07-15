import java.util.*;
import java.io.*;

class Solution {

    public int[] solution(String[] operations) {


        int length = operations.length;
        StringTokenizer st;

        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < length; i++) {
            st = new StringTokenizer(operations[i]);
            String operation = st.nextToken();
            int element = Integer.parseInt(st.nextToken());

            if (operation.equals("I")) {
                treeSet.add(element);
                continue;
            }

            if (element < 0) { // 최소값 삭제
                treeSet.pollFirst();
            } else { // 최대값 삭제
                treeSet.pollLast();
            }
        }

        if (treeSet.isEmpty()) return new int[] {0, 0};
        return new int[] {treeSet.last(), treeSet.first()};
    }
}