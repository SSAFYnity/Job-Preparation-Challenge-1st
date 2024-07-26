import java.util.*;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[][] tired = {{1,1,1},{5,1,1},{25,5,1}};
        
        int p = 0; // 곡괭이 총 개수
        for(int i=0;i<picks.length;i++) p += picks[i];
        
        // 1. 피로도 계산
        List<Mineral> list = new ArrayList<>();
        for(int i=0;i<minerals.length;i+=5){
            if(p==0) break; // 곡괭이를 다 쓰면 어차피 못함
            
            int a=0, b=0, c=0;
            for(int j=i;j<i+5;j++){
                if(j==minerals.length) break;
                
                if(minerals[j].equals("diamond")) a++;
                else if(minerals[j].equals("iron")) b++;
                else c++;
            }

            list.add(new Mineral(a, b, c, a*25+b*5+c*1)); // 돌 곡괭이라고 생각하고 피로도 계산
            p--;
        }
        
        // 2. 피로도 순으로 내림차순
        Collections.sort(list, new Comparator<Mineral>(){
            @Override
            public int compare(Mineral x, Mineral y){
                return y.total-x.total;
            }
        });
        
        // 3. 광물별 피로도 계산
        for(Mineral m : list){
            int dia = m.dia;
            int iron = m.iron;
            int stone = m.stone;
            
            if(picks[0]>0){
                answer += dia*tired[0][0]+iron*tired[0][1]+stone*tired[0][2];
                picks[0]--;
            }else if(picks[1]>0){
                answer += dia*tired[1][0]+iron*tired[1][1]+stone*tired[1][2];
                picks[1]--;
            }else if(picks[2]>0){
                answer += dia*tired[2][0]+iron*tired[2][1]+stone*tired[2][2];
                picks[2]--;
            }
        }
       
        return answer;
    }
    
    public class Mineral {
        int dia,iron,stone,total;
        
        public Mineral(int dia, int iron, int stone,int total) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
            this.total = total;
        }
    }
}
