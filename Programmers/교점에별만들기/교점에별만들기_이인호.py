def solution(line):
    arr =[]

    for i in range(len(line)):
        for j in range(len(line)):
            if i != j:
                xy = getxy(line[i],line[j])
                if xy is not None:
                    arr.append(xy)
    arr =list(set(arr))
    print(arr)

    arr.sort(key=lambda x:(x[1],-x[0]),reverse=True)

    min_x = min(arr, key=lambda p: p[0])[0]
    max_x = max(arr, key=lambda p: p[0])[0]
    min_y = min(arr, key=lambda p: p[1])[1]
    max_y = max(arr, key=lambda p: p[1])[1]


    answer = [['.' for _ in range(min_x,max_x+1)] for _ in range(min_y,max_y+1)]
    for i in arr:
        x = i[0] - min_x
        y = max_y - i[1]
        answer[y][x] = '*'

    answer = [''.join(row) for row in answer]

    if len(arr) ==1 :
        if arr[0] == (0,0):
            answer = ["*"]


    return answer


def getxy(A,B):
    x,y =0,0

    if A[0]*B[1]- A[1]*B[0] != 0:
        x= (A[1]*B[2]- A[2]*B[1])/(A[0]*B[1]- A[1]*B[0])
        y= (A[2]*B[0]- A[0]*B[2])/(A[0]*B[1]- A[1]*B[0])
    else:
        return None

    if x%1 ==0 and y%1 ==0:
        return (int(x),int(y))
    else:
        return None


