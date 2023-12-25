d='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'
R=''
N,B=map(int,input().split())
while N>0:
  R=d[N%B]+R
  N//=B
print(R)