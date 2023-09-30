N,U,L=map(int,input().split())
a=N>999
b=U>7999 or L>259
print("Very Good"if a and b else"Good"if a else"Bad") 
    