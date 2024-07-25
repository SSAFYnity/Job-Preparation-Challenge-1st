import java.io.*;

class Main {
    static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstInput = br.readLine().split(" ");
        int n = Integer.parseInt(firstInput[0]);        // 10 <= 수열의 길이 < 100,000
        s = Integer.parseInt(firstInput[1]);        // 0 < 합 <= 100,000,000
        String[] secondInput = br.readLine().split(" ");
        int[] arr = new int[n + 1];        // 누적합 배열
        int answer = 0;   // 최소 길이 초기화
        boolean flag = false;
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(secondInput[i]);
            arr[i + 1] = arr[i] + num;    // i+1번째 값까지의 누적합
            if(num >= s) {      // 한 값이 S 이상이라서 길이 1이 정답
                flag = true;
            }
        }

        if(flag) {      // 길이가 1이라면
            answer = 1;
        }else if(arr[n] >= s) {  // 마지막 누적합 값이 합 S 이상이면
            answer = twoPointerAlgo(arr);    // 투포인터 알고리즘 실행
        }

        System.out.print(answer);
    }

    /*
        arr: 누적합 배열
    */
    public static int twoPointerAlgo(int[] arr) {
        int left = 0;
        int right = 1;
        int result = arr.length;        // 최소 길이
        while(right < arr.length) {
            if(arr[right] - arr[left] >= s) {    // 합이 S 이상이면
                result = Math.min(result, right - left);    // 최소 길이 갱신
                left++;    // 범위를 줄이기 위해 왼쪽 포인터의 인덱스를 증가
            } else {
                right++;    // 합이 S보다 작아서 오른쪽 포인터의 인덱스를 증가
            }
        }
        return result;
    }
}
