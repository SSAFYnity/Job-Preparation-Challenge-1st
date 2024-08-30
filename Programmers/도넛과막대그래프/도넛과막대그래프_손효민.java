import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, int[]> maps = new HashMap<>(); // 노드 별 간선개수 저장하는 맵.
       
        for(int[] edge: edges){
            int from = edge[0]; // 들어오는 간선
            int to = edge[1]; // 나가는 간선
            
            maps.put(from, maps.getOrDefault(from, new int[]{0, 0}));
            maps.put(to, maps.getOrDefault(to, new int[]{0, 0}));
            
            // 간선 수 증가
            maps.get(from)[0]++;
            maps.get(to)[1]++;
        }
        
         
        int don=0, sti=0, eig=0;
        int add=0;// 추가노드
        
        for(int node : maps.keySet()){
            int[] edge = maps.get(node);
            
            if(edge[0] == 0 && edge[1] > 0){ // 막대 : 가장 마지막 노드 (from 없고 to 있)
                sti++;
            }else if(edge[0] >= 2 && edge[1] >= 2){ // 8자 : from 2개 to 2개
                eig++;
            }else if(edge[0] >= 2 && edge[1] == 0){ // 새로 추가 : from 여러개-2개이상, to 없
                add = node;
            }
        }
        
        // 도넛 : 전체 개수 - 막대,8자
        // 추가한 노드에서 나오는 정점의 개수 = 전체 개수
        don = maps.get(add)[0] - (sti + eig);
        
        int[] answer = {add, don, sti, eig};
        return answer;
    }
}
