n=int(input())
A=[*map(int,input().split())]
l=[]
for i,a in enumerate(A):
    l.insert(a,i+1)
print(*l[::-1])