import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// NxN 도시에서 M개의 병원을 선택하여 모든 바이러스 제거
	// 선택된 병원을 기준으로, 매 초마다 상하좌우 백신 공급(벽 제외)
	// 모든 바이러스를 제거하는 데 걸리는 최소 시간 구하기

	// 전체 병원의 수와 M의 최대 개수는 10이다.
	// 조합(최대 10 C 5) == 252case -> BFS

	private static int N, M, virusCnt, minTime;
	private static int[] selectedNum;
	private static int[][] board, visit;
	private static boolean[] isSelected;
	private static List<Hospital> hospitals;
	private static final int[] dRow = {-1, 1, 0, 0}, dCol = {0, 0, -1, 1};
	private static Queue<int[]> q;

	private static void init() {
		minTime = Integer.MAX_VALUE;
		selectedNum = new int[M];
		isSelected = new boolean[hospitals.size()];
		q = new ArrayDeque<>();
	}

	private static void combination(int depth, int start) {
		// M개의 병원을 모두 골랐다면, BFS 진행
		if (depth == M) {
			bfs();
			return;
		}

		for (int i = start; i < hospitals.size(); i++) {
			if (isSelected[i]) {
				continue;
			}
			selectedNum[depth] = i;
			isSelected[i] = true;
			combination(depth + 1, i + 1);
			isSelected[i] = false;
		}
	}

	private static void bfs() {
		q.clear();

		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < M; i++) {
			q.offer(new int[] {hospitals.get(selectedNum[i]).r, hospitals.get(selectedNum[i]).c, 0});
			visit[hospitals.get(selectedNum[i]).r][hospitals.get(selectedNum[i]).c] = 0;
		}

		int removeCnt = 0;
		while (!q.isEmpty()) {
			// 시간 확인
			if (removeCnt == virusCnt) {
				minTime = Math.min(minTime, getClearTime());
				break;
			}
			int[] cur = q.poll();
			// 가지치기
			if (board[cur[0]][cur[1]] == 0 && minTime <= cur[2]) {
				break;
			}
			for (int k = 0; k < 4; k++) {
				int r = cur[0] + dRow[k];
				int c = cur[1] + dCol[k];
				if (r < 0 || c < 0 || r >= N || c >= N) { // board 범위 확인
					continue;
				}
				if (board[r][c] == 1) { // 벽 확인
					continue;
				}
				if (visit[r][c] <= cur[2] + 1) { // 최단 시간 비교
					continue;
				}
				q.offer(new int[] {r, c, cur[2] + 1});
				visit[r][c] = cur[2] + 1;
				if (board[r][c] == 0) {
					removeCnt++;
				}
			}
		}
	}

	private static int getClearTime() {
		int time = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 0) {
					time = Math.max(time, visit[i][j]);
				}
			}
		}
		return time;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		visit = new int[N][N];
		hospitals = new ArrayList<>();
		virusCnt = 0;

		// board 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				// 병원 좌표 추가
				if (board[i][j] == 2) {
					hospitals.add(new Hospital(i, j));
				}
				// 바이러스 좌표 추가
				if (board[i][j] == 0) {
					virusCnt++;
				}
			}
		}

		if (virusCnt == 0) {
			bw.write(0 + "\n");
		} else {
			init();

			// 병원 고르기 + BFS 진행
			combination(0, 0);

			minTime = (minTime == Integer.MAX_VALUE) ? -1 : minTime;
			bw.write(minTime + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static class Hospital {
		int r;
		int c;

		public Hospital(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
