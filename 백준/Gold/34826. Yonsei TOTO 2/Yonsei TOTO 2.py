N,M,S=map(int,input().split())
A=[*map(int,input().split())]
B=[*map(int,input().split())]

C=[0]*N
s=S
for i in sorted(range(N),key=lambda i:-B[i]/A[i]):
    x=min(s,M,A[i])
    C[i]=x
    s-=x
print(*C)