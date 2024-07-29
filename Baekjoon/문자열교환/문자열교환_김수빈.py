strings = input()
extend_str = strings + strings
a_count = strings.count('a')
min_b_count = 1001
length = len(strings)

for i in range(length):
    curr_b_count = 0
    for j in range(i + a_count - 1):
        if extend_str[j] == 'b':
            curr_b_count += 1
    min_b_count = min(min_b_count, curr_b_count)

print(min_b_count)