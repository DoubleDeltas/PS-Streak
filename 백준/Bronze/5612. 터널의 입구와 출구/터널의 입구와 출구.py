def main():
    n=int(input())
    m=int(input())
    ans=m
    for _ in range(n):
        a,b=map(int,input().split())
        m += a - b
        if m < 0:
            print(0)
            return
        ans=max(ans, m)
    print(ans)
    
main()