N = int(input())

D = 2*N - 1

cnt = 0
buf_x = [False]*N
buf_b = [False]*D   # backslash(\)
buf_s = [False]*D   # slash(/)

def backtrack(y):
    global cnt
    if y == N:
        cnt += 1    # case completed
        return
    
    for x in range(N):
        b, s = (x + y) % D, (x - y + D) % D
        if buf_x[x] or buf_b[b] or buf_s[s]:
            continue
        
        buf_x[x] = True
        buf_b[b] = True
        buf_s[s] = True
        backtrack(y + 1)
        buf_s[s] = False
        buf_b[b] = False
        buf_x[x] = False

backtrack(0)
print(cnt)