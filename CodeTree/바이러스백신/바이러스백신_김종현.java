import java.util.*;
import java.io.*;

public class Main {
    private static int N, M, a[][], virus, INF, ans;
    private static int[] dr = {-1,0,1,0};
    private static int[] dc = {0,-1,0,1};
    private static List<int[]> list, curr;
    public static void main(String[] args) throws Exception {

        // 조합 + 완전탐색
        // 병원좌표 배열에 담기
        // M개 조합
        // BFS 를 통한 시간 탐색. => 모든 바이러스 카운트가 0이 될 때까지
        // 모든 BFS 탐색 중 최솟값 찾기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        a = new int[N][N];
        list = new ArrayList<>();
        INF = 2500+4; // MAX 50*50;
        virus = 0;
        for(int i=0; i<N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                a[i][j] = Integer.parseInt(stk.nextToken());
                if(a[i][j]==0) virus++;
                else if(a[i][j]==2) list.add(new int[] {i,j});
            }
        }

        if(virus == 0) {
            System.out.println(0);
            return;
        }

        curr = new ArrayList<>();
        ans = INF;
        comb(0);

        System.out.println(ans == INF ? -1 : ans);
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int visited[][] = new int[N][N];
        for(int[] pos : curr) {
            q.add(new int[] {pos[0], pos[1]});
            visited[pos[0]][pos[1]] = 1;
        }

        int t = 0;
        int cnt = virus;
        while(!q.isEmpty()) {
            int[] pos = q.poll();

            for(int d=0; d<4; d++) {
                int nr = pos[0]+dr[d];
                int nc = pos[1]+dc[d];

                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                if(visited[nr][nc] > 0 || a[nr][nc] == 1) continue;
                
                visited[nr][nc] = visited[pos[0]][pos[1]]+1;
                q.add(new int[] {nr, nc});
                
                if(a[nr][nc] == 2) continue;
                
                if(--cnt == 0) {
                    return visited[nr][nc]-1;
                }
            }
        }
        
        // 바이러스 처리 다 못함.
        return INF;
    }

    private static void comb(int idx) {
        if(curr.size() >= M) {
            // bfs 진행
            ans = Math.min(ans, bfs());
            return;
        }


        for(int i=idx; i<list.size(); i++) {
            curr.add(list.get(i));
            comb(i+1);
            curr.remove(curr.size()-1);
        }
    }
}