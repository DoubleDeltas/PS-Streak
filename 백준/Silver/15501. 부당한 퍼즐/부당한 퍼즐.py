from collections import deque

n = int(input())
p = deque(map(int, input().split()))
q = deque(map(int, input().split()))

for _ in range(n):
    q.rotate(-1)
    if p == q:
        print("good puzzle")
        exit(0)
r = deque([*q][::-1])
for _ in range(n):
    r.rotate(-1)
    if p == r:
        print("good puzzle")
        exit(0)
print("good puzzle" if p == q else "bad puzzle")