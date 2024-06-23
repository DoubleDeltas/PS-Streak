for _ in range(int(input())):
    s=int(input())
    for _ in range(int(input())):
        s+=eval(input().replace(' ','*'))
    print(s)