import math

def solve():
    x1, y1, r1, x2, y2, r2 = map(int, input().split())

    d = math.sqrt((x2-x1)**2 + (y2-y1)**2)
    if d > r1 + r2: # O o
        return 0
    if d == r1 + r2: # Oo
        return 1
    if d == 0:
        if r1 == r2: # O
            return -1
        else:
            return 0 # ◎
    if d == abs(r1 - r2): # @ : d + r == R
        return 1
    if d > abs(r1 - r2): # Cx> : d + r > R
        return 2
    return 0 # (O) : d + r < R
    


t = int(input())
for i in range(t):
    print(solve())
