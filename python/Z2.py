import numpy as np

import matplotlib.pyplot as plt


count = 0
n = 1000  # number of trials
pr = 0.5  # probability range upper bound (not include)
s = 0
dct = {}

for p in np.arange(0, pr, 0.01):
    # probability
    for i in range(n):
        included = {(0, 0)}
        border = {(0, 0)}

        while border:
            (x, y) = border.pop()
            if (x - 1, y) not in included:
                r = np.random.uniform(0, 1)
                if r <= p:
                    included.add((x - 1, y))
                    border.add((x - 1, y))

            if (x + 1, y) not in included:
                r = np.random.uniform(0, 1)
                if r <= p:
                    included.add((x + 1, y))
                    border.add((x + 1, y))

            if (x, y - 1) not in included:
                r = np.random.uniform(0, 1)
                if r <= p:
                    included.add((x, y - 1))
                    border.add((x, y - 1))

            if (x, y + 1) not in included:
                r = np.random.uniform(0, 1)
                if r <= p:
                    included.add((x, y + 1))
                    border.add((x, y + 1))

        s += len(included)

    dct[p] = s/n
    print(s/n)

plt.bar(list(dct.keys()), list(dct.values()), width=0.01)
plt.show()

print("{:<8} {:<10}".format('p', 'expectation'))
for k, v in dct.items():
    print("{:<8.2f} {:<10}".format(k, v))


