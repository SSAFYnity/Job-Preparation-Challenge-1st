package 교점에별만들기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class 교점에별만들기_이승헌 {
    static final Character POINT = '*';
    static final Character BLANK = '.';

    public static void main(String[] args) {
        String[] solution = solution(new int[][]{{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}});
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
        System.out.println();

        String[] solution1 = solution(new int[][]{{0, 1, -1}, {1, 0, -1}, {1, 0, 1}});
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution1));
        System.out.println();

        String[] solution2 = solution(new int[][]{{1000000, -1, 0}, {2, -1, 0}});
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution2));
        System.out.println();

        String[] solution3 = solution(new int[][]{{1, -1, 0}, {2, -1, 0}, {4, -1, 0}});
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution3));
    }

    private static String[] solution(int[][] line) {
        Set<Node> points = new HashSet<>();

        for (int[] one : line) {
            for (int[] two : line) {
                int denominator = one[0] * two[1] - one[1] * two[0];
                if (one[0] == two[0] && one[1] == two[1] && one[2] == two[2] || denominator == 0) {
                    continue;
                }

                long numeratorX = (long)one[1] * two[2] - (long)one[2] * two[1];
                long numeratorY = (long)one[2] * two[0] - (long)one[0] * two[2];

                if (numeratorX % denominator != 0 || numeratorY % denominator != 0) {
                    continue;
                }

                long x = numeratorX / denominator;
                long y = numeratorY / denominator;
                points.add(new Node(y, x));

            }
        }
        
        List<Node> pointsList = new ArrayList<>();
        pointsList.addAll(points);
        Collections.sort(pointsList, (o1, o2) -> Math.toIntExact(o1.row - o2.row)); // Row
        long lowRow = pointsList.get(0).row;
        long highRow = pointsList.get(points.size() - 1).row;
        Collections.sort(pointsList, (o1, o2) -> Math.toIntExact(o1.col - o2.col)); // Col
        long lowCol = pointsList.get(0).col;
        long highCol = pointsList.get(points.size() - 1).col;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.row == o2.row) {
                return Math.toIntExact(o1.col - o2.col);
            }
            return Math.toIntExact(o2.row - o1.row);
        });
        pq.addAll(points);

        StringBuilder sb = new StringBuilder();
        String[] answer = new String[(int) (highRow - lowRow + 1)];
        int idx = 0;

        Node cur = pq.poll();
        for (long curRow = highRow; curRow >= lowRow; curRow--) {
            for (long curCol = lowCol; curCol <= highCol; curCol++) {
                if (cur != null && cur.row == curRow && cur.col == curCol) {
                    sb.append(POINT);
                    cur = pq.poll();
                } else {
                    sb.append(BLANK);
                }
            }
            answer[idx] = sb.toString();
            sb.setLength(0);
            idx++;
        }

        return answer;
    }

    private static class Node {
        long row;
        long col;

        public Node(long row, long col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return row == node.row && col == node.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}