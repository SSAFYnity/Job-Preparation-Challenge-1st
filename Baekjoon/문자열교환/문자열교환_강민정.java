import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int answer = 1000;      // 교환 횟수의 최솟값 초기화
        Scanner sc = new Scanner(System.in);
        String str = sc.next();     // 문자열 입력 받기
        int aCnt = 0;       // a의 갯수

        // a의 갯수 세기
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == 'a') {      // a를 찾으면
                aCnt++;
            }
        }

        for(int i=0; i<str.length(); i++) {     // 시작점
            int bCnt = 0;           // b의 갯수
            for(int j=i; j<i+aCnt; j++) {       // 시작 인덱스부터 a의 갯수만큼의 범위 안에서의 b의 갯수가 교환 횟수다.
                if(str.charAt(j % str.length()) == 'b') {      // b를 찾으면
                    bCnt++;         // b의 갯수 증가
                }
            }
            answer = Math.min(answer, bCnt);
        }

        System.out.println(answer);     // 교환 횟수의 최솟값을 출력
    }
}
