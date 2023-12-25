s=set()
S=input()
L=len(S)
for i in range(1, L+1):
  for j in range(L-i+1):
    s.add(S[j:j+i])
print(len(s))