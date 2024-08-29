import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (s.length() >= M) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

        List<Entry<String, Integer>> sortedList = new ArrayList<>(map.entrySet());
        sortedList.sort((entry1, entry2) -> {
            int freqCompare = entry2.getValue().compareTo(entry1.getValue()); // 빈도수 비교 (내림차순)
            if (freqCompare != 0) {
                return freqCompare;
            }

            int lengthCompare = Integer.compare(entry2.getKey().length(), entry1.getKey().length()); // 길이 비교 (내림차순)
            if (lengthCompare != 0) {
                return lengthCompare;
            }

            return entry1.getKey().compareTo(entry2.getKey()); // 알파벳 사전순 비교 (오름차순)
        });

        for (Map.Entry<String, Integer> entry : sortedList) {
            sb.append(entry.getKey()).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}