L = []
for _ in range(int(input())):
    L.append([*map(int,input().split())]) 
for C in L:
    X, Y, R = C
    A = 0
    for c in L:
        if C == c:
            continue
        x, y, r = c
        if (X-x)**2 + (Y-y)**2 < (R+r)**2:
            A += 1
    print(A)
        