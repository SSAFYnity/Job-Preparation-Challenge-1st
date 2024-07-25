#include <stdio.h>
#include <stdlib.h>

using namespace std;

typedef struct Node 
{
    int data;
    struct Node *next;
}Node;
 
typedef struct Queue 
{
    Node *front;
    Node *rear; 
    int count; // 큐 안의 노드 개수 
}Queue;
 
void initQueue(Queue *queue)
{
    queue->front = queue->rear = NULL; 
    queue->count = 0;   // 큐 안의 노드 개수를 0으로 설정
}
 
int isEmpty(Queue *queue)
{
    return queue->count == 0;   // 큐안의 노드 개수가 0이면 빈 상태
}
 
void enqueue(Queue *queue, int data)
{
    Node *newNode = (Node *)malloc(sizeof(Node)); // newNode 생성
    newNode->data = data;
    newNode->next = NULL;
 
    if (isEmpty(queue)) // 큐가 비어있을 때
    {
    queue->front = newNode;  
    }
    else    // 비어있지 않을 때
    {
    queue->rear->next = newNode;    //맨 뒤의 다음을 newNode로 설정
    }
    queue->rear = newNode;  //맨 뒤를 newNode로 설정 
    queue->count++; //큐안의 노드 개수를 1 증가
}
 
int dequeue(Queue *queue)
{
    int data;
    Node *ptr;
    if (isEmpty(queue)) //큐가 비었을 때
    {
    printf("Error : Queue is empty!\n");
    return 0;
    }
    ptr = queue->front; //맨 앞의 노드 ptr 설정 
    data = ptr->data;   // return 할 데이터 
    queue->front = ptr->next;   //맨 앞은 ptr의 다음 노드로 설정
    free(ptr);  // ptr 해제 
    queue->count--; //큐의 노드 개수를 1 감소

    return data;
}
 
int main(){
    Queue que;
    initQueue(&que);
    int N=10;
    for (int n = 0; n < N; n++)
    {
        /* code */
        int test;
        scanf("%d",&test);
        for (int i = 0; i < 8; i++)
        {
            int temp;
            scanf("%d", &temp);
            enqueue(&que, temp);
        }
        int cnt =1;
        while (!isEmpty(&que))  // 큐가 빌 때까지 
        {
            int t=dequeue(&que);
            //printf("%d\n", t); //큐에서 꺼내온 값 출력
            if(t>=cnt){
                t-=cnt;
            }else{
                t=0;
            }
            enqueue(&que,t);
            if(t==0){
                break;
            }
            cnt+=1;
            if(cnt>5){
                cnt=1;
            }
        }
        printf("#%d ", n+1);
        while(!isEmpty(&que)){
            printf("%d ", dequeue(&que));
        }
        printf("\n");
    }
    
    return 0;
}

