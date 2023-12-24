p,r=map(int,input().split())
print(['weak','normal','strong','very strong'][min(p*5//r,3)])