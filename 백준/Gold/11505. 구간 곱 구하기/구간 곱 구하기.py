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

MOD: int = 1_000_000_007

def main():
    N, M, K = map(int, input().split())
    segtree: SegmentTree[int] = ObjectSegmentTree[int](N, lambda a, b: a * b % MOD, 1)

    for i in range(N):
        segtree.update(i, int(input()))

    for i in range(M+K):
        a, b, c = map(int, input().split())
        if a == 1:
            segtree.update(b - 1, c)
        else:
            print(segtree.query(b - 1, c - 1))

if __name__ == '__main__':
    main()