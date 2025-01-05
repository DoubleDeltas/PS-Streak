_,Y=zip(*[[*map(int,s.split())]for s in[*open(0)][1:]])
print(max(Y)-min(Y))