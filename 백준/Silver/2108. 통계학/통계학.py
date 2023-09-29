import sys

n = int(sys.stdin.readline().rstrip())
ar = [int(sys.stdin.readline().rstrip()) for _ in range(n)]

ar.sort()

avg = round(sum(ar)/n)
med = ar[n//2]

most = ar[0]
secSet = False
cnt = 1
mostcnt = 0
for i in range(1, n):
    if ar[i] == ar[i-1]:
        cnt += 1
    else:
        if cnt > mostcnt:
            most = ar[i-1]
            mostcnt = cnt
            secSet = False
        elif cnt == mostcnt:
            if not secSet:
                most = ar[i-1]
                secSet = True
        cnt = 1
if cnt > mostcnt:
    most = ar[n-1]
elif cnt == mostcnt:
    if not secSet:
        most = ar[n-1]

rng = ar[n-1] - ar[0]

print(avg)
print(med)
print(most)
print(rng)