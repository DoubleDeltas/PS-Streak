def f(y, x):
    layer = max(abs(y), abs(x))
    if layer == 0:
        return 1
    
    side = 2*layer-1
    
    v, u = -y+x, -y-x
    walk = 0
    if v>0 and u<0:
        walk = v
    elif v>0 and u==0:
        walk = side + 1
    elif v>0 and u>0:
        walk = side + 1 + u
    elif v==0 and u>0:
        walk = 2*side + 2
    elif v<0 and u>0:
        walk = 2*side + 2 - v
    elif v<0 and u==0:
        walk = 3*side + 3
    elif v<0 and u<0:
        walk = 3*side + 3 - u
    else:   # v==0 and u<0
        walk = 4*side + 4

    return side*side + walk


r1, c1, r2, c2 = map(int, input().split())
A=dict()
for i, y in enumerate(range(r1, r2+1)):
    for j, x in enumerate(range(c1, c2+1)):
        A[(i, j)] = f(y, x)

maxlen = max({len(str(v)) for v in A.values()})
for i, _ in enumerate(range(r1, r2+1)):
    for j, x in enumerate(range(c1, c2+1)):
        v = A[(i, j)]
        vlen = len(str(v))
        print(' '*(maxlen-vlen)+str(v), end='')
        if x < c2:print(' ',end='')
    print()