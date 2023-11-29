T = [
  ['Wide Receiver', 4.5, 150, 200],
  ['Lineman', 6.0, 300, 500],
  ['Quarterback', 5.0, 200, 300],
]
while True:
  v, w, p = map(float, input().split())
  if (v, w, p) == (0, 0, 0):
    break
  s = ''
  for t in T:
    if v <= t[1] and w >= t[2] and p >= t[3]:
      s += t[0] + ' '
  if s == '':
    s = 'No positions'
  print(s)