h1, m1, s1 = map(int, input().split(':'))
h2, m2, s2 = map(int, input().split(':'))

d = (((h2 - h1) * 60) + (m2 - m1)) * 60 + (s2 - s1)
if d <= 0:
    d += 86400
h3 = d // 3600
m3 = d // 60 % 60
s3 = d % 60
print('%02d:%02d:%02d' % (h3, m3, s3))