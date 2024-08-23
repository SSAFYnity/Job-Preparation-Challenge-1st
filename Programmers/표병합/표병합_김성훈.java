import java.util.*;

class Solution {
    
    class Cell {
        int r, c, idx;
        String value;

        Cell(int idx, int r, int c) {
            this.idx = idx; this.r = r; this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Cell p = (Cell)o;
            return p.r == this.r && p.c == this.c;
        }

        @Override
        public int hashCode() {
            return r * 100 + c;
        }

    }

    int[] parents;
    Cell[] map;

    public String[] solution(String[] commands) {

        parents = new int[2501];
        map = new Cell[2501];

        int idx = 1;
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++, idx++) {
                map[idx] = new Cell(idx, i, j);
                parents[idx] = idx;
            }
        }

        List<String> ans = new ArrayList<>();
        StringTokenizer st;


        for (String command : commands) {
            st = new StringTokenizer(command);

            String cmd = st.nextToken();
            int r, c, parent;
            String value;

            switch(cmd) {

                case "UPDATE":
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    if (st.hasMoreTokens()) {
                        value = st.nextToken();
                        r = Integer.parseInt(value1);
                        c = Integer.parseInt(value2);

                        parent = find(toIdx(r, c));
                        map[parent].value = value;

                    } else {
                        for (int i = 1; i <= 2500; i++) {
                            if (Objects.equals(map[i].value, value1))
                                map[i].value = value2;
                        }
                    }

                    break;

                case "MERGE":
                    int r1 = Integer.parseInt(st.nextToken());
                    int c1 = Integer.parseInt(st.nextToken());
                    int r2 = Integer.parseInt(st.nextToken());
                    int c2 = Integer.parseInt(st.nextToken());

                    int parentA = find(toIdx(r1, c1));
                    int parentB = find(toIdx(r2, c2));
                    union(parentA, parentB);

                    break;
                case "UNMERGE":

                    r = Integer.parseInt(st.nextToken());
                    c = Integer.parseInt(st.nextToken());
                    idx = toIdx(r, c);
                    parent = find(idx);

                    value = map[parent].value;
                    List<Integer> clearList = new ArrayList<>();

                    for (int i = 1; i <= 2500; i++) {
                        if (find(i) == parent) {
                            clearList.add(i);
                        }
                    }

                    for (int i : clearList) {
                        parents[i] = i;
                        map[i].value = null;
                    }

                    map[idx].value = value;
                    break;

                case "PRINT":

                    r = Integer.parseInt(st.nextToken());
                    c = Integer.parseInt(st.nextToken());
                    idx = toIdx(r, c);
                    parent = find(idx);

                    value = map[parent].value;

                    if (value == null) ans.add("EMPTY");
                    else ans.add(value);
                    break;
            }

        }
        
        String[] answer = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) answer[i] = ans.get(i);

        return answer;
    }

    boolean union(int a, int b) {

        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB) return false;
        String valueA = map[parentA].value;
        String valueB = map[parentB].value;

        if (valueA == null) parents[parentA] = parentB;
        else parents[parentB] = parentA;

        return true;
    }

    int find(int idx) {
        if (parents[idx] == idx) return idx;
        return parents[idx] = find(parents[idx]);
    }

    int toIdx(int r, int c) {
        return (r - 1) * 50 + c;
    }
}