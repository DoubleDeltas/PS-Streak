A=[]
while 1:
  a,b=input().split()
  b=int(b)
  A.append((b, a))
  if a == 'Waterloo':
    break
A.sort()
print(A[0][1])