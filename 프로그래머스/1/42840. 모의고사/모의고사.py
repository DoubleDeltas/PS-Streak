patterns=[
    [1,2,3,4,5],
    [2,1,2,3,2,4,2,5],
    [3,3,1,1,2,2,4,4,5,5]
]
corrects=[0,0,0]
def solution(answers):
    for i, a in enumerate(answers):
        for p in range(3):
            period = len(patterns[p])
            if patterns[p][i % period] == a:
                corrects[p] += 1
    ans = []
    for p in range(3):
        if corrects[p] == max(corrects):
            ans.append(p + 1)
    return ans