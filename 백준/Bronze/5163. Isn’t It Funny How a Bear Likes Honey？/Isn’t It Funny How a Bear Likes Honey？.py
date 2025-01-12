from math import pi
A = 4*pi/3 / 1000
K = int(input())
for t in range(1, K+1):
    b, w = [typ(arg) for typ, arg in zip([int, float], input().split())]
    B = A * sum(float(input())**3 for _ in range(b))
    print(f'Data Set {t}:')
    print('Yes' if B >= w else 'No')
    if t < K: print()