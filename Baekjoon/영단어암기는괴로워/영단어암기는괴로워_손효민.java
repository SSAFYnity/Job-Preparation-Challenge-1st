import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>(); //key가 string, value가 integer
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			if(s.length() < M) continue;
			
			map.put(s, map.getOrDefault(s, 0) + 1); 
		}
		
		List<String> words = new ArrayList<>(map.keySet());
		Collections.sort(words, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(map.get(o1) == map.get(o2)) {
					if(o1.length() == o2.length()) {
						return o1.compareTo(o2);
					}
					return o2.length()-o1.length();
				}
				return map.get(o2)-map.get(o1);
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		for(String str : words) {
			sb.append(str+"\n");
		}
		System.out.println(sb.toString());
	}
}
