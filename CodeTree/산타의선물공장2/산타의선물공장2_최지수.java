import java.io.*;
import java.util.*;

public class Main {

    public static class Gift {
        int num, front, back;
        public Gift(int num, int front, int back) {
            this.num = num;
            this.front = front;
            this.back = back;
        }
        public String toString() {
            return "Gift_" + num + " front: " + front + " back: " + back;
        }
    }

    public static class Belt {
        int num, head, tail, cnt;
        public Belt(int num, int head, int tail, int cnt) {
            this.num = num;
            this.head = head;
            this.tail = tail;
            this.cnt = cnt;
        }
        public String toString() {
            return "Belt_" + num + " head: " + head + " tail: " + tail + " cnt: " + cnt;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Belt[] belts;
    static Gift[] gifts;

    public static void factoryEstablish(int n, int m) {
        belts = new Belt[n+1];
        for (int i = 0; i <= n; i++) {
            belts[i] = new Belt(i, -1, -1, 0);
        }

        gifts = new Gift[m+1];
        for (int i = 1; i <= m; i++) {
            int beltNum = Integer.parseInt(st.nextToken());

            if (belts[beltNum].cnt == 0) {
                belts[beltNum].head = i;
                gifts[i] = new Gift(i, -1, -1);
            } else {
                int front = belts[beltNum].tail;
                gifts[i] = new Gift(i, front, -1);
                gifts[front].back = i;
            }
            belts[beltNum].tail = i;
            belts[beltNum].cnt++;
        }
    }

    public static int moveAll(int src, int dst) {
        if (belts[src].cnt == 0) return belts[dst].cnt;

        int originSrcHead = belts[src].head;
        int originSrcTail = belts[src].tail;
        int originDstHead = belts[dst].head;

        belts[dst].head = originSrcHead;
        gifts[originSrcTail].back = originDstHead;

        if (belts[dst].cnt != 0) {
            gifts[originDstHead].front = originSrcTail;
        } else belts[dst].tail = originSrcTail;

        belts[dst].cnt += belts[src].cnt;
        belts[src].head = -1;
        belts[src].tail = -1;
        belts[src].cnt = 0;

        return belts[dst].cnt;
    }

    public static int changeHead(int src, int dst) {
        int originSrcHead = belts[src].head;
        int originDstHead = belts[dst].head;

        belts[src].head = originDstHead;
        belts[dst].head = originSrcHead;

        if (belts[src].cnt == 0 && belts[dst].cnt == 0) {}
        else if (belts[src].cnt == 0) {
            belts[src].tail = originDstHead;
            belts[src].cnt++;

            belts[dst].head = gifts[originDstHead].back;
            belts[dst].cnt--;

            if (gifts[originDstHead].back != -1) gifts[gifts[originDstHead].back].front = -1;
            gifts[originDstHead].back = -1;

        } else if (belts[dst].cnt == 0) {
            belts[dst].tail = originSrcHead;
            belts[dst].cnt++;

            belts[src].head = gifts[originSrcHead].back;
            belts[src].cnt--;

            if (gifts[originSrcHead].back != -1) gifts[gifts[originSrcHead].back].front = -1;
            gifts[originSrcHead].back = -1;

        } else {
            int originSrc2nd = gifts[originSrcHead].back;
            int originDst2nd = gifts[originDstHead].back;
            gifts[originSrcHead].back = originDst2nd;
            gifts[originDstHead].back = originSrc2nd;

            if (originSrc2nd != -1) gifts[originSrc2nd].front = originDstHead;
            if (originDst2nd != -1) gifts[originDst2nd].front = originSrcHead;
        }

        if (belts[src].cnt <= 1) belts[src].tail = belts[src].head;
        if (belts[dst].cnt <= 1) belts[dst].tail = belts[dst].head;

        return belts[dst].cnt;
    }

    public static int divide(int src, int dst) {
        if (belts[src].cnt <= 1) return belts[dst].cnt;

        int n = belts[src].cnt/2;

        int idx = belts[src].head;
        int originSrcHead = belts[src].head; // 3
        int originDstHead = belts[dst].head; // 2

        for (int i = 1; i < n; i++) {
            idx = gifts[idx].back;
        }

        int moveHead = originSrcHead; // 3
        int moveTail = idx; // 3
        int newSrcHead = gifts[moveTail].back; // 4

        belts[src].head = newSrcHead; // 4
        belts[dst].head = moveHead; // 3
        gifts[newSrcHead].front = -1;

        if (belts[dst].cnt == 0) {
            belts[dst].tail = moveTail;
            gifts[moveTail].back = -1;
        } else {
            gifts[originDstHead].front = moveTail;
            gifts[moveTail].back = originDstHead;
        }

        belts[dst].cnt += n;
        belts[src].cnt -= n;

        return belts[dst].cnt;
    }

    public static int getGiftInfo(int num) {
        return gifts[num].front + gifts[num].back * 2;
    }

    public static int getBeltInfo(int num) {
        return belts[num].head + belts[num].tail * 2 + belts[num].cnt * 3;
    }

    public static String lineToString(int num) {
        StringBuilder sb = new StringBuilder();
        sb.append(num + "번 벨트 ");
        int idx = belts[num].head;
        while (idx != -1) {
            sb.append(idx + " ");
            idx = gifts[idx].back;
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());

        for (int t = 0; t < q; t++) {
            st = new StringTokenizer(br.readLine());
            int inst = Integer.parseInt(st.nextToken());
            int answer = Integer.MIN_VALUE;
            try {
                switch (inst) {
                    case 100: {
                        factoryEstablish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    }
                    case 200: {
                        answer = moveAll(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    }
                    case 300: {
                        answer = changeHead(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    }
                    case 400: {
                        answer = divide(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    }
                    case 500: {
                        answer = getGiftInfo(Integer.parseInt(st.nextToken()));
                        break;
                    }
                    default: answer = getBeltInfo(Integer.parseInt(st.nextToken()));
                }
                if (answer != Integer.MIN_VALUE) System.out.println(answer);
            } catch (Exception e) {
                System.out.println(t + " " + e);
                break;
            }
        }
    }

}