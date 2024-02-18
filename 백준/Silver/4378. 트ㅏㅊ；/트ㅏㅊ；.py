a="1234567890-=WERTYUIOP[]\\SDFGHJKL;'XCVBNM,./ \n"
b="`1234567890-QWERTYUIOP[]ASDFGHJKL;ZXCVBNM,. \n"
d={}
for i in range(len(a)):
  d[a[i]] = b[i]

for l in open(0):
  for c in l:
    print(d[c],end='')