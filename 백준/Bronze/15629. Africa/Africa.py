default_visa_prices = {
    'botswana': 0,
    'ethiopia': 50,
    'kenya': 50,
    'namibia': 140,
    'south-africa': 0,
    'tanzania': 50,
    'zambia': 50,
    'zimbabwe': 30,
}

a=0

N=int(input())
S=[""] * N
for i in range(N):
    S[i] = input()
    if S[i-1] == 'zambia' and S[i] == 'zimbabwe' or S[i-1] == 'zimbabwe' and S[i] == 'zambia':
        a -= default_visa_prices[S[i-1]] # 이전 비자 취소
        a += 50             # 잠비아-짐바브웨 연합비자 신청
    elif S[i] == 'namibia' and 'south-africa' in S[:i]:
        a += 40     # 이전에 남아공을 들렀을 경우 40$
    else:
        a += default_visa_prices[S[i]]

print(a)