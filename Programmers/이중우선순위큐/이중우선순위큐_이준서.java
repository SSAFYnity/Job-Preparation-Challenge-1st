import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int length = operations.length;
        StringTokenizer st = null;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i<length; i++){
            st = new StringTokenizer(operations[i]);
            char c = st.nextToken().charAt(0);
            int val = Integer.parseInt(st.nextToken());
            if(c == 'I'){
                minHeap.add(val);
                maxHeap.add(val);
            }
            else{
                if(val == -1 && !minHeap.isEmpty()){
                    int mn = minHeap.poll();
                    maxHeap.remove(mn);
                }
                else if(val == 1 && !maxHeap.isEmpty()){
                    int mx = maxHeap.poll();
                    minHeap.remove(mx);
                }
            }
        }
        if(maxHeap.isEmpty() || minHeap.isEmpty()) return new int[] {0,0};
        else return new int[] {maxHeap.peek(),minHeap.peek()};
    }
}
