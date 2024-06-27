A=input()
B=input()
print(*[max(A[i],B[i])for i in range(len(A))],sep='')