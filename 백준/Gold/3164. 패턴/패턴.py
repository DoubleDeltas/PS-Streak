# max(x,y)%2 == 1

# a: x > y & x % 2 == 1
# b: x < y & y % 2 == 1
# c: x == y & x % 2 == 1

# a+b-c

x1, y1, x2, y2 = map(int,input().split())

#a
a=0
for y in range(y1, y2):
  x = max(x1, y+1)
  w = x2 - x
  if w <= 0:
    continue
  a += w // 2
  if w % 2 == 1 and x % 2 == 1:
    a += 1

#b
b=0
for x in range(x1, x2):
  y = max(y1, x+1)
  h = y2 - y
  if h <= 0:
    continue
  b += h // 2
  if h % 2 == 1 and y % 2 == 1:
    b += 1

# c
c = 0
d = max(x1, y1)
D = min(x2, y2)
l = D - d
if l > 0:
  c += l // 2
  if l % 2 == 1 and d % 2 == 1:
    c += 1

print(a+b+c)
