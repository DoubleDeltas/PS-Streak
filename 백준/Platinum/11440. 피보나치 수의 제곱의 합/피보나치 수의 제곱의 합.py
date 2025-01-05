M = 1_000_000_007

d = {0: 0, 1: 1, 2: 1}

def f(n):
    if n in d.keys():
        return d[n]
    if n % 2:
        d[n] = (pow(f(n//2), 2, M) + pow(f(n//2+1), 2, M)) % M
    else:
        d[n] = (f(n+1) - f(n-1)) % M
    return d[n]

n = int(input())
print((f(n-1)+f(n))*f(n) % M)