n = int(input())
X = [*map(int,input().split())]
ans = 0
for i in range(n):
    a, b = map(int,input().split())
    if b > a and X[i]:
        ans += b - a
print(ans)