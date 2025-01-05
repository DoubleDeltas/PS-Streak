T,X=map(int,input().split())
s={*range(1,T+1)}
for _ in range(int(input())):
    input()
    s=s&{*map(int,input().split())}
print('YES' if X in s else 'NO')