a='.omln'
b='owln.'
c='..oLn'
print(*[''.join(t)for t in zip(*[{a:b,b:a,c:c}[''.join(t)]for t in zip(*[input()for _ in[0]*5])])],sep='\n')