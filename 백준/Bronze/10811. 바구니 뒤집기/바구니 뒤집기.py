N,M=map(int,input().split())
A=list(range(1,N+1))
for _ in range(M):
    i,j=map(int,input().split())
    i,j=i-1,j-1
    A=A[:i]+A[j:(i-1 if i>0 else None):-1]+A[j+1:]
for n in A:
    print(n, end=' ')
