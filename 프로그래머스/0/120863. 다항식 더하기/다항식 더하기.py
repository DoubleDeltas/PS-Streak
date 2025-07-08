def solution(s):
    a,b=0,0
    for w in s.split(' + '):
        if w[-1]=='x':
            a+=1 if w=='x'else int(w[:-1])
        else:
            b+=int(w)
    if a > 1:
        return f'{a}x + {b}'if b>0else f'{a}x'
    if a == 1:
        return f'x + {b}'if b>0else'x'
    return str(b)if b>0else'0'