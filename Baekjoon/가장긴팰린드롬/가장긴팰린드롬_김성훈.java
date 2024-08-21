class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        char[] str = s.toCharArray();
        int n = str.length;

        for (int i = n; i > 0; i--) {

            for (int j = 0; j <= n - i; j++) {

                int left = j, right = j + i - 1;

                boolean flag = true;

                while (left < right) {

                    if (str[left] != str[right]) {
                        flag = false;
                        break;
                    }

                    left++;
                    right--;
                }

                if (flag) return i;
            }

        }
        return 1;
    }
}