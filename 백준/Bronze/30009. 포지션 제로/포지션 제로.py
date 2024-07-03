_,X,_,R,*T=map(int,open(0).read().split())
a=b=0
for t in T:d=abs(t-X);a+=d<R;b+=d==R
print(a,b)