from fractions import Fraction

T = int(input())
for _ in range(T):
    N, M = map(int,input().split())

    cat = [Fraction(0) for _ in range(N)]

    for j in range(M):
        V, *A = map(int,input().split())
        for i, a in enumerate(A):
            cat[i] += Fraction(a, V)
    
    print(max(cat) - min(cat))