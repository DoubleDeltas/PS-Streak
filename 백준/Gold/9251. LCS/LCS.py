A=input()
B=input()
N,M=len(A),len(B)
memo=[[0]*(M+1) for _ in range(N+1)]

for i in range(1, N+1):
  for j in range(1, M+1):
    if A[i-1] == B[j-1]:
      memo[i][j] = memo[i-1][j-1] + 1
    else:
      memo[i][j] = max(memo[i-1][j], memo[i][j-1])
print(memo[-1][-1])