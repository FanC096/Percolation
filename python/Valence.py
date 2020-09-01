import numpy as np
import sys
import matplotlib.pyplot as plt

sys.setrecursionlimit(50000)

count = 0
k = 2  # valence graph of degree k
p = 0.5  # probability
n = 1000000  # number of trials
lst = [None] * n
dist = [0, 0, 0, 0, 0, 0, 0]

'''
This parameter can be adjusted to construct multiple trees from a single root 
e.g. setting this param to 4 when k=2 constructs a tree with 6 central spokes
     (2 spokes from k=2, plus 4 additional spokes)
For higher values of k, the shape of the graph when this param is nonzero is
more difficult to visualize

Why This is Relevant:
For p = 0.5:
    For k=2 and amb=0, the expectation ≈ 3
        ''  and amb=1 -> expectation ≈ 4
                amb=2 -> expectation ≈ 5
                etc.
                amb=n -> expectation = 3 + n
Suggests that the number of "spokes" is  linearly related to the expected
cluster size - maybe we can analyze other trees in terms of their spokes,
instead of their layers (and then apply linearity of expectation??)
'''
additional_main_branches = 0

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
    for j in range(k + additional_main_branches):
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


