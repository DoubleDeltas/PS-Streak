flips=' 15??2??8?'
W, N=input().split()
N=int(N)
M=[[*map(int,input().split())]for _ in range(N)]
f=range(N)
r=range(N-1,-1,-1)
if W == 'L' or W == 'R':
    yr, xr = f, r
else:
    yr, xr = r, f
for y in yr:
    for x in xr:
        print(flips[M[y][x]], end=' ')
    print()