from math import lcm
n,a,b=map(int,input().split())
x=n//lcm(a,b)
print(n//a-x,n//b-x,x)