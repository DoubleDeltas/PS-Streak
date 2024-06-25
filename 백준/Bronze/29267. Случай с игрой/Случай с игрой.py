n,k=map(int,input().split())
s,c=0,0
for _ in range(n):
    d=input()
    if d == 'save':
        s=c
    elif d == 'load':
        c=s
    elif d == 'shoot':
        c-=1
    else:
        c+=k
    print(c)