N, Q = map(int,input().split())
A = [*map(int,input().split())]
offset = 0
for _ in range(Q):
    query = input()
    if query[0] == '1':
        _, i, x = map(int, query.split())
        A[(i + offset - 1) % N] += x
    elif query[0] == '2':
        _, s = map(int, query.split())
        offset -= s
    else:
        _, s = map(int, query.split())
        offset += s

for i in range(N):
    print(A[(i + offset) % N], end=' ')