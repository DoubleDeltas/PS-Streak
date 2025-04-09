for s in [*open(0)][:-1]:
    a, b = map(int,s.split())
    d = a-b
    if d == 1:
        print('0 0')
    elif d % 2:
        print(d//2-1, 1)
    else:
        print(d//2, 0)