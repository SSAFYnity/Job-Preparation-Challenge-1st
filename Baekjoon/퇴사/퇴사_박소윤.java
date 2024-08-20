import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Reservation {
        int period;
        int profit;

        public Reservation(int period, int profit) {
            this.period = period;
            this.profit = profit;
        }
    }

    static int N;
    static Reservation[] reservations;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        reservations = new Reservation[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int period = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());
            reservations[i] = new Reservation(period, profit);
        }

        backtracking(1, 0);

        System.out.println(max);
    }

    static void backtracking(int day, int profit) {

        if (day == N + 1) {
            max = Math.max(max, profit);
            return;
        }

        if (day + reservations[day].period <= N + 1) {  // 해당 일에 상담 진행
            backtracking(day + reservations[day].period, profit + reservations[day].profit);
        }
        backtracking(day + 1, profit);  // 해당 일에 상담 진행 X
    }
}
