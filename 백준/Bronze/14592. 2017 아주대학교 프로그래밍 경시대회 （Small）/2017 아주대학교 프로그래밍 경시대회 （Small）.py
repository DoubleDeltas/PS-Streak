S=[]
n=int(input())
for i in range(1, n+1):
    s,c,l=map(int,input().split())
    S.append((-s,c,l,i))
print(min(S)[3])