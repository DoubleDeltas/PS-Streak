while 1:
    n,d=map(int,input().split())
    if n==d==0:
        break
    print(f'{n//d} {n%d} / {d}')