d=dict()
er=set()
erd=set()

def has_any(w):
    return w in d
def has(w, i):
    return has_any(w) and i in d[w]

for s in open(0):
    d.clear()
    er.clear()
    erd.clear()

    ws = s.split()
    for i,w in enumerate(ws):
        if w not in d: d[w] = set()
        d[w].add(i)
    n = len(ws)    

    if has_any('dip'):
        for i in d['dip']:
            if not (has('jiggle', i-1) or has('jiggle', i-2) or has('twirl', i+1)):
                er.add(1)
                erd.add(i)
    if not (has('clap', n-3) and has('stomp', n-2) and has('clap', n-1)):
        er.add(2)
    if has_any('twirl') and not has_any('hop'):
        er.add(3)
    if has('jiggle', 0):
        er.add(4)
    if not has_any('dip'):
        er.add(5)

    a = [''] * n
    for w, I in d.items():
        if w == 'dip':
            for i in I: a[i] = 'DIP' if i in erd else 'dip'
        else:
            for i in I: a[i] = w
    x = ' '.join(a)

    erl = sorted(er)
    if len(erl) == 0:
        print(f'form ok: {x}')
    elif len(erl) == 1:
        print(f'form error {erl[0]}: {x}')
    else:
        cm = ', '.join(map(str, erl[:-1]))
        print(f'form errors {cm} and {erl[-1]}: {x}')