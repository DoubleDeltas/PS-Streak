N=int(input())
A=list(map(int,input().split()))
odd=[]
even=[]
for i in range(N):
  if A[i] % 2 == 1:
    odd.append(A[i])
  else:
    even.append(A[i])
if len(odd) % 2 == 0:
  print(sum(A))
else:
  odd.sort()
  print(sum(even)+sum(odd[1:]))