n=int(input())
o=sum(1 for c in input()if c in'02468')
print((o>n-o)+2*(o<n-o)-1)