Sab = int(input())
Ma, Fab, Mb = map(int,input().split())
if Sab <= 240 or Ma + Fab + Mb >= Sab:
    print('high speed rail')
else:
    print('flight')