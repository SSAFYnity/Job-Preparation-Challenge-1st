def solution(s):
    def isPalindrome(target_string):
        S = len(target_string)
        for i in range(S//2):
            if target_string[i] != target_string[S-i-1]:
                return False
        return True
    value = 1
    S = len(s)
    for start in range(S):
        for end in range(start+value,S+1):
            string = s[start:end]
            if isPalindrome(string):
                value = end-start
    return value