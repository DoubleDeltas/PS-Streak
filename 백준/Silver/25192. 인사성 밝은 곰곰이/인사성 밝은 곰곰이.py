N=int(input())
s=set()
a=0
for _ in range(N):
  S=input()
  if S=='ENTER':
    a+=len(s)
    s.clear()
  else:
    if S not in s:
      s.add(S)
a+=len(s)
print(a)