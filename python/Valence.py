import numpy as np
import sys
import matplotlib.pyplot as plt

sys.setrecursionlimit(50000)

count = 0
k = 2  # valence graph of degree k
p = 0.5  # probability
n = 10000000  # number of trials
lst = [None] * n
dist = [0, 0, 0, 0, 0, 0, 0]

def valence():
    global count
    count += 1
    for j in range(k - 1):
        r = np.random.uniform(0, 1)
        if r <= p:
            valence()
    return


s = 0
for i in range(n):
    count = 1
    for j in range(k):
        r = np.random.uniform(0, 1)
        if r <= p:
            valence()
    # print(count)
    lst[i] = count
    if count <= 7:
        dist[count - 1] += 1
    s += count
print(s/n)
print(dist)
plt.hist(lst, bins=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15])
plt.show()


