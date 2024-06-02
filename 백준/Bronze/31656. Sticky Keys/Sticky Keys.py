s=input()
print(s[0], end='')
for i in range(1, len(s)):
    if s[i-1] != s[i]:
        print(s[i], end='')