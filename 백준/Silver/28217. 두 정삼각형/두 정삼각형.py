# no move: 00 10 11 20 21 22 ...
# rotate cw: 

def id(N, y, x):
    return y, x

def cw(N, y, x):
    return N-1-x, y-x

def ccw(N, y, x):
    return N-1-x, N-1-y

def flip(N, y, x):
    return y, y-x

N=int(input())

A = []
for Y in range(N):
    A.append([*map(int,input().split())])

B = []
for Y in range(N):
    B.append([*map(int,input().split())])

ans = 9999
for fA in (id, cw, ccw, flip):
    for fB in (id, cw, ccw, flip):
        acc = 0
        for Y in range(N):
            for X in range(Y+1):
                yA, xA = fA(N, Y, X)
                yB, xB = fB(N, Y, X)
                if A[yA][xA] != B[yB][xB]:
                    acc += 1
        ans = min(ans, acc)

print(ans)