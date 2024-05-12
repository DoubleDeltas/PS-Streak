for _ in range(int(input())):
    P,M=map(int,input().split())
    l=[input() for _ in range(P)]
    s=set(l)
    print(len(l)-len(s))