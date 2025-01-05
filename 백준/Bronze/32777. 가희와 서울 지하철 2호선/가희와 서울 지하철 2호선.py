for _ in range(int(input())):
    a,b=map(int,input().split())
    m=(b-a)%43
    n=(a-b)%43
    if m<n:
        print('Inner circle line')
    elif m>n:
        print('Outer circle line')
    else:
        print('Same')
    