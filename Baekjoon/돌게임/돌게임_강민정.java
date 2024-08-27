import java.util.*;

class 돌게임_강민정 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();    // 1 <= 돌의 갯수 <= 1,000
        String answer = "SK";    // 초기값(상근이가 게임을 이김)

        if(n % 2 == 0) {        // 돌의 갯수가 짝수라면
            answer = "CY";      // 창영이가 게임을 이김
        }

        System.out.println(answer);     // 이긴 사람 출력
    }
}