from fractions import Fraction 

n,b=map(int,input().split())
X,Y=[*zip(*[map(int,input().split()) for _ in range(n)])]
Sx, Sy = sum(X), sum(Y)
if Sx == 0:
    print('EZPZ')
else:
    print(Fraction(Sy - b*n, Sx))