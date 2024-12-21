m={'K':0,'P':1,'N':3,'B':3,'R':5,'Q':9,'k':0,'p':-1,'n':-3,'b':-3,'r':-5,'q':-9,'.':0}
a=0
for i in range(8):
    for c in input():
        a+=m[c]
print(a)
    