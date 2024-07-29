a=0
n,k,x,y=map(int,input().split())
for _ in[0]*n:
 X,Y=map(int,input().split())
 if(X-x)**2+(Y-y)**2>k*k:a+=1
print(a)