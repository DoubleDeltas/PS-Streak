w,h=map(int,input().split())
n,a,b=map(int,input().split())
x=(w//a)*(h//b)
print((n-1)//x+1 if x else -1)