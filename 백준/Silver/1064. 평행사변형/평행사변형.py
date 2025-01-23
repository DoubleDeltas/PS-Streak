xa, ya, xb, yb, xc, yc = map(int,input().split())

if (yb-ya)*(xc-xa) == (yc-ya)*(xb-xa):
    print(-1.0)
    exit(0)

ab = ((xa - xb)**2 + (ya - yb)**2)**.5
bc = ((xb - xc)**2 + (yb - yc)**2)**.5
ca = ((xc - xa)**2 + (yc - ya)**2)**.5

d1=2*(ab+bc)
d2=2*(bc+ca)
d3=2*(ca+ab)
print(max(d1,d2,d3)-min(d1,d2,d3))