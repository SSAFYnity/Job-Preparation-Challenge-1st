import java.util.ArrayList;

class Solution {

    private class Node {
        ArrayList<Integer> in, out;
        public Node() {
            in = new ArrayList<>();
            out = new ArrayList<>();
        }
    }

    private ArrayList<Node> nodes;
    private int[] isGraph;
    private int cnt;

    public int[] solution(int[][] edges) {
        // 노드 생성 및 경로 저장
        nodes = new ArrayList<>();
        nodes.add(new Node());

        for(int[] a : edges) {
            init(a[0]);
            init(a[1]);
            nodes.get(a[1]).in.add(a[0]);
            nodes.get(a[0]).out.add(a[1]);
        }

        int[] answer = new int[4];

        for(int i = 1; i < nodes.size(); i++) { // 하나씩 임의 정점으로 선택

            Node start = nodes.get(i);

            if(start.in.size() == 0 && start.out.size() > 0) { // 임의 정점은 진입 없고, 진출 있음

                //System.out.println(i + "번 노드에서 시작");
                answer = new int[4];    // { 노드번호, 도넛 수, 막대 수, 8 수 }
                cnt = nodes.size();     // 전체 노드 수(1부터 시작)
                isGraph = new int[cnt];
                cnt -= 2; // 0번 노드와 start노드 제외 -> 그래프 판별되어야 하는 노드 수
                boolean flag = true;

                for(int target : start.out) {
                    if(isDoughnut(i, target))
                        answer[1]++;
                    else if(isBar(i, target))
                        answer[2]++;
                    else if(is8(i, target))
                        answer[3]++;
                    else {
                        flag = false;
                        break;
                    }
                }

                if(flag && cnt == 0) { // 모든 노드가 그래프에 포함 됨
                    answer[0] = i;
                    return answer;
                }
            }
        }
        return answer;
    }

    private boolean isDoughnut(int start, int target) {
        //System.out.println("도넛인지 확인 : " + target);

        boolean[] visited = new boolean[nodes.size()];
        int now = target;

        while(!visited[now]) {
            visited[now] = true;

            Node node = nodes.get(now);

            if(isGraph[now] > 0
                    || node.out.size() != 1
                    || (node.in.size() == 2 && !node.in.contains(start))
                    || (node.in.size() != 1 && node.in.size() != 2)) return false;

            now = node.out.get(0);
        }

        for(int i = 0; i < visited.length; i++) {
            if(visited[i]) {
                isGraph[i] = 1;
                cnt--;
            }
        }
        //System.out.println("도넛임!");
        return true;
    }

    private boolean isBar(int start, int target) {
        //System.out.println("막대인지 확인 : " + target);

        boolean[] visited = new boolean[nodes.size()];
        int now = target;

        // 정방향
        while(now > 0 && !visited[now]) {
            visited[now] = true;

            Node node = nodes.get(now);

            if(isGraph[now] > 0 || node.out.size() > 1) return false;

            now = node.out.size() == 0 ? 0 : node.out.get(0);
        }

        // 역방향
        Node t = nodes.get(target);

        if(t.in.size() == 2) {
            int temp = t.in.get(0);
            now = temp == start ? t.in.get(1) : temp;

            while(now > 0 && !visited[now]) {
                visited[now] = true;

                Node node = nodes.get(now);

                if(isGraph[now] > 0 || node.in.size() > 1) return false;

                now = node.in.size() == 0 ? 0 : node.in.get(0);
            }
        }


        for(int i = 0; i < visited.length; i++) {
            if(visited[i]) {
                isGraph[i] = 2;
                cnt--;
            }
        }
        //System.out.println("막대임!");
        return true;
    }

    private boolean is8(int start, int target) {
        //System.out.println("8자인지 확인 : " + target);

        boolean[] visited = new boolean[nodes.size()];
        int now = target;
        int mid = -1;

        // 8자 허리 찾기
        while(!visited[now]) {
            visited[now] = true;
            // System.out.println(now);

            Node node = nodes.get(now);

            if(isGraph[now] > 0
                    || node.out.size() == 0 || node.out.size() > 2
                    || (node.in.size() == 3 && !node.in.contains(start))
                    || node.in.size() == 0 || node.in.size() > 3) {
                //System.out.println("종료 : " + now);
                return false;
            }

            now = node.out.get(0);
        }
        // System.out.println("지금 : " + now);
        if(nodes.get(now).out.size() == 2) { // 허리 찾음
            mid = now;
        }
        else return false;

        // System.out.println("허리 찾음");

        // 허리 기준 한쪽
        visited = new boolean[nodes.size()];
        visited[mid] = true;
        now = nodes.get(mid).out.get(0);

        while(!visited[now]) {
            visited[now] = true;

            Node node = nodes.get(now);

            if(isGraph[now] > 0
                    || node.out.size() != 1
                    || (node.in.size() == 2 && !node.in.contains(start))
                    || (node.in.size() != 1 && node.in.size() != 2)) return false;

            now = node.out.get(0);
        }

        // 허리 기준 다른 한쪽
        visited[mid] = true;
        now = nodes.get(mid).out.get(1);

        while(!visited[now]) {
            visited[now] = true;

            Node node = nodes.get(now);

            if(isGraph[now] > 0
                    || node.out.size() != 1
                    || (node.in.size() == 2 && !node.in.contains(start))
                    || (node.in.size() != 1 && node.in.size() != 2)) return false;

            now = node.out.get(0);
        }

        for(int i = 0; i < visited.length; i++) {
            if(visited[i]) {
                isGraph[i] = 3;
                cnt--;
            }
        }
        // System.out.println("8자임!");
        return true;
    }

    private void init(int n) { // 아직 생성하지 없는 노드면, 해당 노드까지 생성
        int size = nodes.size();
        if(size <= n) {
            for(int i = 0; i < n - size + 1; i++)
                nodes.add(new Node());
        }
    }

    private void printEdge() {
        for(int i = 0; i < nodes.size(); i++) {
            System.out.println(i + "번 노드");
            for(int input : nodes.get(i).in) {
                System.out.print(input + " ");
            }
            System.out.println();
            for(int output : nodes.get(i).out) {
                System.out.print(output + " ");
            }
            System.out.println();
        }
    }
}

/*

< 문제 분석 >
- 3가지 그래프 모양이 있음 : 도넛, 막대. 8자
    - 도넛 : 순환. 들어오는거 1 + 나가는거 1
    - 막대 : 비순환. 들어오는거 0 or 1 + 나가는거 0 or 1
    - 8자 : 순환. 2번 밟는거 1개 있음. 들어오는거 1 or 2 + 나가는거 1 or 2
- 1개의 임의 정점을 찍어 다른 그래프들에 간선 하나씩 연결한 상태로 edges가 주어짐
    - 임의 정점 : 들어오는 간선이 없고, 나가는 간선만 있다.
    - 나머지 정점들 끼리 그래프 모양을 이루고, 임의 정점이 거기에 연결 되어 있어야 한다.
    - 이때, 연결 위치는 해당 그래프의 아무데나 상관 없음

< 풀이 전략 >
1) 한 정점씩 지워보며 확인하기
2) 다른 노드들이 모두 그래프 형태를 띄우고 있는지 확인
3) 해당 그래프 모두에 정점이 연결되어 있는지 확인
4) 연결 안되어 있거나, 모두 그래프 형태가 되지 않는다면 다시 1) 실행
5) 정답 출력

*/