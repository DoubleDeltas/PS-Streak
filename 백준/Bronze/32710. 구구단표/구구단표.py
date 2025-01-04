N=int(input())
print(+(N<10 or any(N%i==0 and N//i<=9 for i in range(2,10))))