import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

class 성냥개비_강민정 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long[] minDp = new long[101];     // minDp[성냥개비 갯수] = 만들 수 있는 가장 작은 수
        String[] maxDp = new String[101];
        String[] minStr = new String[]{"1", "7", "4", "2", "0", "8"};       // minStr[성냥개비갯수 - 2] = 만들 수 있는 가장 작은 수
        String[] maxStr = new String[]{"0", "0", "1", "7", "4", "5", "9", "8"};     // max[성냥개비갯수] = 만들 수 있는 가장 큰 수

        // minDp 초기화
        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;       // 0이 가장 작지만, 맨 앞에 올 수 없으니까
        minDp[7] = 8;
        minDp[8] = 10;      // minDp[8 - 8] = 1일때 성냥개비 하나로 아무것도 못 만들어서 초기값이 Integer.MAX_VALUE이면 에러가 터져서 8까지 초기화해줌

        // maxDp 초괴화
        maxDp[2] = "1";       // 성냥개비 2개로 만들 수 있는 가장 큰 수는 1

        for(int num=9; num<=100; num++) {       // 만들어야 하는 숫자 num
            for(int i=2; i<8; i++) {        // 0~9까지 숫자를 만드는 데 사용하는 성냥개비의 갯수 범위
                minDp[num] = Math.min(minDp[num], Long.parseLong(minDp[num - i] + minStr[i - 2]));      // 두 자릿수 이상이 된다.
            }
        }

        for(int num=3; num<=100; num++) {       // 만들어야 하는 숫자 num
            String strNum = "";
            if(num % 2 == 0) {      // 짝수라면
                for(int i=0; i<num/2; i++) {        // 1을 만드려면 성냥개비가 2개 필요해서 num/2만큼 반복
                    strNum += "1";      // 1로 모두 채워서 자릿수를 많게 하는 게 최댓값이 됨
                }
            } else {        // 홀수라면
                int val = num / 2 - 1;
                for(int i=0; i<val; i++) {      // 절반 정도는 1로 모두 채워서 자릿수를 늘린다.
                    strNum += "1";
                }
                strNum = maxStr[num - val * 2] + strNum;        // 뒷 자리는 1로 모두 채우고 남은 성냥개비로 앞에 숫자를 만든다.
            }
            maxDp[num] = strNum;
        }

        int tc = sc.nextInt();      // 테스트케이스 수
        for(int t=0; t<tc; t++) {
            int cnt = sc.nextInt();     // 성냥개비 갯수
            bw.write(minDp[cnt] + " " + maxDp[cnt] + "\n");     // 성냥개비 갯수 cnt를 모두 써서 만든 가장 작은 수와 가장 큰 수를 출력
        }

        bw.flush();     // 테스트케이스 별로 정답 출력
    }
}
