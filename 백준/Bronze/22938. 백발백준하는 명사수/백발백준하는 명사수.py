x1,y1,r1=map(int,input().split())
x2,y2,r2=map(int,input().split())
dx=x1-x2
dy=y1-y2
print('YES'if dx**2+dy**2<(r1+r2)**2 else 'NO')