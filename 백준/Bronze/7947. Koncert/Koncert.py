for _ in range(int(input())):
    colors = [[*map(int,input().split())] for _ in range(10)]
    rgb = [*map(lambda x:(sum(x)+5)//10, zip(*colors))]
    print(*rgb)
