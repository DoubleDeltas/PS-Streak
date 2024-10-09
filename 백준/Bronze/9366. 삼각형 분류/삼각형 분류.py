T = int(input())
for i in range(1, T+1):
    A, B, C = map(int,input().split())
    if sum([A, B, C]) <= 2*max(A, B, C):
        print(f'Case #{i}: invalid!')
    elif A == B == C:
        print(f'Case #{i}: equilateral')
    elif A == B or B == C or C == A:
        print(f'Case #{i}: isosceles')
    else:
        print(f'Case #{i}: scalene')