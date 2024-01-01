import sys
input=sys.stdin.readline

N=int(input())

L=[0]*(N+1)
U=[0]*(N+1)
for x in map(int,input().split()):
    if x > 0:
        U[x] += 1
    else:
        L[-x] += 1

LS=[0]*(N+1)
US=[0]*(N+1)

LS[0] = 0
for i in range(1, N+1):
    LS[i] = LS[i-1] + L[i-1]
US[N] = 0
for i in range(N-1, -1, -1):
    US[i] = US[i+1] + U[i+1]

ans = []
for i in range(N+1):
    if US[i] + LS[i] == i:
        ans.append(i)
print(len(ans))
print(*ans)