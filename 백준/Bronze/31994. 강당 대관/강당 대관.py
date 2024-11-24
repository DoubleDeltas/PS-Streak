M=[input().split() for _ in range(7)]
idx=0
for i in range(1,7):
  if int(M[i][1]) > int(M[idx][1]):
    idx = i
print(M[idx][0])