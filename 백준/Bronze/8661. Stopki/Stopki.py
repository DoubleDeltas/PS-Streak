p=0
x,k,a=map(int,input().split())
while x>=0:
    p=1-p
    x-=(k if p else a)
print(p)