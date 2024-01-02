import sys
input=sys.stdin.readline
s=[]
for _ in range(int(input())):
  A=list(map(int,input().split()))
  if A[0] == 1:
    s.append(A[1])
  elif A[0] == 2:
    print(s.pop() if s else -1)
  elif A[0] == 3:
    print(len(s))
  elif A[0] == 4:
    print(0 if s else 1)
  elif A[0] == 5:
    print(s[-1] if s else -1)