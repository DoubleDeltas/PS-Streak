solution=lambda l:''.join(dict([*zip([1,-1,10,-10],'wsda')])[l[i]-l[i-1]] for i in range(1,len(l)))