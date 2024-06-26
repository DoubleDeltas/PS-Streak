def main():
  N=int(input())
  A=[True]*N
  for a in map(int,input().split()):
    if a < 1 or a > N or not A[a-1]:
      print('NIE')
      return
    A[a-1] = False
  print('TAK')
main()