import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {

        TreeMap<Integer,Integer> map = new TreeMap<>();

        for(int i = 0; i < operations.length; i++){
            StringTokenizer st = new StringTokenizer(operations[i]);
            String order = st.nextToken();

            switch(order){
                case "I": {
                    int v = Integer.parseInt(st.nextToken());
                    if(map.get(v) == null){
                        map.put(v, 1);
                    }else {
                        map.put(v, map.get(v)+1);
                    }
                    break;
                }
                case "D": {

                    if(map.isEmpty()) break;

                    int order2 = Integer.parseInt(st.nextToken());
                    int k = 0;
                    if(order2 == 1) {
                        k = map.lastKey();
                    }else {
                        k = map.firstKey();
                    }
                    if(map.get(k) >= 2) {
                        map.put(k, map.get(k)-1);
                    }else if(map.get(k) == 1){
                        map.remove(k);
                    }
                    break;
                }
            }
        }



        if(map.size() == 0) return new int[]{0,0};
        else {
            return new int[]{map.lastKey(), map.firstKey()};
        }

    }
}