i=1
terms=['Double eagle.', 'Eagle.', 'Birdie.', 'Par.', 'Bogey.', 'Double Bogey.']
while True:
    p,s=map(int,input().split())
    if p == 0:
        break
    print(f"Hole #{i}")
    if s == 1:
        print("Hole-in-one.")
    else:
        print(terms[min(s-p,2)+3])
    print()
    i += 1