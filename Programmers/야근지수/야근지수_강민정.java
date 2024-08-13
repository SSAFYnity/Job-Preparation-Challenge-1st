import java.util.PriorityQueue;
import java.util.Collections;

public class 야근지수_강민정 {
    public static long solution(int n, int[] works) {
        // 작업량 합이 남은 시간보다 적거나 같으면 야근 피로도는 0
        int totalWork = 0;
        for (int work : works) { // 각 작업량을 더해서 총 작업량 계산
            totalWork += work;
        }
        if (totalWork <= n) { // 총 작업량이 남은 시간보다 적거나 같으면
            return 0; // 야근 피로도는 0
        }

        // 최대 힙 생성
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) { // 작업량을 최대 힙에 추가
            maxHeap.add(work);
        }

        // 남은 시간 동안 작업 감소
        while (n > 0) { // 남은 시간이 0이 될 때까지
            int maxWork = maxHeap.poll(); // 최대 작업량을 꺼냄
            maxHeap.add(maxWork - 1); // 최대 작업량을 1 줄여서 다시 힙에 추가
            n--; // 남은 시간 감소
        }

        // 야근 피로도 계산
        long fatigue = 0;
        while (!maxHeap.isEmpty()) { // 힙이 빌 때까지
            int work = maxHeap.poll(); // 작업량을 꺼내서
            fatigue += (long) work * work; // 작업량의 제곱을 피로도에 더함
        }

        return fatigue; // 최종 야근 피로도 반환
    }
}
