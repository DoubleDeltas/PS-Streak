T=[-1]*36
def t(x):
    if T[x] != -1:return T[x]
    if x==0:return 1
    T[x] = sum(t(i)*t(x-i-1) for i in range(x))
    return T[x]

n=int(input())
print(t(n))