class Solution
{
    public int solution(String s)
    {   
        int max = 1;
        int length = s.length();
        boolean[][] dp = new boolean[length][length];   // i부터 j까지 문자열이 팰린드롬인지
        
        // 초기화 - 팰린드롬 길이 1인 경우
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        // 초기화 - 팰린드롬 길이 2인 경우
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                max = 2;
            }
        }
        // 팰린드롬 길이 3 이상인 경우
        for (int i = 3; i <= length; i++) {
            for (int st = 0; st <= length - i; st++) {
                int en = st + i - 1;
                // 처음과 끝을 제외한 문자열이 팰린드롬이고, 처음과 끝 문자가 같다면 -> 팰린드롬
                if (dp[st + 1][en - 1] && s.charAt(st) == s.charAt(en)) {
                    dp[st][en] = true;
                    max = i;
                }
            }
        }
        return max;
    }
}