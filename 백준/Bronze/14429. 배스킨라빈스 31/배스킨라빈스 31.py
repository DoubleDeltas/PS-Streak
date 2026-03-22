n=int(input())
A=[]
def f(j,m):
    return ((j-1)//(m+1)+1)*2

for i in range(1, n+1):
    j,m=map(int,input().split())
    A.append((f(j,m), i))
print(*min(A)[::-1])