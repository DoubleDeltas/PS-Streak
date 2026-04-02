a=0
M=[*map(int,input().split())]
N=int(input())
for _ in range(N):
    b,l,s=input().split()
    b,l,s=int(b),float(l),int(s)
    if s>=17 and l>=2.0:a+=M[b]
print(a)