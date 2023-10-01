T=int(input())
K,M,E,X,F=list(map(int,input().split()))+[0]*(5-T)
print(((508 if K>E else -108)*(K-E)+(212 if M>X else -305)*(M-X)+707*F)*4763)