N=int(input())
A=[0]*(N+1)
exp=act=0
maxi=0
P=int(input())
for i in range(1, P+1):
    p,k=map(int,input().split())
    for j in range(p, k+1):
      if A[j] == 0:
        A[j] = i
    if k-p > maxi:
      exp = i
      maxi = k-p
print(exp)
maxi=0
for i in range(1, P+1):
   c=A.count(i)
   if c > maxi:
      act = i
      maxi = c
print(act)