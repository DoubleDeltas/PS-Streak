ops={
    '+':lambda a,b:a+b,
    '-':lambda a,b:a-b,
    '*':lambda a,b:a*b,
    '/':lambda a,b:-(abs(a)//abs(b)) if a<0 or b<0 else a//b
}
k1,o1,k2,o2,k3=input().split()
k1,k2,k3=map(int,(k1,k2,k3))

a=ops[o2](ops[o1](k1, k2), k3)
b=ops[o1](k1, ops[o2](k2, k3))
print(min(a,b))
print(max(a,b))
