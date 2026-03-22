n,a,b=map(int,input().split())
s=input()
print(s[:a-1]+s[a-1:b][::-1]+s[b:])