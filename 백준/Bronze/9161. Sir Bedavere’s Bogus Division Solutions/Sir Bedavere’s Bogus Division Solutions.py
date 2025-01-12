for X in range(100,1000):
    for Y in range(100,1000):
        if X % 111 == 0 and Y % 111 == 0:
            continue
        if X % 10 != Y // 100:
            continue
        x, y = X // 10, Y % 100
        if X * y == x * Y:
            print(f'{X} / {Y} = {x} / {y}')