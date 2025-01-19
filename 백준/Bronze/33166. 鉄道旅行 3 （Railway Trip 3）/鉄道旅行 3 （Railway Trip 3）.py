P,Q=map(int,input().split())
A,B=map(int,input().split())
print(A*min(Q,P)+B*max(Q-P,0))