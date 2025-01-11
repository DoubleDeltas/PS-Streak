X=[*map(str.strip,open(0))]
N,M=map(int,X[0].split())
S={s[:i+1] for s in X[1:N+1] for i in range(len(s))}
print(sum(s in S for s in X[N+1:]))