C = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
D = dict(zip(C, range(36)))
N = int(input())
W = [0]*36
sm=0
for _ in range(N):
  s = input()
  for i in range(len(s)):
    d = D[s[-i-1]]
    W[d] += 36 ** i
    sm += d * 36 ** i

K = int(input())
L = [((35 - i)*w, i) for i, w in enumerate(W)]
L.sort(reverse=True)

for _, i in L[:K]:
  sm += W[i] * (35 - i)

res = ""
while sm > 0:
  res = C[sm % 36] + res
  sm //= 36
if len(res) == 0:
  res = "0"
print(res)