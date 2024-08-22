import java.util.Scanner;

public class Main {
	static int[] arr1;
	static int[] arr2;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		arr1 = new int[N + 1]; // 걸리는 시간
		arr2 = new int[N + 1]; // 버는 비용

		for (int i = 1; i <= N; i++) {
			arr1[i] = sc.nextInt();
			arr2[i] = sc.nextInt();
		}

		int max = 0;
		solve(N, 1, max);
		System.out.println(ans);
	}

	public static void solve(int N, int toDay, int max) {
		if (toDay >= N + 1) {
			ans = Math.max(ans, max);
			return;
		}

		if (arr1[toDay] + toDay <= N + 1) {
			solve(N, toDay + arr1[toDay], max + arr2[toDay]);
		}
		solve(N, toDay + 1, max);
	}
}
