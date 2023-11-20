N, _ = map(int, input().split())
K = list(map(int,input().split()))

ans = 0

L = len(str(N))
def recur(i, n):
  global ans
  if n > N: return
  if i > L: return
  ans = max(ans, n)
  for m in K:
    recur(i+1, n*10+m)


recur(0, 0)
print(ans)