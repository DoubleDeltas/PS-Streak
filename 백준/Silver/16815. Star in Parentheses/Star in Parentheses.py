s=0
for c in input():
  if c == '(':
    s += 1
  elif c == ')':
    s -= 1
  else: # *
    print(s)