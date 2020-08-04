import numpy as np
import sys

sys.setrecursionlimit(50000)

count = 0
k = 2  # valence graph of degree k
p = 0.5  # probability
n = 1000  # number of trials


def valence():
    global count
    count += 1
    for j in range(k):
        r = np.random.uniform(0, 1)
        if r <= p:
            valence()
    return


s = 0
for i in range(n):
    count = 0
    valence()
    print(count)
    s += count
print(s/n)


