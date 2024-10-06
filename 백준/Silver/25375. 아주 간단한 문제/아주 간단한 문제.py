import sys
input = sys.stdin.readline

Q=int(input())
for _ in range(Q):
    a,b=map(int,input().split())
    print(+(a < b and b % a == 0))