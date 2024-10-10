s=int(input())
ans=0
while s-(ans*(ans+1)//2) > ans:
    ans += 1
print(ans)