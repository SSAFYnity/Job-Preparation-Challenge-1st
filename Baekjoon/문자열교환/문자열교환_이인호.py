str= input()

arr = str.split()

tri_arr = str*3
min_change = len(str)
b_cnt =0
for i in str:
    if i == "b":
        b_cnt+=1
for i in range(len(tri_arr)-b_cnt):
    a_cnt=0
    tmp = tri_arr[i:i+b_cnt]
    # print(tmp)
    for j in tmp:
        if j == "a":
            a_cnt+=1

    min_change= min(min_change,a_cnt)
print(min_change)