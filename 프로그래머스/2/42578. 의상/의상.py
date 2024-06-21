def solution(clothes):
    dct = {}
    for name, kind in clothes:
        if kind not in dct:
            dct[kind] = 0
        dct[kind] += 1
        
    ans = 1
    for v in dct.values():
        ans *= v + 1
    ans -= 1
    
    return ans