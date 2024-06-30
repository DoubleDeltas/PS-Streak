for s in open(0):
  N,w,d,S=map(int,s.split())
  print((((N-1)*N//2*w-S)//d-1)%N+1)