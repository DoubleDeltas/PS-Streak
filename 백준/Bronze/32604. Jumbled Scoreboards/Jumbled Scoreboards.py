pa=pb=-1
for _ in range(int(input())):
    a,b=map(int,input().split())
    if pa > a or pb > b:
        print("no")
        break
    pa,pb=a,b        
else:
    print("yes")