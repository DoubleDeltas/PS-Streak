d={'.':'.','O':'O','-':'|','|':'-','/':'\\','\\':'/','^':'<','<':'v','v':'>','>':'^'}
N,M=map(int,input().split())
s=[]
for _ in range(N):
    s.append(input())

for j in range(M):
    for i in range(N):
        print(d[s[i][M-1-j]], end='')
    print()