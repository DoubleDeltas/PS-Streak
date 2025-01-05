a,b,_=map(int,input().split('/'))
print(['US','EU','either'][(b<13)+(a<13 and b<13)])