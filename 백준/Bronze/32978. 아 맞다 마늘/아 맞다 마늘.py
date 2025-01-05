_,a,b=map(str.split,open(0))
print([*({*a}-{*b})][0])