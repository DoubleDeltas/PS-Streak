X=["G...",".I.T","..S."]
K=int(input())
for i in range(K*3):
    for j in range(K*4):
        print(X[i//K][j//K], end='')
    print()