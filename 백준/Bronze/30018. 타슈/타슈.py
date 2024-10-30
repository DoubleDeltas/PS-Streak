N=int(input())
A=[*map(int,input().split())]
B=[*map(int,input().split())]
D=[abs(A[i]-B[i]) for i in range(N)]
print(sum(D)//2)