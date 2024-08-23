import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

class 표병합_강민정 {
    static final int ROW_SIZE = 51;
    static final int COL_SIZE = 51;

    static String[][] table = new String[ROW_SIZE][COL_SIZE];          // 실제 표의 크기는 50 * 50
    static Node[][] mergeArr = new Node[ROW_SIZE][COL_SIZE];           // 병합 정보를 담는 배열
    static List<String> lst = new ArrayList();

    public String[] solution(String[] commands) {       // 실행할 명령어들이 담긴 1차원 문자열 배열
        String[] answer;
        initMergeArr();         // mergeArr 배열 초기화

        for(String command : commands) {                // 명령어를 하나씩 실행
            String[] splitedCommand = command.split(" ");
            switch(splitedCommand[0]) {
                case "UPDATE":
                    if(splitedCommand.length == 4) {
                        int r = Integer.parseInt(splitedCommand[1]);
                        int c = Integer.parseInt(splitedCommand[2]);
                        table[mergeArr[r][c].r][mergeArr[r][c].c] = splitedCommand[3];
                    } else {
                        updateValues(splitedCommand[1], splitedCommand[2]);
                    }
                    break;
                case "MERGE":       // (r1, c1) 위치의 셀과 (r2, c2) 위치의 셀을 선택해 병합
                    int r1 = Integer.parseInt(splitedCommand[1]);
                    int c1 = Integer.parseInt(splitedCommand[2]);
                    int r2 = Integer.parseInt(splitedCommand[3]);
                    int c2 = Integer.parseInt(splitedCommand[4]);
                    merge(r1, c1, r2, c2);
                    break;
                case "UNMERGE":     // (r, c) 위치의 셀을 선택하여 해당 셀의 모든 병합을 해제
                    int r = Integer.parseInt(splitedCommand[1]);
                    int c = Integer.parseInt(splitedCommand[2]);
                    unMerge(r, c);
                    break;
                case "PRINT":       // (r, c) 위치의 셀을 선택하여 값을 출력
                    int x = Integer.parseInt(splitedCommand[1]);
                    int y = Integer.parseInt(splitedCommand[2]);
                    print(x, y);
                    break;
            }
        }

        answer = new String[lst.size()];
        for(int i = 0; i < lst.size(); i++) {
            answer[i] = lst.get(i);
        }

        return answer;
    }

    public void updateValues(String value1, String value2) {
        for(int r = 1; r < ROW_SIZE; r++) {
            for(int c = 1; c < COL_SIZE; c++) {
                if(!Objects.equals(table[r][c], null) && table[r][c].equals(value1)) {
                    table[r][c] = value2;
                }
            }
        }
    }

    /*
        (r1, c1) 위치의 셀과 (r2, c2) 위치의 셀을 선택하여 병합
    */
    public void merge(int r1, int c1, int r2, int c2) {
        // (r1, c1), (r2, c2)가 가리키는 병합된 좌표
        int pointR1 = mergeArr[r1][c1].r;
        int pointC1 = mergeArr[r1][c1].c;
        int pointR2 = mergeArr[r2][c2].r;
        int pointC2 = mergeArr[r2][c2].c;

        // (r2, c2)를 가리키는 셀들은 (r1, c1)을 가리키도록 한다.
        for(int r = 1; r < ROW_SIZE; r++) {
            for(int c = 1; c < COL_SIZE; c++) {
                if(mergeArr[r][c].r == pointR2 && mergeArr[r][c].c == pointC2) {
                    mergeArr[r][c] = new Node(pointR1, pointC1);
                }
            }
        }

        if(pointR1 == pointR2 && pointC1 == pointC2) {      // 선택한 두 위치의 셀이 같은 셀일 경우 무시
            return;
        } else {
            if(!Objects.equals(table[pointR1][pointC1], null) && Objects.equals(table[pointR2][pointC2], null)) {  // (r1, c1)이 값을 가지고 있을 때 병합된 셀은 그 값을 가진다.
                table[pointR2][pointC2] = table[pointR1][pointC1];
            } else if(Objects.equals(table[pointR1][pointC1], null) && !Objects.equals(table[pointR2][pointC2], null)) {    // (r2, c2)가 값을 가지고 있을 때 병합된 셀은 그 값을 가진다.
                table[pointR1][pointC1] = table[pointR2][pointC2];
            } else if(!Objects.equals(table[pointR1][pointC1], null) && !Objects.equals(table[pointR2][pointC2], null)) {    // 두 셀 모두 값을 가지고 있다.
                table[pointR2][pointC2] = table[pointR1][pointC1];      // (r1, c1) 위치의 셀 값을 가진다.
            }
        }
    }

    public void unMerge(int r, int c) {
        int pointR1 = mergeArr[r][c].r;
        int pointC1 = mergeArr[r][c].c;
        String pointValue = table[pointR1][pointC1];    // 병합된 셀에 저장된 값

        for(int x = 1; x < ROW_SIZE; x++) {
            for(int y = 1; y < COL_SIZE; y++) {
                if(mergeArr[x][y].r == pointR1 && mergeArr[x][y].c == pointC1) {
                    mergeArr[x][y] = new Node(x, y);        // 자기 자신을 가리키도록 초기화
                    table[x][y] = null;                     // 셀의 값을 초기화
                }
            }
        }

        table[r][c] = pointValue;
    }

    /*
        병합되어 있어서 실제로 비어있는 게 아닌 경우도 출력해야 할듯
    */
    public void print(int r, int c) {
        int pointR = mergeArr[r][c].r;
        int pointC = mergeArr[r][c].c;
        if(Objects.equals(table[pointR][pointC], null)) {
            lst.add("EMPTY");    // 선택한 셀이 비어있다면
        } else {
            lst.add(table[pointR][pointC]);
        }
    }

    /*
        mergeaArr 배열의 값들을 자신의 좌표를 가리키도록 초기화
    */
    public void initMergeArr() {
        for(int r = 1; r < ROW_SIZE; r++) {
            for(int c = 1; c < COL_SIZE; c++) {
                mergeArr[r][c] = new Node(r, c);
            }
        }
    }

    class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}