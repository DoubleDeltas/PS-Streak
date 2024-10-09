N, A, B = map(int,input().split())
good, bad = 1, 1
for _ in range(N):
    good += A
    bad += B
    if good < bad:
        good, bad = bad, good
    if good == bad:
        bad -= 1
print(good, bad)