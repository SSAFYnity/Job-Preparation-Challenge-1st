class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        
        for (int i = s.length()-1; i >= 1; i--) {
            int start = 0;
            while (start + i < s.length()) {
                int end = start + i;
                for (int j = 0; j < (i+1) / 2; j++) {
                    if (s.charAt(start+j) != s.charAt(end-j)) {
                        start++;
                        break;
                    } else if (j == (i+1)/2-1) {
                        return i+1;
                    }
                }
            }
        }

        return answer;
    }
}