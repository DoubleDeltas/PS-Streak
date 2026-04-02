D=dict(zip('ABCDEFGHIJKLMNO', map(lambda x:(x//4,x%4),range(15))))
d=lambda c,y,x:abs(D[c][0]-y)+abs(D[c][1]-x)
a=0
for y in range(4):
    s=input()
    for x in range(4):
        if s[x]=='.': continue
        a+=d(s[x],y,x)
print(a)