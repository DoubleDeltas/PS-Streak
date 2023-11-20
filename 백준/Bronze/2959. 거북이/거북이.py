X=list(map(int,input().split()))
X.sort()
print(max(X[0]*X[1], X[0]*X[2]))

