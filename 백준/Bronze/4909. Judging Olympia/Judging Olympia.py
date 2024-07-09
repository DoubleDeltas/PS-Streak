while 1:
    A=[*map(int,input().split())]
    S=sum(A)
    if S==0:break
    X=S-min(A)-max(A)
    print(str(X//4)+['','.25','.5','.75'][X%4])