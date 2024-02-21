for _ in range(int(input())):
  _, T = input().split()
  if T == 'C':
    print(*map(lambda c:ord(c)-64,input().split()))
  else:
    print(*map(lambda n:chr(n+64),map(int,input().split())))