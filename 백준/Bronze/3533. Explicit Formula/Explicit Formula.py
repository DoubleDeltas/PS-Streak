f=0
x=[*map(int,input().split())]

for i in range(0, 10):
    for j in range(i+1, 10):
        f ^= x[i] | x[j]
for i in range(0, 10):
    for j in range(i+1, 10):
        for k in range(j+1, 10):
            f ^= x[i] | x[j] | x[k]
print(f)