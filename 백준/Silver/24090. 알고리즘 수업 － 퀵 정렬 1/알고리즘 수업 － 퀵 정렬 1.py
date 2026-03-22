import sys
sys.setrecursionlimit(10**4)

a = 0
K = 0

def swap(A, i, j):
    global a, K
    A[i], A[j] = A[j], A[i]
    a += 1
    if a==K:
        print(A[i], A[j])
        sys.exit(0)

def quick_sort(A, p, r):
    if p < r:
        q = partition(A, p, r)
        quick_sort(A, p, q-1)
        quick_sort(A, q+1, r)

def partition(A, p, r):
    x = A[r]
    i = p-1
    for j in range(p, r):
        if A[j] <= x:
            i += 1
            swap(A, i, j)
    if i+1 != r: swap(A, i+1, r)
    return i + 1

N, K = map(int,input().split())
A = [0, *map(int,input().split())]
quick_sort(A, 1, N)
print(-1)
