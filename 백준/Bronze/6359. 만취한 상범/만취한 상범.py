T=int(input())
for _ in range(T):
    n = int(input())
    is_open = [False]*(n+1)
    for i in range(1, n+1):
        for j in range(i, n+1, i):
            is_open[j] = not is_open[j]
    print(sum(is_open))