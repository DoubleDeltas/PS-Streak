R,G,B=[*zip(*[map(int,s.split())for s in [*open(0)][1:]])]
N=len(R)
r,g,b=[0]*N,[0]*N,[0]*N
for i in range(N):
    p=(i-1)%N
    r[i]=R[i]+min(g[p],b[p])
    g[i]=G[i]+min(r[p],b[p])
    b[i]=B[i]+min(r[p],g[p])
print(min(r[-1],g[-1],b[-1]))