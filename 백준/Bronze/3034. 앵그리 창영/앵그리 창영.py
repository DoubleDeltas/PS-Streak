N,W,H=map(int,input().split())
for _ in range(N):
    n=int(input())
    print('NE'if n*n>W*W+H*H else'DA')