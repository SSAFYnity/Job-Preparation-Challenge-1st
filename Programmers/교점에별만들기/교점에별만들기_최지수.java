import java.util.*;

class Solution {

    class wv {
        long y, x;
        public wv(long y, long x) {
            this.y = y;
            this.x = x;
        }
        public String toString() {return "[" + y + ", " + x + "]";}
    }
    
    public String[] solution(int[][] line) {
        
        PriorityQueue<wv> topQue = new PriorityQueue<>(new Comparator<wv>() {
            @Override
            public int compare(wv o1, wv o2) {
                return o2.y < o1.y ? -1 : ((o2.y == o1.y) ? 0 : 1);
            }
        });
        PriorityQueue<wv> leftQue = new PriorityQueue<>(new Comparator<wv>() {
            @Override
            public int compare(wv o1, wv o2) {
                if (o1.x == o2.x) return o2.y < o1.y ? -1 : ((o2.y == o1.y) ? 0 : 1);
                return o1.x < o2.x ? -1 : ((o1.x == o2.x) ? 0 : 1);
            }
        });
        PriorityQueue<wv> rightQue = new PriorityQueue<>(new Comparator<wv>() {
            @Override
            public int compare(wv o1, wv o2) {
                if (o1.x == o2.x) return o2.y < o1.y ? -1 : ((o2.y == o1.y) ? 0 : 1);
                return o2.x < o1.x ? -1 : ((o2.x == o1.x) ? 0 : 1);
            }
        });
        PriorityQueue<wv> bottomQue = new PriorityQueue<>(new Comparator<wv>() {
            @Override
            public int compare(wv o1, wv o2) {
                return o1.y < o2.y ? -1 : ((o1.y == o2.y) ? 0 : 1);
            }
        });
        
        for (int i = 0; i < line.length-1; i++) {
            for (int j = i+1; j < line.length; j++) {
                long a = line[i][0], b = line[i][1], e = line[i][2];
                long c = line[j][0], d = line[j][1], f = line[j][2];

                long parent = a*d-b*c;
                long ySon = e*c-a*f;
                long xSon = b*f-e*d;
                
                if (parent == 0 || ySon % parent != 0 || xSon % parent != 0) continue;                
                wv point = new wv(ySon/parent, xSon/parent);
                
                
                topQue.add(point);
                leftQue.add(point);
                rightQue.add(point);
                bottomQue.add(point);
            }
        }
        
        wv top = topQue.poll();
        wv left = leftQue.poll();
        wv right = rightQue.poll();
        wv bottom = bottomQue.poll();
        
        StringBuilder sb = new StringBuilder();
        
        int row = (int)(top.y-bottom.y)+1;
        String[] answer = new String[Math.min(row, 1000)];
        for (int i = 0; i < answer.length; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < right.x - left.x + 1; j++) {
                sb.append('.');
            }
            answer[i] = sb.toString();
        }
        
        long y = top.y, x = left.x;
        sb = new StringBuilder(answer[0]);
        sb.setCharAt((int)(top.x-x), '*');
        answer[0] = sb.toString();
        
        for (wv now : topQue) {
            sb = new StringBuilder(answer[(int)(y-now.y)]);
            sb.setCharAt((int)(now.x-x), '*');
            answer[(int)(y-now.y)] = sb.toString();
        }
        
        return answer;
    }
}