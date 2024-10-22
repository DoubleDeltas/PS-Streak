A=[True]*100_001
A[0]=A[1]=False
for i in range(2, 100_001):
    for j in range(i*2, 100_001, i):
        A[j] = False

N=int(input())
input()
print("Yes" if A[N] else "No")