import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    List<Point> list = new ArrayList<>();
    boolean [][] visited;
    int [][] score;
    int n, m;
    
    public int solution(int[][] maze) {
        int answer = 0;
        int res = 0;
        n = maze.length;            // 행
        m = maze[0].length;         // 열

        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                if(maze[i][j] == 1){
                    res = Math.max(findEnd(i, j, 3, maze), res);
                }else if(maze[i][j] == 2){
                    res = Math.max(findEnd(i, j, 4, maze), res);
                }
            }
        }

        if(res == 17){
            answer = 0;
        }else{
            answer = res;
        }

        return answer;
    }
    
    int findEnd (int x, int y, int target, int[][] maze){
        int maxNum = 17;
        visited = new boolean[n][m];
        score = new int[n][m];
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(x, y));
        visited[x][y] = true;
        score[x][y] = 0;


        while(!que.isEmpty()){
            Point p = que.poll();

            if(maze[p.x][p.y] == target){
                maxNum = score[p.x][p.y];
                break;
            }

            int [] dx = {-1, 0, 1, 0};
            int [] dy = {0, 1, 0, -1};

            for(int i = 0; i < 4; i++){
                int tx = p.x + dx[i];
                int ty = p.y + dy[i];

                if(tx < 0 || tx >= n || ty < 0 || ty >= m) continue;        // 범위 밖 이동 X
                if(visited[tx][ty]) continue;
                if(maze[tx][ty] == 5) continue;     // 벽 통과 X
                if(maze[tx][ty] == 0 || maze[tx][ty] == 3 || maze[tx][ty] == 4){
                    visited[tx][ty] = true;
                    score[tx][ty] = score[p.x][p.y] + 1;
                    que.add(new Point(tx, ty));

                }
            }

        }

        return maxNum;
    }
}