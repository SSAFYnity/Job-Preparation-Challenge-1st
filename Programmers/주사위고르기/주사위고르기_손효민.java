import java.util.*;
class Solution {
    static int[][] D;
    static int N, maxWin, maxSum;
    static int[] answer, A, B;
    static List<Integer> sumList;
    
    public int[] solution(int[][] dice) {
        N = dice.length; // 주사위 개수
        D = dice;
        
        maxWin = 0;
        answer = new int[N/2];
        A = new int[N/2]; // A가 가져가는 주사위 조합
        B = new int[N/2]; // B가 가져가는 주사위 조합
        
        select(0,0);
        return answer;
    }
    
    public void select(int x, int idx){
        if(idx == N/2){ // A 주사위 선택이 모두 끝났으면
            
            int index = 0;
            for(int i=0;i<N;i++){ // B 주사위 선택
                boolean flag = false;
                for (int j = 0; j < N/2; j++){
                    if (A[j] == i) flag = true;
                }
                if (flag) continue;
                B[index++] = i;
            }
            
            calc(); // 승리 확률 구하기
            return;
        }
        
        if(x >= N) return; // 범위 넘어가면
        A[idx] = x; 
        
        // 다음 조합
        select(x+1, idx+1);
        select(x+1, idx);
    }
    
    public void calc(){
        sumList = new ArrayList<>();
        
        maxSum=0;
        
        // B의 모든 합의 경우의 수를 리스트로 저장하고 정렬
        calcB(0,0);       
        Collections.sort(sumList);
        
        // A의 모든 합의 경우의 수를 계산하고 이진탐색.
        calcA(0,0);
        
        // answer 갱신
        if(maxWin < maxSum){
            maxWin = maxSum;
            for(int i=0;i<N/2;i++){
                answer[i] = A[i]+1;
            }
        }
    }
    
    public void calcB(int idx, int sum){
        // B 가 선택한 주사위 별로 하나씩 구성의 합의 경우의 수
        if(idx == N/2){
            sumList.add(sum);
            return;
        }
        
        for(int i=0;i<6;i++){
            calcB(idx+1, sum+D[B[idx]][i]);
        }
    }
    
    public void calcA(int idx, int sum){
        if(idx == N/2){
            int left=0, right = sumList.size();
            
            while(left + 1 < right){
                int mid = (left+right)/2;
                
                if(sumList.get(mid) < sum) left = mid;
                else right = mid;
            }
            
            // maxSum += (left+1);
            maxSum += right;
            return;
        }
        
        for(int i=0;i<6;i++){
            calcA(idx+1, sum+D[A[idx]][i]);
        }
    }
}
