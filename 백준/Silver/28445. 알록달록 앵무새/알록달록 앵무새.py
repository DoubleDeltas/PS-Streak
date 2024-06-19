a,b=input().split()
c,d=input().split()
s=[*{
  f'{a} {a}', f'{a} {b}', f'{a} {c}', f'{a} {d}',
  f'{b} {a}', f'{b} {b}', f'{b} {c}', f'{b} {d}',
  f'{c} {a}', f'{c} {b}', f'{c} {c}', f'{c} {d}',
  f'{d} {a}', f'{d} {b}', f'{d} {c}', f'{d} {d}'
}]
s.sort()
for t in s:
  print(t)
