from math import sin
a, b, c = map(int, input().split())

x = c / a
while 1:
    y = a*x + b*sin(x) - c
    if abs(y) < 1e-9:
        break
    x -= y/(a+b)
print(x)