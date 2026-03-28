N,M,S=map(int,input().split())
print(min((M+1)*S*(100-N)//100, M*S))