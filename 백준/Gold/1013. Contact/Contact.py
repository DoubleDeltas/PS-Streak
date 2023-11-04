import re

regex = re.compile('^(100+1+|01)+$')

for _ in range(int(input())):
    if regex.match(input()):
      print('YES')
    else:
      print('NO')