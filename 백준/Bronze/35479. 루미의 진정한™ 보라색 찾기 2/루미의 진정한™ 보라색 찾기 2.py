R,G,B=map(lambda x:int(x)/255,input().split())
X=max(R,G,B)
C,M,Y=map(lambda c:(X-c)/X if X>0 else 0,[R,G,B])
print(C,M,Y,1-X)