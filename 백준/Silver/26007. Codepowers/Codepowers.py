import sys
input=sys.stdin.readline

N, M, K, X = map(int,input().split())
A = [0, *map(int,input().split())]

S = [0] * (N+1)
P = [0] * (N+2)
S[0] = X
for i in range(1, N+1):
  S[i] = S[i-1] + A[i]
  P[i] = P[i-1]
  if S[i] < K:
    P[i] += 1

for j in range(M):
  L, R = map(int,input().split())
  print(P[R-1] - P[L-1])