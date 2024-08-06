import java.io.*;
import java.util.*;

public class Main {

    static class Box {
        Box prev, next;
        int idx;
    }

    static class Belt {
        Box head, tail;
        int cnt = 0;
    }

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int q, n, m;

    static Box[] boxes;
    static Belt[] belts;

    public static void init() { // 100
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        belts = new Belt[n + 1];
        for (int i = 1; i <= n; i++) belts[i] = new Belt();

        boxes = new Box[m + 1];
        for (int i = 1; i <= m; i++) boxes[i] = new Box();

        ArrayList<Integer>[] beltBoxes = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) beltBoxes[i] = new ArrayList<Integer>();

        for (int i = 1; i <= m; i++) {
            int location = Integer.parseInt(st.nextToken());
            beltBoxes[location].add(i);
        }

        for (int i = 1; i <= n; i++) {
            if (beltBoxes[i].size() == 0) continue;

            int size = beltBoxes[i].size();
            belts[i].cnt = size;
            int boxIdx = beltBoxes[i].get(0);

            Box prev = boxes[boxIdx];
            prev.idx = boxIdx;

            Box next = boxes[boxIdx];

            belts[i].head = prev;

            for (int s = 1; s < size; s++) {
                boxIdx = beltBoxes[i].get(s);
                next = boxes[boxIdx];
                next.idx = boxIdx;

                prev.next = next;
                next.prev = prev;
                prev = next;
            }

            belts[i].tail = next;
        }
    }

    public static int moveAll(int m_src, int m_dst) { // 200

        int srcCnt = belts[m_src].cnt;
        if (srcCnt == 0) return belts[m_dst].cnt; // 만약 출발 벨트가 비어있다면

        Belt srcBelt = belts[m_src];
        Belt dstBelt = belts[m_dst];

        Box prevHead = dstBelt.head;

        dstBelt.head = srcBelt.head;
        if (prevHead == null) { // 만약 도착 벨트가 비어있는 상태였다면
            dstBelt.tail = srcBelt.tail;
        } else {
            srcBelt.tail.next = prevHead;
            prevHead.prev = srcBelt.tail;
        }

        srcBelt.head = null;
        srcBelt.tail = null;

        dstBelt.cnt += srcBelt.cnt;
        srcBelt.cnt = 0;

        return dstBelt.cnt;
    }

    public static int moveFront(int m_src, int m_dst) { // 300

        Belt srcBelt = belts[m_src];
        Belt dstBelt = belts[m_dst];

        if (srcBelt.cnt == 0 && dstBelt.cnt == 0) return 0; // 두 벨트가 모두 비어있다면

        if (srcBelt.cnt == 0) { // src 벨트만 비어있다면
            Box dstBox = dstBelt.head;
            if (dstBelt.cnt == 1) { //dst 벨트에 박스가 1개만 존재하는 경우
                dstBelt.head = null;
                dstBelt.tail = null;
            } else { // dst 벨트에 박스가 2개이상 존재하는 경우
                Box dstNext = dstBox.next;
                dstBelt.head = dstNext;
                dstNext.prev = null;
            }
            srcBelt.head = dstBox;
            srcBelt.tail = dstBox;
            dstBox.next = null;

            srcBelt.cnt++;
            dstBelt.cnt--;
        } else if (dstBelt.cnt == 0) { // dst 벨트만 비어있다면
            Box srcBox = srcBelt.head;
            if (srcBelt.cnt == 1) { // src 벨트에 박스가 1개만 존재하는 경우
                srcBelt.head = null;
                srcBelt.tail = null;
            } else { // src 벨트에 박스가 2개이상 존재하는 경우
                Box srcNext = srcBox.next;
                srcBelt.head = srcNext;
                srcNext.prev = null;
            }
            dstBelt.head = srcBox;
            dstBelt.tail = srcBox;
            srcBox.next = null;
            dstBelt.cnt++;
            srcBelt.cnt--;
        } else {
            Box srcBox = srcBelt.head;
            Box srcNext = srcBox.next;

            Box dstBox = dstBelt.head;
            Box dstNext = dstBox.next;

            if (srcNext == null && dstNext == null) { // 둘 다 박스가 1개만 존재하는 경우
                srcBelt.head = dstBox;
                srcBelt.tail = dstBox;
                dstBelt.head = srcBox;
                dstBelt.tail = srcBox;
            } else if (srcNext == null) { // src만 박스가 1개 존재하는 경우
                srcBelt.head = dstBox;
                srcBelt.tail = dstBox;
                dstBox.next = null;

                dstBelt.head = srcBox;
                srcBox.next = dstNext;
                dstNext.prev = srcBox;
            } else if (dstNext == null) { // dst만 박스가 1개 존재하는 경우
                dstBelt.head = srcBox;
                dstBelt.tail = srcBox;
                srcBox.next = null;

                srcBelt.head = dstBox;
                dstBox.next = srcNext;
                srcNext.prev = dstBox;
            } else {
                srcBelt.head = dstBox;
                dstBelt.head = srcBox;

                dstBox.next = srcNext;
                srcNext.prev = dstBox;

                srcBox.next = dstNext;
                dstNext.prev = srcBox;
            }
        }

        // 진행 이후 m_dst의 선물의 총 수를 출력합니다.
        return dstBelt.cnt;
    }

    public static int moveHalf(int m_src, int m_dst) { // 400

        Belt srcBelt = belts[m_src];
        Belt dstBelt = belts[m_dst];

        if (srcBelt.cnt == 0 || srcBelt.cnt == 1) return dstBelt.cnt;
        int floor = srcBelt.cnt / 2;

        if (dstBelt.cnt == 0) {
            Box head = srcBelt.head;
            Box half = srcBelt.head;

            for (int i = 1; i < floor; i++) {
                half = half.next;
            }

            Box nextHead = half.next;
            srcBelt.head = nextHead;
            nextHead.prev = null;

            dstBelt.head = head;
            dstBelt.tail = half;
            half.next = null;

            srcBelt.cnt -= floor;
            dstBelt.cnt += floor;

        } else {

            Box srcHead = srcBelt.head;
            Box srcHalf = srcBelt.head;
            Box dstHead = dstBelt.head;

            for (int i = 1; i < floor; i++) {
                srcHalf = srcHalf.next;
            }

            Box srcNextHead = srcHalf.next;
            srcBelt.head = srcNextHead;
            srcNextHead.prev = null;

            dstBelt.head = srcHead;
            dstHead.prev = srcHalf;
            srcHalf.next = dstHead;

            srcBelt.cnt -= floor;
            dstBelt.cnt += floor;
        }

        //  진행 이후 m_dst의 선물의 총 수를 출력합니다.
        // 최대 100번까지만 주어집니다.
        return dstBelt.cnt;
    }

    public static int getBoxInfo(int p_num) { // 500

        Box box = boxes[p_num];

        int prev = -1;
        if (box.prev != null) prev = box.prev.idx;

        int next = -1;
        if (box.next != null) next = box.next.idx;

        // prev + 2 * next
        return prev + 2 * next;
    }

    public static int getBeltInfo(int b_num) { // 600
        Belt belt = belts[b_num];

        int head = -1;
        if (belt.head != null) head = belt.head.idx;

        int tail = -1;
        if (belt.tail != null) tail = belt.tail.idx;

        int cnt = belt.cnt;
        // head.idx + 2 * tail.idx + 3 * belt.cnt
        return head + 2 * tail + 3 * cnt;
    }

    public static void printBelt() {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {

            sb.append(i + "번 벨트 " + belts[i].cnt + "개 : ");
            Box box = belts[i].head;
            for (int j = 0; j < belts[i].cnt; j++) {
                if (box.prev == null) {
                    sb.append("null");
                } else sb.append(box.prev.idx);

                sb.append(" <- ");

                sb.append('[');
                sb.append(box.idx);
                sb.append(']');
                sb.append(" -> ");
                if (box.next == null) {
                    sb.append("null");
                    break;
                }
                sb.append(box.next.idx).append(" / ");
                box = box.next;
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException{
        q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 100:
                    init();
                    break;
                case 200:
                    System.out.println(
                            moveAll(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
                    );
                    break;
                case 300:
                    System.out.println(
                            moveFront(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
                    );
                    break;
                case 400:
                    System.out.println(
                            moveHalf(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
                    );
                    break;
                case 500:
                    System.out.println(
                            getBoxInfo(Integer.parseInt(st.nextToken()))
                    );
                    break;
                case 600:
                    System.out.println(
                            getBeltInfo(Integer.parseInt(st.nextToken()))
                    );
                    break;
            }
            System.out.println("cmd : " + cmd);
            printBelt();
        }
    }
}