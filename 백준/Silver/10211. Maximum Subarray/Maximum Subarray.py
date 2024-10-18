for _ in range(int(input())):
  N=int(input())
  A=[0, *map(int,input().split())]
  S=[0]*(N+1)
  for i in range(1,N+1):
    S[i] = S[i-1] + A[i]
  ans = -10**9
  for i in range(1, N+1):
    for j in range(i, N+1):
      ans = max(ans, S[j] - S[i-1])
  print(ans)
