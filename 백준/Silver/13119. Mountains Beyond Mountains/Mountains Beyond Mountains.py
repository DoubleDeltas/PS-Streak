N,M,X=map(int,input().split())
A=[['.' for _ in range(M)] for _ in range(N)]

H = [*map(int,input().split())]
for x,h in enumerate(H):
    for y in range(h):
        A[y][x] = '#'

for x in range(M):
    A[X-1][x] = '*' if A[X-1][x] == '#' else '-'

for x in range(2, M, 3):
    if X-1 > H[x]:
        for y in range(H[x], X-1):
            A[y][x] = '|'

for y in range(N-1,-1,-1):
    for x in range(M):
        print(A[y][x],end='')
    print()