n=int(input())
if n==0:
  print(0)
else:
  a=0
  while 1<<a < n:
    a+=1
  print(a+1)