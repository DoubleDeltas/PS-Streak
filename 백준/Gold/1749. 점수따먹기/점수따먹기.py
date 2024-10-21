import sys
input = sys.stdin.readline

N, M = map(int,input().split())
A = [[0]*(M+1), *[[0, *map(int,input().split())] for _ in range(N)]]
S = [[0]*(M+1), *[[0]*(M+1) for _ in range(N)]]

for i in range(1, N+1):
  for j in range(1, M+1):
    S[i][j] = S[i-1][j] + S[i][j-1] - S[i-1][j-1] + A[i][j]

ans = -10**9
for i1 in range(1, N+1):
  for j1 in range(1, M+1):
    for i2 in range(i1, N+1):
      for j2 in range(j1, M+1):
        ans = max(ans, S[i2][j2] - S[i2][j1-1] - S[i1-1][j2] + S[i1-1][j1-1])

print(ans)