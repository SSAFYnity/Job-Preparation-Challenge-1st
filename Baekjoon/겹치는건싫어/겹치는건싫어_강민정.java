import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 겹치는건싫어_강민정 {
    public static void main(String[] args) throws IOException {
        // 입력을 받기 위해 BufferedReader와 StringTokenizer 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫째 줄에서 N(배열의 길이)과 K(각 숫자의 최대 빈도) 입력 받기
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 둘째 줄에서 배열의 요소들 입력 받기
        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자의 빈도를 저장할 배열 초기화
        int[] count = new int[100001];

        // 초기 포인터 설정: start와 end 모두 0으로 시작
        int start = 0, end = 0;
        
        // 최장 연속 부분 수열의 길이를 저장할 변수 초기화
        int answer = 0;

        // 슬라이딩 윈도우를 사용하여 배열 탐색
        while (end < N) {
            // K개 이하일 때까지 end 포인터 증가
            while (end < N && count[numbers[end]] < K) {
                count[numbers[end]]++; // 현재 숫자의 빈도 증가
                end++; // end 포인터를 오른쪽으로 이동
            }

            // 현재 구간의 길이를 계산하여 최장 길이 갱신
            answer = Math.max(answer, end - start);

            // start 포인터를 이동시키며 카운트 감소
            count[numbers[start]]--; // 현재 start 포인터가 가리키는 숫자의 빈도 감소
            start++; // start 포인터를 오른쪽으로 이동
        }

        // 최장 연속 부분 수열의 길이 출력
        System.out.print(answer);
    }
}
