for _ in range(int(input())):
    N=int(input())
    M=int(str(N)[::-1])
    P=str(N+M)
    print('YES' if P == P[::-1] else 'NO')
    