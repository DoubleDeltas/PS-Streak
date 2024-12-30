l=[int(input()) for _ in range(9)]
diff = sum(l) - 100
l.sort()
for i in range(9):
    for j in range(i+1, 9):
        if i == j: continue
        if l[i] + l[j] == diff:
            for k in range(9):
                if k == i or k == j: continue
                print(l[k])
            exit(0)