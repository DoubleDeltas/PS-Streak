for s in[*open(0)][1:]:
    a,b=map(int,s.split())
    print('TAK'if b%a==0 else'NIE')