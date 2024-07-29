m,M=map(int,input().split())
s=[1]*(M-m+1)
for i in range(2,1000001):
 I=i*i
 for j in range(m+(I-m%I)%I,M+1,I):s[j-m]=0
print(sum(s))