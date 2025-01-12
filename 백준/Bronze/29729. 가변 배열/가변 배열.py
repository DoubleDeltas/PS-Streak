S0,N,M=map(int,input().split())
s=0
c=S0
for _ in range(N+M):
    if input()=='1':
        if s==c:c*=2
        s+=1
    else:
        s-=1
print(c)