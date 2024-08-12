import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lenS = Integer.parseInt(br.readLine());  // 3 <= 문자열의 길이 <= 200,000
        String s = br.readLine();  // 알파벳 소문자로만 이루어진 길이 len_s의 문자열
        String answer = "Yes";

        int[] cnt = new int[26];  // 각 알파벳의 빈도수

        // 각 문자의 빈도수를 셈
        for (char ch : s.toCharArray()) {       // char 배열로 변환 후 반복문으로 탐색
            cnt[ch - 'a']++;
        }

        // 문자열의 길이가 홀수인 경우, 중간 문자의 빈도를 조정
        if (lenS % 2 == 1) {
            cnt[s.charAt(lenS / 2) - 'a']--;
        }

        // 홀수 빈도를 가진 문자가 있는지 확인
        for (int count : cnt) {
            if (count % 2 == 1) {
                answer = "No";
                break;
            }
        }
        System.out.println(answer);
    }
}
