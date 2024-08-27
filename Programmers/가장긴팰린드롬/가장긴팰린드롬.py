def solution(s):
    answer = 0
    for i in range(len(s)):
        for j in range(len(s), i, -1):
            str = s[i:j]
            if str == str[::-1]:
                answer = max(answer, len(str))
    return answer


# def palindrome(str):

#     half = len(str)//2

#     if (len(str)%2) ==0:
#         str1 =str[:half]
#         str2 =str[half:]

#     else:
#         str1 =str[:half]
#         str2 =str[half+1:]

#     tmp=''
#     for i in str2:
#         tmp= i+tmp

#     if tmp == str1:
#         return True
#     else:
#         return False