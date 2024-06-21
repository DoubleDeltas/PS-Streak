def solution(A,Q):
    for s,e in Q:
        A[s],A[e]=A[e],A[s]
    return A
        