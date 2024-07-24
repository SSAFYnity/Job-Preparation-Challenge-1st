import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int [2];
        
        PriorityQueue<Integer> asc = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		PriorityQueue<Integer> desc = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		int cnt = 0;
		for (int i = 0; i < operations.length; i++) {
			String type = operations[i].split(" ")[0];
			int num = Integer.parseInt(operations[i].split(" ")[1]);
			
            if (cnt == 0) {
                asc.clear();
                desc.clear();
			}
            
			if (type.equals("I")) {
				asc.add(num);
				desc.add(num);
				cnt++;
			} else {
				if (cnt == 0) continue;
                
				if (num == -1) {
					asc.poll();
				} else {
					desc.poll();
				}
                
				cnt--;
			}
		}
        
        if (cnt == 0) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = desc.peek();
            answer[1] = asc.peek();
        }
        return answer;
    }
}