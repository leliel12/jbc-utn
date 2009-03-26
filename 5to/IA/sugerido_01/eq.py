#!/usr/bin/env python

def get_histogram(img, levels):
    histogram=[0.0 for l in range(levels)]
    pix_total = 0
    for row in img:
        for element in row:
            histogram[element] += 1
            pix_total += 1
    histogram = [v/pix_total for v in histogram]
    return histogram


def get_equalized_histogram(histogram):
    levels = len(histogram)
    eq_histogram = [0.0 for l in range(levels)]
    last = 0
    for value in histogram:
        last += levels * value
        new_idx = int(round(last))
        if new_idx == levels:
            new_idx -= 1
        eq_histogram[new_idx] += value
    return eq_histogram 
       
    
def equalize(img, levels):
    hist = get_histogram(img, levels)
    eq_hist = get_equalized_histogram(hist)
    return eq_hist


if __name__ == '__main__':
    print("TEST RUN")
    
    i0 = [10, 20, 30]
    i1 = [23, 45, 35]
    i2 = [54, 65, 28]
    img = [i0, i1, i2]
    
    levels = 255
    
    print equalize(img, levels)
