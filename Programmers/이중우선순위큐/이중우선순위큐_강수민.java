import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] operations) {

        Queue<Integer> minQue = new PriorityQueue<>();
        Queue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());
        int size = 0;

        for(String s : operations) {
            StringTokenizer st = new StringTokenizer(s);

            if(st.nextToken().charAt(0) == 'I') { // 큐 삽입
                int input = Integer.parseInt(st.nextToken());
                minQue.add(input);
                maxQue.add(input);
                size++;
            } else if(Integer.parseInt(st.nextToken()) == 1) { // 최대힙 삽입
                if(size-- > 0) maxQue.poll();
                if(size == 0) maxQue.clear();
            } else { // 최소힙 삽입
                if(size-- > 0) minQue.poll();
                if(size == 0) minQue.clear();
            }
        }
        if (size > 0)
            return new int[] { maxQue.poll(), minQue.poll() };
        else {
            return new int[] { 0, 0 };
        }
    }
}