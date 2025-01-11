n=int(input())
X,Y=[*zip(*[map(int,input().split()) for _ in range(n)])]
Sx, Sy = sum(X), sum(Y)
Sxx = sum(x*x for x in X)
Sxy = sum(X[i]*Y[i] for i in range(n))

if Sx*Sx != n*Sxx:
    a2 = (n*Sxy - Sx*Sy) / (n*Sxx - Sx*Sx)
    b2 = (Sy - a2*Sx) / n
    print(a2, b2)
else:
    print('EZPZ')