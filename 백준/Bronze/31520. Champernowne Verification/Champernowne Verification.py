l=[1,12,123,1234,12345,123456,1234567,12345678,123456789]
n=int(input())
print(1+l.index(n)if n in l else -1)