for n in [*map(int,''.join(open(0)).replace('\n',' ').split())][:-1]:
    x=sum(i for i in range(1,n) if n%i==0)
    if x==n:
        print(n, 'PERFECT')
    elif x<n:
        print(n, 'DEFICIENT')
    else:
        print(n, 'ABUNDANT')