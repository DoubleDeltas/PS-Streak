N, M = map(int,input().split())
A = [0, *map(int,input().split())]
S = [0] * (N+1)
ans = 0
for i in range(1, N+1):
    S[i] = max(0, S[i-1] + A[i])
    if S[i] >= M:
        ans += 1
print(ans)