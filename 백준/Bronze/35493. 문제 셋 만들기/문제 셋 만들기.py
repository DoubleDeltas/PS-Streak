n=int(input())
d=[*map(int,input().split())]
print("NO" if len(d)==1 and d[0]%2==1 else "YES")