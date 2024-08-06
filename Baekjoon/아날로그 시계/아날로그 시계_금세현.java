class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        int start=h1*3600+m1*60+s1;
        int end=h2*3600+m2*60+s2;

        answer = end*59/3600 - start*59/3600 + end*719/43200-start*719/43200;

        if(start>=43200){
            answer += 1;
        }

        if(end>=43200){
            answer -= 1;
        }
        if(start * 59 % 3600 == 0 || start * 719 % 43200 == 0){
            answer += 1;
        }
        return answer;
    }
}