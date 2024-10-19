f = [0]*1_000_001
for i in range(1, 1_000_001):
  for j in range(i, 1_000_001, i):
    f[j] += i

N = int(input())
print(sum(f[1:N+1]))