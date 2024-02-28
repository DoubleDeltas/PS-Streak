N=int(input())
M=input()
K=int(input())
if K == 0:
  print('YES')
elif K > N:
  print('YES' if M == '0'*N else 'NO')
else:
  print('YES' if M[-K:] == '0'*K else 'NO')