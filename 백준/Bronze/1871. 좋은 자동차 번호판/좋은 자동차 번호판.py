for i in range(int(input())):
    A,B=input().split('-')
    a=0
    for c in A:
        a *= 26
        a += ord(c) - ord('A')
        A = A[:-1]
    b=int(B)
    print("nice"if abs(a-b)<=100 else"not nice")