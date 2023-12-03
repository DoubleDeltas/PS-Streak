A=[c=='Y'for c in input()]
L=len(A)
a=0
for i in range(L):
  if A[i]:
    a += 1
    for j in range(i,L,i+1):
      A[j] = not A[j]
print(a)