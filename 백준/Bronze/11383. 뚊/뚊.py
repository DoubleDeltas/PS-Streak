N, M = map(int,input().split())
eyfa = True
A = [input() for y in range(N)]
B = [input() for y in range(N)]

for y in range(N):
    for x in range(2*M):
        if B[y][x] != A[y][x//2]:
            eyfa = False

print('Eyfa' if eyfa else 'Not Eyfa')