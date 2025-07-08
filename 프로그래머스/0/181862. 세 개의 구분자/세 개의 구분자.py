import re
solution=lambda s:[w for w in re.split('a|b|c',s)if w]or['EMPTY']