for i in range(1, 10):
  print(f'? A {i}')
  if input() == '1':
    A=i
    break

for i in range(1, 10):
  print(f'? B {i}')
  if input() == '1':
    B=i
    break
print(f'! {A+B}')