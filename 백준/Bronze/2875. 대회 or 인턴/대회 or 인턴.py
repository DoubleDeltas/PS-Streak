N, M, K = map(int,input().split())
T=min(N//2, M)
L=N+M-3*T
T-=max(0, (K-L+2)//3)
print(T)