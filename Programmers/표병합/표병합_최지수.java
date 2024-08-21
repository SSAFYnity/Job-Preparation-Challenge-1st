import java.util.*;

class Solution {
    // int[50][50] 해서 각 위치의 값이 map 키 값(0인 경우 초기 상태)
    // map은 인덱스: value
    // 이 인덱스를 가지는 셀 위치들도 맵으로 가지고 있어야 할 듯.(unmerge를 위해)
    
    static int[][] excel = new int[51][51];
    static Map<Integer, String> cell = new HashMap<>();
    static Map<Integer, Set<int[]>> locations = new HashMap<>();
    static int index = 1;
    static ArrayList<String> list = new ArrayList<>();
    
    public void updateLoc(StringTokenizer st) {
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        String val = st.nextToken();
        
        if (excel[r][c] == 0) {
            cell.put(index, val);
            locations.put(index, new HashSet<>());
            locations.get(index).add(new int[] {r,c});
            excel[r][c] = index;
            index++;
        } else {
            int cellIdx = excel[r][c];
            cell.put(cellIdx, val);
        }
    }
    
    public void updateVal(StringTokenizer st) {
        String one = st.nextToken();
        String two = st.nextToken();
        
        for (Map.Entry<Integer, String> entry : cell.entrySet()) {
            if (!cell.containsValue(one)) {
                break;
            }
            if (entry.getValue().equals(one)) {
                cell.replace(entry.getKey(), two);
            }
        }
    }
    
    public void merge(StringTokenizer st) {
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        
        if ((r1 == r2 && c1 == c2) || (excel[r1][c1] != 0 && excel[r1][c1] == excel[r2][c2])) {
            return;
        }
        
        if (excel[r1][c1] == 0 && excel[r2][c2] == 0) {
            excel[r1][c1] = index;
            cell.put(index, "");
            locations.put(index, new HashSet<>());
            locations.get(index).add(new int[] {r1,c1});
            index++;
            excel[r2][c2] = excel[r1][c1];
            locations.get(excel[r1][c1]).add(new int[] {r2, c2});
        } else if (excel[r1][c1] != 0 && excel[r2][c2] != 0 && !(cell.get(excel[r1][c1]).equals("")) ^ cell.get(excel[r2][c2]).equals("")) {
            int delIdx = excel[r2][c2];
            cell.remove(delIdx);
            
            for (int[] loc : locations.get(delIdx)) {
                excel[loc[0]][loc[1]] = excel[r1][c1];
                locations.get(excel[r1][c1]).add(new int[] {loc[0], loc[1]});
            }
            
            locations.remove(delIdx);
            
        } else if (excel[r1][c1] != 0 ^ excel[r2][c2] != 0) {
            if (excel[r1][c1] == 0) {
                int tr = r1;
                int tc = c1;
                r1 = r2;
                c1 = c2;
                r2 = tr;
                c2 = tc;
            }
            excel[r2][c2] = excel[r1][c1];
            locations.get(excel[r1][c1]).add(new int[] {r2, c2});
        } else {
            if (cell.get(excel[r1][c1]).equals("")) {
                int tr = r1;
                int tc = c1;
                r1 = r2;
                c1 = c2;
                r2 = tr;
                c2 = tc;
            }
            
            cell.remove(excel[r2][c2]);
            locations.get(excel[r1][c1]).addAll(locations.get(excel[r2][c2]));
            for (int[] loc : locations.get(excel[r2][c2])) {
                excel[loc[0]][loc[1]] = excel[r1][c1];
            }
        }
    }
    
    public void unmerge(StringTokenizer st) {
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int idx = excel[r][c];
        if (idx == 0 || locations.get(idx).size() == 1) {
            return;
        }
        
        for (int[] loc : locations.get(idx)) {
            excel[loc[0]][loc[1]] = 0;
        }
        
        if (!cell.get(idx).equals("")) {
            excel[r][c] = idx;
            locations.get(idx).clear();
            locations.get(idx).add(new int[] {r,c});
        }
        
    }
    
    public String[] solution(String[] commands) {
        
        for (String com: commands) {
            
            StringTokenizer st = new StringTokenizer(com);
            String inst = st.nextToken();
            
            if (inst.equals("UPDATE")) {
                if (st.countTokens() == 3) {
                    updateLoc(st);
                } else {
                    updateVal(st);
                }
                
            } else if (inst.equals("MERGE")) {
                merge(st);
                
            } else if (inst.equals("UNMERGE")) {
                unmerge(st);
                
            } else { // print
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if (excel[r][c] == 0 || cell.get(excel[r][c]).equals("")) {
                    list.add("EMPTY");
                } else {
                    list.add(cell.get(excel[r][c]));
                }
            }
        }
        
        String[] answer = new String[list.size()];
        int sidx = 0;
        for (String s : list) {
            answer[sidx++] = s;
        }
        return answer;
    }
}