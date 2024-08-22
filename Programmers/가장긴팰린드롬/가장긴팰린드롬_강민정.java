class 가장긴팰린드롬_강민정 {
    public static int solution(String s) {
        int n = s.length();     // 문자열의 길이       
        int maxLength = 1;      // 가장 긴 팰린드롬의 길이

        // 팰린드롬 검사 함수
        for (int center = 0; center < 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + (center % 2);

            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {   // 인덱스가 유효하고 두 문자가 같다면
                maxLength = Math.max(maxLength, right - left + 1);      // 가장 긴 팰린드롬의 길이 갱신
                left--;
                right++;
            }
        }

        return maxLength;
    }
}
