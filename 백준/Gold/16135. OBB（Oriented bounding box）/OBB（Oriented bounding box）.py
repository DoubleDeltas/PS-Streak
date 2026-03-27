adot = lambda ux, uy, vx, vy: abs(ux*vx + uy*vy)

# input order?
a1x, a1y, a2x, a2y, a3x, a3y, a4x, a4y = map(float,input().split())
b1x, b1y, b2x, b2y, b3x, b3y, b4x, b4y = map(float,input().split())

amx, amy = (a1x + a3x)/2, (a1y + a3y)/2
bmx, bmy = (b1x + b3x)/2, (b1y + b3y)/2
dx, dy = bmx - amx, bmy - amy
ahx, ahy = (a1x + a2x)/2, (a1y + a2y)/2
bhx, bhy = (b1x + b2x)/2, (b1y + b2y)/2
A1x, A1y = ahx - amx, ahy - amy
B1x, B1y = bhx - bmx, bhy - bmy
A2x, A2y = a1x - ahx, a1y - ahy
B2x, B2y = b1x - bhx, b1y - bhy

for ux, uy in (a2x-a1x, a2y-a1y), (a3x-a2x, a3y-a2y):
 adotu = lambda vx, vy: adot(vx, vy, ux, uy)
 if adotu(dx, dy) >= adotu(A1x, A1y) + adotu(A2x, A2y) + adotu(B1x, B1y) + adotu(B2x, B2y):
  print(0)
  break
else:
  print(1)