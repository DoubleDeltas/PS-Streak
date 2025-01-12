while 1:
    B,A=input()[::-1].split()
    if'0'==B==A:break
    ans=0
    c=False
    for i in range(10):
        b = int(B[i]) if i<len(B) else 0
        a = int(A[i]) if i<len(A) else 0
        if c:=b+a+c>9:ans+=1
    print(ans)