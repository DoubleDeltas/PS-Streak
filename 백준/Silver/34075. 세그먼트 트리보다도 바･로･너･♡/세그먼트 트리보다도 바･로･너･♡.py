N=int(input())
alg=[]
for _ in range(N):
    name, d = input().split()
    alg.append((name, int(d)))
    
M=int(input())
mem={}
for _ in range(M):
    name, d = input().split()
    mem[name] = int(d)
    
Q=int(input())
for _ in range(Q):
    q = input()
    if q == 'nani ga suki?':
        print(f'{alg[1][0]} yori mo {alg[0][0]}')
    else:
        cmem = q.split()[0]
        alg.sort(key=lambda x:(abs(mem[cmem] - x[1]), x[0]))
        print('hai!')