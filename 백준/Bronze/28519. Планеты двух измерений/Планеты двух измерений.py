N,M=map(int,input().split())
print(min(N,M)*2+min(abs(N-M),1))