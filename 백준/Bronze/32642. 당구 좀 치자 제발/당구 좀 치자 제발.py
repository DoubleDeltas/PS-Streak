N=int(input())
A=[2*a-1 for a in map(int,input().split())]
S=[0]*N
S[0]=A[0]
for i in range(1,N):
    S[i]=S[i-1]+A[i]
print(sum(S))