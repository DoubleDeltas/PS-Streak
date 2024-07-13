t,s,h=map(int,open(0))
S=' '*s
for _ in[0]*t:print('*'+S+'*'+S+'*')
print('*'*(2*s+3))
for _ in[0]*h:print(S+' '+'*')