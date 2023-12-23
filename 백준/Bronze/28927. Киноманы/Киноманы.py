T,E,F=map(int,input().split())
t,e,f=map(int,input().split())
W=3*T+20*E+120*F
w=3*t+20*e+120*f
print(['Draw','Max','Mel'][(W>w)-(W<w)])
