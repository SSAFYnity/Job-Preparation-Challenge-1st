import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            String str = br.readLine();

            if(str.length() < m) continue;

            if(map.containsKey(str)){
                map.put(str, map.get(str)+1);
            }else{
                map.put(str, 1);
            }
        }

        List<String> list = new ArrayList<>(map.keySet());      // 단어장 단어들
        list.sort((o1, o2) -> {
            int pre = map.get(o1);
            int next = map.get(o2);

            if(pre == next){    // 빈도수가 같은 경우
                if(o1.length() == o2.length()){     // 단어 길이가 같은 경우
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
            return next - pre;
        });

        for(String word : list){
            sb.append(word + "\n");
        }
        
        System.out.print(sb);
    }
}
