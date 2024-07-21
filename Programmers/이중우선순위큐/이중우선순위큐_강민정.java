import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    public int[] solution(String[] operations) {    // 길이가 1 이상 1,000,000 이하인 문자열 배열
        int[] answer = new int[2];
        PriorityQueue<Integer> minQue = new PriorityQueue();        // 오름차순 정렬된 우선순위 큐
        PriorityQueue<Integer> maxQue = new PriorityQueue(new Comparator<Integer>() {   // 내림차순 정렬된 우선순위 큐
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        
        for(String cmd : operations) {
            String[] arr = cmd.split(" ");
            switch(arr[0]) {
                case "I":       // 큐에 숫자를 삽입
                    minQue.add(Integer.parseInt(arr[1]));
                    maxQue.add(Integer.parseInt(arr[1]));
                    break;
                case "D":       // 큐에서 숫자를 삭제
                    if(minQue.isEmpty()) {      // 큐가 비었으면 삭제 연산을 수행하지 않음
                        continue;
                    }
                    if(arr[1].equals("1")) {        // 큐에서 최댓값 삭제
                        int maxValue = maxQue.poll();
                        minQue.remove(maxValue);                  
                    }else {     // 큐에서 최솟값 삭제
                        int minValue = minQue.poll();
                        maxQue.remove(minValue);
                    }
                    break;
            }
        }
        
        /*
            큐가 비어있으면 [0,0]
            비어있지 않으면 [최댓값, 최솟값]
        */
        if(minQue.isEmpty()) {
            return answer;
        }else {
            int maxValue = maxQue.poll();
            int minValue = minQue.poll();
            answer[0] = maxValue;
            answer[1] = minValue;
        }

        return answer;      
    }
}