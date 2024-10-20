N=int(input())
A=[*map(int,input().split())]

B=[*A]
up = 0
for i in range(N-1):
  for j in range(i, -1, -1):
    if B[j] > B[j+1]:
      up += 1
      B[j], B[j+1] = B[j+1], B[j]

B=[*A]
down = 0
for i in range(N-1):
  for j in range(i, -1, -1):
    if B[j] < B[j+1]:
      down += 1
      B[j], B[j+1] = B[j+1], B[j]

print(min(up, down + 1))