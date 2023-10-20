for t in range(1,int(input())+1):
    n=int(input())
    print(f'Case #{t}: ', end='')
    if n <= 25:
        print('World Finals')
    elif n <= 1000:
        print('Round 3')
    elif n <= 4500:
        print('Round 2')
    else:
        print('Round 1')
        
