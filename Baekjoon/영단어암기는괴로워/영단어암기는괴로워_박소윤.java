import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

    static class Word implements Comparable<Word> {
        String word;
        int frequency;
        int length;

        public Word(String word) {
            this.word = word;
            this.frequency = 1;
            this.length = word.length();
        }

        public void increaseFrequency() {
            this.frequency++;
        }

        @Override
        public int compareTo(Word other) {
            // 우선순위 1) 빈도가 높을 수록 앞
            if (this.frequency == other.frequency) {
                // 우선순위 2) 길이가 길 수록 앞
                if (this.length == other.length) {
                    // 우선순위 3) 사전순
                    return this.word.compareTo(other.word);
                }
                return Integer.compare(this.length, other.length) * -1;
            }
            return Integer.compare(this.frequency, other.frequency) * -1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Word> wordMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) {
                continue;
            }
            if (wordMap.containsKey(word)) {
                wordMap.get(word).increaseFrequency();
            } else {
                wordMap.put(word, new Word(word));
            }
        }

        List<Entry<String, Word>> voca = new ArrayList<>(wordMap.entrySet());
        voca.sort(Entry.comparingByValue());

        StringBuilder sb = new StringBuilder();
        for (Entry<String, Word> word : voca) {
            sb.append(word.getKey()).append("\n");
        }
        System.out.println(sb);
    }
}
