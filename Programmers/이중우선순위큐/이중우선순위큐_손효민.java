import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> min = new PriorityQueue<>(); //오름차순,최소힙
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); //내림차순, 최대힙
        
        for(int i=0;i<operations.length;i++){
            String[] str = operations[i].split(" ");
            
            if(str[0].equals("I")) {
                min.add(Integer.parseInt(str[1]));
                max.add(Integer.parseInt(str[1]));
            }
            else if(!min.isEmpty() && str[1].equals("-1")) {
                // 최솟값 삭제
                int x = min.poll();
                max.remove(x);
            }
            else if(!max.isEmpty() && str[1].equals("1")) {
                // 최댓값 삭제
                int x = max.poll();
                min.remove(x);
            }
        }
        
        if(max.isEmpty()) return new int[]{0,0};
        return new int[]{max.peek(), min.peek()};
    }
}
