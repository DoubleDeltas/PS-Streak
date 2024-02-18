n,m,k=map(int,input().split())
x=1000000
y=1000000
for t in range(1,k+1):
  f, d = map(int,input().split())
  if f-d < x:
    x = f-d
    y = t
  elif f-d == x:
    y = min(y,t)
print(y)