N=int(input())
A=int(input())
x=0
for _ in range(N):
    B=int(input())
    x+=min((A-B)%360,(B-A)%360)
    A=B
print(x)