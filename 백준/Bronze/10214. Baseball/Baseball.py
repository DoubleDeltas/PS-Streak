Y=K=0
for _ in range(int(input())):
    for _ in range(9):
        y,k=map(int,input().split())
        Y+=y
        K+=k
    print(['Yonsei','Draw','Korea'][(Y<=K)+(Y<K)])