for _ in range(int(input())):
  A,B=input().split()
  Ay=int(A[1])-1
  Ax=ord(A[0])-ord('A')
  b=int(B)-1
  By=b//8
  Bx=b%8
  print('YES' if (Ay+Ax) % 2 == (By+Bx) % 2 else 'NO')