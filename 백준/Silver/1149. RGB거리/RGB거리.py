L=[0]*3
exec("M=input().split();L=[int(M[i])+min(L[i-2],L[i-1])for i in[0,1,2]];"*int(input()))
print(min(L))