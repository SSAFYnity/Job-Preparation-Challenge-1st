import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static List<Integer> tree[];
	
	static int[][] parent; // 부모 노드 ->각 노드의 2^i번째 조상노드를 저장
	static int[] depth; // 노드별 depth
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 노드의 개수
		
		tree = new ArrayList[N+1];
		for(int i=0;i<N+1;i++) {
			tree[i] = new ArrayList<>();
		}
		
		StringTokenizer st = null;
		for(int i=0;i<N-1;i++) { // 트리 연결
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
		
		int tmp=1;
		K=0; // 최대 depth, K는 N이 될 때까지 몇 번 2를 곱했는지 = 트리의 깊이에 대한 이진 로그 값
		while(tmp <= N) {
			tmp *= 2;
			K++;
		}
		
		parent = new int[N+1][K];
		depth = new int[N+1];
		
		dfs(1, 1); //dfs : 각 노드별 depth를 구해 배열에 저장, 각 노드의 첫번째 조상 노드를 parent 배열에 담기
		dp(); // dp로 각 노드의 모든 2^i번째 조상노드를 구해 parent 배열에 저장 
		
		M = Integer.parseInt(br.readLine()); // 가장 가까운 조상을 알고싶은 쌍의 개수
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++) { // 트리 연결
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int lca = lca(a,b); //a와 b 깊이 맞추기. 2^i번째 조상노드를 구하면서 최소 공통 조상 찾기
			sb.append(lca+"\n");
		}
		
		System.out.println(sb.toString());
	}

	private static int lca(int a, int b) {
		if(depth[a] < depth[b]) { // 깊이가 낮은 쪽을 기준으로 맞춘다. a가 더 깊고 b가 더 얕은 깊이 노드가 되도록
			int temp = a;
			a = b;
			b = temp;
		}
		
		//더 깊은 a를 2승씩 점프하며 두 노드의 depth를 맞춘 후, 맞춘 depth의 조상 노드로 대체
		for(int i=K-1;i>=0;i--) {
			if(Math.pow(2, i) <= depth[a]-depth[b]) {
				a = parent[a][i]; // a를 2^i 번 째 조상 노드로 대체
			}
		}
		
		// depth 맞춘 후 대체한 조상 노드가 b와 같으면 = a 조상노드가 b면 종료
		if(a == b) return a;
		
		// 두 노드가 같은 depth
		// 공통 부모 바로 아래까지 찾기
		for(int i=K-1;i>=0;i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		return parent[a][0];
	}

	private static void dp() {
		for(int i=1;i<K;i++) { // 각 노드별 2^K 번째 조상 노드 저장
			for(int j=1;j<=N;j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
	}

	private static void dfs(int node, int idx) {
		depth[node] = idx; //현재 노드의 깊이
		
		for(Integer next : tree[node]) {
			if(depth[next] == 0) { // 다음노드의 깊이가 0, 방문안했다면
				dfs(next, idx+1); // 다음 노드 탐색
				parent[next][0] = node; // 다음 노드의 부모 노드를 현재 노드로 설정
			}
		}		
	}
}
