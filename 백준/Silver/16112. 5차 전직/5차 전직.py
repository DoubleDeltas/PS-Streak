n,k,*A=map(int,open(0).read().split())
A.sort()
print(sum(min(k,i)*A[i]for i in range(n)))