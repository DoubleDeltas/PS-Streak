a=list(map(int,input().split()))
a.sort()
print(sum([a[4]-x<=1000 for x in a])-1)