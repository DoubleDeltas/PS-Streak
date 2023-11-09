A = list(map(int, input().split()))
S = input()
A.sort()
for i in range(3):
    print(A[ord(S[i]) - ord('A')], end=' ')