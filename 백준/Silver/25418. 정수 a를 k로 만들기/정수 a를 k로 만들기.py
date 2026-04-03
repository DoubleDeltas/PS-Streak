from collections import deque
A,K=map(int,input().split())
E=[False]*1000001
def solve():
    q=deque([A])
    for i in range(1<<30):
        for _ in range(len(q)):
            a=q.popleft()
            if a == K:
                return i
            if a+1 <= K and not E[a+1]:
                q.append(a+1)
                E[a+1]=True
            if a*2 <= K and not E[a*2]:
                q.append(a*2)
                E[a*2]=True

print(solve())