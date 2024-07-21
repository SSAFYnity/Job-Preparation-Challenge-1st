package 이중우선순위큐;

import java.util.TreeSet;

public class 이중우선순위큐_이승헌 {
    public static void main(String[] args) {
        solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"});
        solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"});
    }

    public static int[] solution(String[] operations) {
        int[] answer = new int[2];

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (String operation : operations) {
            String[] s = operation.split(" ");
            if (s[0].equals("I")) { // Insert
                treeSet.add(Integer.parseInt(s[1]));
            } else if (s[0].equals("D")) { // Delete
                if (s[1].equals("-1")) { // Min
                    treeSet.pollFirst();
                }else{ // Max
                    treeSet.pollLast();
                }
            }
        }

        if(!treeSet.isEmpty()){
            answer[0] = treeSet.last();
            answer[1] = treeSet.first();
        }
        return answer;
    }
}
