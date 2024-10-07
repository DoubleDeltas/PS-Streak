N, M = map(int,input().split())
X = [[0]*N, [0]*N]
head = [True]*N

for i in range(N):
    X[True][i], X[False][i] = map(int,input().split())

for _ in range(M):
    K = int(input())
    for i in range(N):
        if X[head[i]][i] <= K:
            head[i] = not head[i]

print(sum(X[head[i]][i] for i in range(N)))