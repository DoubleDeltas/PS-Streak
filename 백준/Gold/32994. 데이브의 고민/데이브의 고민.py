N, M = map(int,input().split())
for y in range(N):
    for x in range(M):
        print((y*2+x) % 5 + 1, end=' ')
    print()