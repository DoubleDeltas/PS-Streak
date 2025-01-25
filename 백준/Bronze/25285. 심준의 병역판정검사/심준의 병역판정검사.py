for s in[*open(0)][1:]:
    a,b=map(int,s.split())
    bmi = b / (a/100)**2
    if a < 140.1:
        print(6)
    elif a < 146:
        print(5)
    elif a < 159:
        print(4)
    elif a < 161:
        if 16.0 <= bmi < 35.0:
            print(3)
        else:
            print(4)
    elif a < 204:
        if 20.0 <= bmi < 25.0:
            print(1)
        elif 18.5 <= bmi < 20.0 or 25.0 <= bmi < 30.0:
            print(2)
        elif 16.0 <= bmi < 18.5 or 30.0 <= bmi < 35.0:
            print(3)
        else:
            print(4)
    else:
        print(4)