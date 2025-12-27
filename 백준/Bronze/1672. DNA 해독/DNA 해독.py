A,G,C,T=range(4)
table=[[A,C,A,G],[C,G,T,A],[A,T,C,G],[G,A,G,T]]
d=dict(zip('AGCT', (A,G,C,T)))
n=input()
dna=[*map(lambda x: d[x], input())]
while len(dna) > 1:
    b=dna.pop()
    a=dna.pop()
    dna.append(table[a][b])
print('AGCT'[dna[0]])