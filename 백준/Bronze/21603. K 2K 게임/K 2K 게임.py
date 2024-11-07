f=lambda x:x%10
N,K=map(int,input().split())
S=[i for i in range(1,N+1)if f(i)!=f(K) and f(i)!=f(2*K)]
print(len(S))
print(*S)