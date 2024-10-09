N, M=map(int, input().split())
a = 1
for i in range(1, N+1):
    a = (a * i) % M
print(a)