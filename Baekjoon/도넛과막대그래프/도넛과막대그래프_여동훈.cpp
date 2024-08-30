#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <map>
using namespace std;


struct info{
    int s, e;
};
vector<int> todo;
map<int,bool> visited;
vector<int> graph[1000000];

int find_new_point(vector<vector<int>> vc){
    int inV[1000000] = {0}; 
    int outV[1000000] = {0};
    int rows = vc.size();

    for(int i =0 ; i< rows; i++){
        inV[vc[i][1]]++; // 들어오는 간선
        outV[vc[i][0]]++; // 나가는 간선
        
    }
    for(int i = 0 ; i<rows; i++){
        if(outV[vc[i][0]] - inV[vc[i][0]] >= 2){ // 나가는 간선이 들어오는 간선 보다 2 이상 크면
            return vc[i][0];          
        }
    }
    return 0;
}
void make_graph(vector<vector<int>> vc){
    for(int i =0 ; i< vc.size(); i++){
        int start = vc[i][0];
        int end = vc[i][1];
        graph[start].push_back(end);
    }
}
void make_todo(vector<vector<int>> vc, int point){
    for(int i =0 ; i< vc.size(); i++){
        if(vc[i][0] == point){
            todo.push_back(vc[i][1]);
        }
    }
}

bool check_visited(int t){
    if(visited.find(t) != visited.end()){
        return true;
    }
    return false;
}
vector<int> solution(vector<vector<int>> edges) {
    vector<int> answer;
    int answer_box[4] = {0};
    int rows = edges.size();
    make_graph(edges);
    int new_point = find_new_point(edges); 
    make_todo(edges, new_point);
    answer.push_back(new_point);
    visited[new_point] = true;
    
    for(int i = 0 ; i< todo.size(); i++){
        
        int flag=2; // 기본은 막대
        queue<int> q;
        q.push(todo[i]);
        int start = todo[i]; // 현재 노드 저장
        while(!q.empty()){
            int now = q.front(); q.pop();
            if(graph[now].size() >= 2){ // 8자
                flag = 3;
            }
            for(int i = 0 ; i< graph[now].size(); i++){
                int next = graph[now][i];
                if(flag != 3 && start == next){ // 도넛 1 자기자신을 돌아옴
                    flag = 1;
                } 
                if(check_visited(next)) continue;
                visited[next] = true;
                q.push(next);
            } 
        }
        answer_box[flag] ++;
    }
    
    for(int i = 1; i<= 3 ; i++){
        answer.push_back(answer_box[i]);
    }
    
    return answer;
}