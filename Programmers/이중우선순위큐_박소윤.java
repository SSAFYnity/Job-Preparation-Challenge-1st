import java.util.*;

class Solution {
    
    static class DoublePriorityQueue {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = 
            new PriorityQueue<>((o1, o2) -> -1 * Integer.compare(o1, o2));
        int size = 0;
        
        public void insert(int number) {
            minHeap.offer(number);
            maxHeap.offer(number);
            size++;
        }

        public void remove(int number) {
            if (size == 0) {
                return;
            }
            if (number == 1) {
                int removed = maxHeap.poll();
                minHeap.remove(removed);
            }
            if (number == -1) {
                int removed = minHeap.poll();
                maxHeap.remove(removed);
            }
            size--;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }
        
        public int getMinValue() {
            return minHeap.peek();
        }
        
        public int getMaxValue() {
            return maxHeap.peek();
        }
    }
    
    public int[] solution(String[] operations) {
        
        DoublePriorityQueue pq = new DoublePriorityQueue();

        for (String operation : operations) {
            String[] splitted = operation.split(" ");
            
            String command = splitted[0];
            int number = Integer.parseInt(splitted[1]);
            
            switch(command) {
                case "I" -> pq.insert(number);
                case "D" -> pq.remove(number);
            }
        }
        if (pq.isEmpty()) {
            return new int[]{ 0, 0 };
        }
        return new int[]{ pq.getMaxValue(), pq.getMinValue() };
    }
}