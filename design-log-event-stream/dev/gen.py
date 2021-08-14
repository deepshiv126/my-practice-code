import random
import time

JITTER = 10
TICKS = 10
LINES_PER_TICK = 10


def log_line(now):
    timestamp = now - (random.random() * JITTER)
    return "%f City %d" % (timestamp, random.randint(0, 10000))


start = time.time()

for tick in xrange(TICKS):
    now = start + tick
    for num_line in xrange(LINES_PER_TICK):
        print(log_line(now))
