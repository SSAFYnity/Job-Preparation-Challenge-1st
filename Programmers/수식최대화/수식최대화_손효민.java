import java.util.*;
class Solution {
    static List<Long> number; // 숫자 리스트
    static List<Character> operator; // 연산자 리스트
    static List<Character> op; // 수식의 연산자 종류 리스트
    
    static boolean[] visit;
    static long answer = 0;
    
    public long solution(String expression) {        
        number = new ArrayList<>(); 
        operator = new ArrayList<>(); 
        op = new ArrayList<>(); 
        
        // 숫자와 연산 문자 분리
        String buf="";
        Set<Character> set = new HashSet<>();
        for(int i=0;i<expression.length();i++){
            char c = expression.charAt(i);
            if(c-'0'>=0 && c-'0'<=9){
                buf += c;
            }else{
                number.add(Long.valueOf(buf));
                buf="";
                operator.add(c);
                set.add(c);
            }
        }
        number.add(Long.valueOf(buf));
        op = new ArrayList<>(set);
        
        // 연산자 우선순위 정하기 -> 결과값 구하기
        visit = new boolean[op.size()];
        func(new ArrayList<Character>());
        
        // 최대값 출력
        return answer;
    }
    
    public void func(List<Character> list){
        // 연산자 우선순위 구함
        if(list.size() == op.size()){
            Long sum = calc(list); // -> 결과값 구하기
            answer = Math.max(sum, answer);
            return;
        }
        
        for(int i=0;i<op.size();i++){
            if(visit[i]) continue;
            
            visit[i] = true;
            list.add(op.get(i)); 
            func(list);
            
            visit[i] = false;
            list.remove(list.size()-1); // 앞에서 추가했던 연산자 제거하기
        }
    }
    
    public Long calc(List<Character> list){
        // 연산자 리스트 중에서 우선순위에 해당하는 연산자들 연산하기
        List<Long> nlist = new ArrayList<>(number);
        List<Character> olist = new ArrayList<>(operator);
        
        for(char opt : list){
            for(int i=0;i<olist.size();i++){
                if(opt == olist.get(i)){
                    // 계산
                    long sum=0;
                    
                    if(opt=='*') sum = nlist.get(i) * nlist.get(i+1);
                    else if(opt=='+') sum = nlist.get(i) + nlist.get(i+1);
                    else sum = nlist.get(i) - nlist.get(i+1);
                    
                    nlist.set(i, sum);
                    
                    // 숫자, 연산자 제거
                    nlist.remove(i+1);
                    olist.remove(i);
                    i--; // 현재 위치부터 다시 계산및 체크할 수 있도록
                }
            }
        }        
        return Math.abs(nlist.get(0));
    }    
}
