for i in range(1,int(input())+1):
    if i%15==0:
        print('DeadMan')
    elif i%5==0:
        print('Man')
    elif i%3==0:
        print('Dead')
    else:
        print(i,end=' ')