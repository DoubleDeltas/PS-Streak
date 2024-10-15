n,m,k=map(int,input().split())
pM = m*k
pm = pM - m + 1
print(max(n - pM, 0), max(n - pm, 0))