N=int(input())
if N>=1000000:X=20
elif N>=500000:X=15
elif N>=100000:X=10
else:X=5
print(N*X//100,N*(100-X)//100)