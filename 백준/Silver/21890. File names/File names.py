ans = 0
for t in range(int(input())):
    s=input()
    if s.count('.') != 1:
        continue
    n,x=s.split('.')
    N,X=len(n),len(x)
    if 0 < N <= 8 and 0 < X <= 3:
        ans += 1
print(ans)