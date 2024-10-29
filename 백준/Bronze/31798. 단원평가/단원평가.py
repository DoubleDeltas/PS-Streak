a,b,c=map(int,input().split())
if a==0:
    print(c*c-b)
elif b==0:
    print(c*c-a)
else:
    print(int((a+b)**.5))
    