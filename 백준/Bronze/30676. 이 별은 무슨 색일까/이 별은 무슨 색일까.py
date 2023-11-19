L=[620,590,570,495,450,425,380]
S=['Red','Orange','Yellow','Green','Blue','Indigo','Violet']
def main():
    l=int(input())
    for i in range(7):
        if l >= L[i]:
            print(S[i])
            return
main()