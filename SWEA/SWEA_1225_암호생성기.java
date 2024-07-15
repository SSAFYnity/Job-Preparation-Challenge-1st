package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_1225_암호생성기 {

	static int T = 10;
	static Queue<Integer> password = new LinkedList<Integer>();
	static int t;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			t = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				password.offer(Integer.parseInt(st.nextToken()));
			}

			int index = 1;
			int num = 0;
			while (true) {
				num = password.poll() - index++;
				if (num <= 0) {
					num = 0;
					password.offer(num);
					break;
				} else
					password.offer(num);

				if (index > 5)
					index = 1;
			}

			sb.append("#").append(t).append(" ");

			while (!password.isEmpty()) {
				sb.append(password.poll()).append(" ");
			}

			sb.append("\n");

		}

		System.out.println(sb);
	}
}
