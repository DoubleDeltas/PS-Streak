r=input
p=print
l=len
N=int(r())
M=int(r())
E=abs(N-100)
if M>9:p(E);exit()
S={*r().split()}if M else{*[]}
f=lambda x:l(S&{*str(x)})<1
D=1e9
for i in range(9**9):
 if N>=i and f(N-i):D=l(str(N-i))+i;break
 if f(N+i):D=l(str(N+i))+i;break
p(min(D,E))