h1,m1,s1=map(int,input().split(':'))
h2,m2,s2=map(int,input().split(':'))
sec1=h1*3600+m1*60+s1
sec2=h2*3600+m2*60+s2
sec3=(sec2-sec1)%86400
h3,m3,s3 = sec3//3600, sec3//60%60, sec3%60
print('%02d:%02d:%02d' % (h3, m3, s3))