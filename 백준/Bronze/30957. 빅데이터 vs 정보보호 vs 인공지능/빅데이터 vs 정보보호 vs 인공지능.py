input()
l=input()
b,s,a=[l.count(w)for w in'BSA']
m=max(b,s,a)
if b==s==a==m:print('SCU')
else:
    if b==m:print('B',end='')
    if s==m:print('S',end='')
    if a==m:print('A',end='')
    