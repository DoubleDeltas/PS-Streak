from collections import Counter
c=[(k, v) for k,v in dict(Counter(s.rstrip().split('.')[1] for s in [*open(0)][1:])).items()]
c.sort()
for k, v in c:
    print(k, v)