L,R,A=map(int,input().split())
while L<R and A>0:
    L += 1
    A -= 1
while L>R and A>0:
    R += 1
    A -= 1
print(2*min(L,R)+A//2*2)