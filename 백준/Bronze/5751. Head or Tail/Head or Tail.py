while True:
    n = int(input())
    if n == 0:
        break
    A = [*map(int,input().split())]
    print(f'Mary won {A.count(0)} times and John won {A.count(1)} times')