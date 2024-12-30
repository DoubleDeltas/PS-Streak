l=[*range(21)]
for _ in range(10):
    a,b=map(int,input().split())
    l=l[:a]+l[b:a-1:-1]+l[b+1:]
print(*l[1:])