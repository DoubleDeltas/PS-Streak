a=[1]*5+[2]*7+[3]*12+[4]*17+[5]*20+[6]*17+[7]*12+[8]*7+[9]*4
N,K=map(int,input().split())
G=list(map(int,input().split()))
for i in range(K):
    P=G[i]*100//N
    print(a[P], end=' ')