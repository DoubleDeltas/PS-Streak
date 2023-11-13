l=[
    'Never gonna give you up',
    'Never gonna let you down',
    'Never gonna run around and desert you',
    'Never gonna make you cry',
    'Never gonna say goodbye',
    'Never gonna tell a lie and hurt you',
    'Never gonna stop',
]
ans = 'No'
for _ in range(int(input())):
    if input() not in l:
        ans = 'Yes'
print(ans)