K = int(input())
for t in range(1, K+1):
 b,w=input().split()
 B=.0041887902047863905*sum(float(input())**3 for _ in range(int(b)))
 print(f'Data Set {t}:','YNeos'[B<float(w)::2]+'\n'[:t<K],sep='\n')