import java.util.*;

class Solution {
    
    public static class Rock implements Comparable<Rock> {
        int order, diff, iron, dia;
        public Rock(int order, int diff, int iron, int dia) {
            this.order = order;
            this.diff = diff;
            this.iron = iron;
            this.dia = dia;
        }
        public int compareTo(Rock o) {
            boolean s = o.diff == this.diff;
            boolean i = o.iron == this.iron;
            boolean d = o.dia == this.dia;
            if (s && i) return this.dia - o.dia;
            else if (s) return o.iron - this.iron;
            return o.diff - this.diff;
        }
        public String toString() {
            return "{" + order + ", " + diff + ", " + iron + ", " + dia + "}";
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int canMine = 0;
        for (int i = 0; i < 3; i++) {
            canMine+=picks[i];
        }
        canMine = Math.min(minerals.length, canMine*5);
        
        PriorityQueue<Rock> que = new PriorityQueue<>();
        for (int i = 0; i < canMine; i+=5) {
            int diff = 0;
            int iron = 0;
            int dia = 0;
            for (int j = i; j < Math.min(i+5, canMine); j++) {
                if (minerals[j].equals("diamond")) {
                    diff += 25;
                    iron += 5;
                }
                else if (minerals[j].equals("iron")) {
                    diff += 5;
                    iron += 1;
                }
                else {
                    diff += 1;
                    iron += 1;
                }
                dia++;
            }
            que.add(new Rock(i/5, diff, iron, dia));
        }
        
        while (!que.isEmpty()) {
            Rock now = que.poll();
            if (picks[0] > 0) {
                picks[0]--;
                answer += now.dia;
            } else if (picks[1] > 0) {
                picks[1]--;
                answer += now.iron;
            } else {
                picks[0]--;
                answer += now.diff;
            }
        }
        
        return answer;
    }
}