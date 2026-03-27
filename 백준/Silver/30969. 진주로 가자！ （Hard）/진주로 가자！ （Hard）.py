import sys
input=sys.stdin.readline

N=int(input())
A=[0]*1003
S=[0]*1003
j=-1
for _ in range(N):
    a,b=input().split()
    b=int(b)
    A[min(b,1001)]+=1
    if a=='jinju':j=b

S[1002]=0
for i in range(1001, -1, -1):S[i]=S[i+1]+A[i]
print(j)
print(S[j+1])