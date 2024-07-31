import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class 교점에별만들기_강민정 {
    class Point {
        long x;     // 열
        long y;     // 행
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public String[] solution(int[][] line) {
        String[] answer;    // 모든 별을 포함하는 최소 삼각형 배열
        List<Point> points = new ArrayList<>();     // 교점을 담는 리스트
        char[][] arr;
        long minX = Long.MAX_VALUE;     // 최소 열
        long maxX = Long.MIN_VALUE;     // 최대 열
        long minY = Long.MAX_VALUE;     // 최소 행
        long maxY = Long.MIN_VALUE;     // 최대 열
        
        for(int i = 0; i < line.length - 1; i++) {
            for(int j = i + 1; j < line.length; j++) {
                Point point = getIntersectionPoint(line[i], line[j]);   // 교점 좌표를 구하기
                if(point != null) {     // 교점을 구할 수 있고, 정수 좌표가 맞으면
                    points.add(point);      // 정수 교점 좌표 추가
                    // 행, 열의 크기를 알기 위해 갱신한다.
                    minX = Math.min(minX, point.x);
                    maxX = Math.max(maxX, point.x);
                    minY = Math.min(minY, point.y);
                    maxY = Math.max(maxY, point.y);
                }
            }
        }
        
        int rowSize = (int)(maxY - minY + 1);     // 행 길이
        int colSize = (int)(maxX - minX + 1);     // 열 길이
        
        arr = new char[rowSize][colSize];       // 문자 배열
        answer = new String[rowSize];
        
        for(int i = 0; i < rowSize; i++) {
            Arrays.fill(arr[i], '.');       // 문자 배열을 빈 공간으로 채운다.
        }
        
        for(Point point : points) {         // 교점 좌표를 꺼내서
            int x = (int)(point.x - minX);        // 열
            int y = (int)(maxY - point.y);        // 행
            arr[y][x] = '*';            // 별표 표시
        }
        
        for(int i = 0; i < rowSize; i++) {
            answer[i] = new String(arr[i]);     // 문자를 문자열로 변환
        }
        
        return answer;
    }
    
    /*
        두 직선의 좌표를 구하는 공식에 따라 교점을 구한다.
    */
    public Point getIntersectionPoint(int[] line1, int[] line2) {
        long a1 = line1[0];
        long b1 = line1[1];
        long c1 = line1[2];
        long a2 = line2[0];
        long b2 = line2[1];
        long c2 = line2[2];
        
        long denominator = a1 * b2 - a2 * b1;
        if(denominator == 0) {      // 0으로 나눌 수 없으니까
            return null;
        }
        
        double x = (double)(b1 * c2 - b2 * c1) / denominator;
        double y = (double)(c1 * a2 - c2 * a1) / denominator;
        
        if((x % 1 != 0) || (y % 1 != 0)) {      // 정수가 아니라면
            return null;
        }
        
        return new Point((long)x, (long)y);
    }
}
