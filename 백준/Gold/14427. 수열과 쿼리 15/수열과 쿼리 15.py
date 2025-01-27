from abc import ABCMeta, abstractmethod
from typing import TypeVar, Generic, Callable

T = TypeVar('T')
class SegmentTree(Generic[T], metaclass=ABCMeta):
    @abstractmethod
    def update(idx: int, newValue: T) -> None:
        ...
    
    @abstractmethod
    def query(start: int, end: int) -> T:
        ...


class ObjectSegmentTree(SegmentTree, Generic[T]):
    def __init__(self, dataSize: int, operator: Callable[[T, T], T], identity: T):
        self.operator = operator
        self.identity = identity
        self.u      : int       = 1 << (dataSize - 1).bit_length() if dataSize > 1 else 1
        self.tree   : list[T]   = [identity] * (self.u * 2)


    def update(self, idx: int, newValue: T) -> None:
        i: int = self.u + idx
        self.tree[i] = newValue
        while i > 1:
            i //= 2
            self.tree[i] = self.operator(self.tree[i * 2], self.tree[i * 2 + 1])

    def query(self, a: int, b: int) -> T:
        def search(start: int, end: int, a: int, b: int, idx: int):
            if end < a or b < start:
                return self.identity
            if a <= start and end <= b:
                return self.tree[idx]
            mid: int = (start + end) // 2
            return self.operator(
                search(start, mid, a, b, idx * 2),
                search(mid + 1, end, a, b, idx * 2 + 1),
            )
        return search(0, self.u - 1, a, b, 1)

def main():
    N = int(input())
    segtree: SegmentTree[tuple[int, int]] = ObjectSegmentTree[tuple[int, int]](N, min, (10**9, -1))

    A = [*map(int,input().split())]

    for i in range(N):
        segtree.update(i, (A[i], i+1))

    M = int(input())

    for _ in range(M):
        query = input()
        if query[0] == '1':
            _, i, v = map(int, query.split())
            segtree.update(i - 1, (v, i))
        else:
            print(segtree.tree[1][1])

if __name__ == '__main__':
    main()