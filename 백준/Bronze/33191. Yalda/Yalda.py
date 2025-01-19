M,*A=map(int,open(0).read().split())
for i in[0,1,2]:
    if A[i]<=M:
        print(['Watermelon','Pomegranates','Nuts'][i])
        exit(0)
print('Nothing')