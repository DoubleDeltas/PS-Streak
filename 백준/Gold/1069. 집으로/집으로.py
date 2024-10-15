from math import sqrt, ceil, floor

x,y,d,t=map(int,input().split())
D=sqrt((x*x)+(y*y))
ans=0

a = t + t * ceil(D / d)

if d >= D: d = 2*D - d

if d <= t:
    print(min(a, D))
    exit()

jmp = floor(D / d)
ans += jmp * t
D -= jmp * d

ans += min(D, t + max(0, D-d))

print(min(a, ans))