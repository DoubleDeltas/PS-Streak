while True:
    n = int(input())
    if n == 0: break

    a = list(map(int,input().split()))

    cur = ans = a[0] + a[1] + a[2]
    for i in range(3, n):
        cur -= a[i-3]
        cur += a[i]
        ans = max(ans, cur)

    print(ans)