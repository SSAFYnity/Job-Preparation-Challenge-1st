import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

class 영단어암기는괴로워_강민정 {
    static class Word {
        int cnt;        // 빈도 수
        String str;     // 단어

        public Word(int cnt, String str) {
            this.cnt = cnt;
            this.str = str;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] firstInput = br.readLine().split(" ");
        int n = Integer.parseInt(firstInput[0]);        // 1 <= 단어의 개수 <= 10,000
        int m = Integer.parseInt(firstInput[1]);        // 1 <= 외울 단어의 기준 <= 10
        Map<String, Integer> hashMap = new HashMap();   // 단어 : 빈도 수
        Queue<Word> pq = new PriorityQueue(new Comparator<Word>(){
            @Override
            public int compare(Word o1, Word o2) {
                // 1. 빈도가 높은 순으로
                int cntCompare = Integer.compare(o2.cnt, o1.cnt);
                if (cntCompare != 0) {      // 두 단어의 빈도 수가 같지 않다면
                    return cntCompare;      // o2 빈도 수 < o1 빈도 수라면 0 반환, o2 빈도 수 > o1 빈도 수라면 1 반환
                }

                // 2. 단어의 길이가 긴 순서로
                int lengthCompare = Integer.compare(o2.str.length(), o1.str.length());
                if (lengthCompare != 0) {   // 두 단어의 길이가 같지 않다면
                    return lengthCompare;   // o2 단어의 길이 < o1 단어의 길이라면 0 반환, o2 단어의 길이 > o1 단어의 길이라면 1 반환
                }

                // 3. 알파벳 사전 순으로
                return o1.str.compareTo(o2.str);
            }
        });

        // n개의 단어 입력 받기
        for(int i=0; i<n; i++) {
            String str = br.readLine();     // 단어
            if(str.length() < m) {      // 외울 단어의 길이보다 짧으면
                continue;
            }
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);     // 단어가 없으면 빈도 수 1, 있으면 기존 빈도 수 + 1을 할당
        }

        // 우선순위 큐에 Word 객체 추가
        for(Map.Entry<String, Integer> item : hashMap.entrySet()) {
            pq.add(new Word(item.getValue(), item.getKey()));
        }

        while(!pq.isEmpty()) {      // 우선순위 큐에 원소가 있을 동안에
            bw.write(pq.poll().str);
            bw.write("\n");
        }

        bw.flush();
    }
}