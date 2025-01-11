N,m=map(int,input().split())
T=[N//2,(N+1)//2,(N+2)//3]  # even, odd, 3k+1
a=0
if N == 1:
    print(2 if m > 0 else 1)
elif N == 2:
    if m == 0:
        print(1)
    elif m == 1:
        print(3)
    else:
        print(4)
else:
    for i in range(8):
        s=0
        for j in range(3):
            if (i & (1 << j)) != 0:
                s += T[j]
        if s <= m:
            a+=1
    print(a)