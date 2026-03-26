N=int(input())
S=sorted(map(int,input().split()))
a=S[-1]
for i in range(N):
    if S[i]!=i:break
    a=max(a,S[max(N//2, N-1-i)]+i+1)
print(a)