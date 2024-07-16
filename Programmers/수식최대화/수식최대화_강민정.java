import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    /*
        expression = 연산 수식이 담긴 문자열
    */
    public long solution(String expression) {
        long answer = 0;        // 우승 시 받을 수 있는 가장 큰 상금 금액
        String[] numArr = expression.split("[^0-9]+");       // 숫자 추출
        String[] operationArr = expression.split("[0-9]+");     // 연산자 추출
        String[][] priorityOperations = {{"+", "-", "*"}, {"+", "*", "-"}, {"-", "+", "*"},
                                         {"-", "*", "+"}, {"*", "+", "-"}, {"*", "-", "+"}};        // 최대 6가지라서 직접 생성함
        
        for(String[] operations : priorityOperations) {     // 연산자 조합 하나를 뽑음
            List<String> numLst = new ArrayList();          // 정수 문자열을 담는 리스트
            numLst.addAll(Arrays.asList(numArr));
            List<String> operationLst = new ArrayList(Arrays.asList(operationArr));     // 연산자를 담는 리스트
            operationLst.remove(0);     // ""를 제거      
            for(String operation : operations) {        // 연산자 하나를 뽑음
                for(int i=0; i<operationLst.size(); i++) {     // 모든 연산을 할때까지 반복
                    if(operationLst.get(i).equals(operation)) {         // 찾는 연산자가 맞다면
                        long a = Long.parseLong(numLst.get(i));         // 왼쪽 피연산자
                        long b = Long.parseLong(numLst.get(i + 1));     // 오른쪽 피연산자
                        
                        String result = String.valueOf(calculate(a, b, operationLst.get(i)));       // 계산 메서드 호출
                        
                        numLst.set(i, result);        // 연산 결과를 넣기
                        numLst.remove(i + 1);         // 계산을 했으니 값을 제거
                        operationLst.remove(i);       // 연산이 끝난 연산자는 제거
                        i--;                          // 인덱스 초과를 막기 위한 작업
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(numLst.get(0))));     // 절댓값으로 변환 후 최댓값 갱신
        }
        
        return answer;      // 우승 시 받을 수 있는 가장 큰 상금 금액
    }
    
    /*
        a: 왼쪽 피연산자
        b: 오른쪽 피연산자
        operation: 연산자
    */
    public long calculate(long a, long b, String operation) {
        switch(operation) {     // 연산자의 종류에 따라 연산을 실행
            case "+":
                return a + b;
            case "-":
                return a - b;
            default:
                return a * b;
        }  
    }
}