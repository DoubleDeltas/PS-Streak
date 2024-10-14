import sys
input=sys.stdin.readline
S=0
for _ in range(int(input())):
  op, *x = input().split()
  if len(x)>0: x = int(x[0])-1
  if op == "add":S |= 1 << x
  if op == "remove": S &= ~(1 << x)
  if op == "check": print((S >> x) & 1)
  if op == "toggle": S ^= (1 << x)
  if op == "all": S = (1 << 20) - 1
  if op == "empty": S = 0
