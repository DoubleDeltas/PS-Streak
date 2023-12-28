y,m,d=map(int,input().split('-'))
x=y*10000+m*100+d
print(['TOO LATE','GOOD'][x <= 20230916])