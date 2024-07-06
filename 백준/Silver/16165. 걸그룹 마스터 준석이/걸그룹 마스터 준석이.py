N,M=map(int,input().split())
D={}
T={}
for _ in[0]*N:
  t=input()
  D[t]=[]
  for _ in[0]*int(input()):
    m=input()
    D[t].append(m)
    T[m]=t
  D[t].sort()
for _ in[0]*M:
  q=input()
  if input()=='1':
    print(T[q])
  else:
    print(*D[q],sep='\n')