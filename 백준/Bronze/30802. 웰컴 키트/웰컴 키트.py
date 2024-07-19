n=int(input())
a=[*map(int,input().split())]
t,p=map(int,input().split())
print(sum((n+t-1)//t for n in a))
print(n//p,n%p)