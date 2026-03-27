def solve():
    N=int(input())
    if N == 0:return "INSOMNIA"
    s=set()
    for i in range(1,10**9):
        a=i*N
        for c in str(a):s.add(c)
        if s == {*'0123456789'}:return a

for t in range(1, int(input())+1):print(f'Case #{t}: {solve()}')