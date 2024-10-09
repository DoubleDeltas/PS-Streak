while True:
    M, A, B = map(int,input().split())
    if M == A == B == 0:
        break
    t = round((M/A - M/B) * 3600)
    print(f'%01d:%02d:%02d' % (t // 3600, t // 60 % 60, t % 60))