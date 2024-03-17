for _ in range(int(input())):
    s=input().replace(' ','')
    x=[*set(filter(lambda c:s.count(c)==max(s.count(c)for c in s),s))]
    print(x[0]if 2>len(x)else'?')