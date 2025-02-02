#!/usr/bin/env python

__author__ = 'Juan B Cabral 40.42 - 5k4'
__licence__ = 'GPL 3'

def get_histogram(img, levels):
    ''' obtiene el histograma de una imagen '''
    histogram=[0.0 for l in range(levels)]
    pix_total = 0
    for row in img:
        for element in row:
            histogram[element] += 1
            pix_total += 1
    histogram = [v/pix_total for v in histogram]
    return histogram


def get_equalized_histogram(histogram):
    '''retorna el histograma ecualizado '''
    levels = len(histogram)
    eq_histogram = [0.0 for l in range(levels)]
    last = 0
    for value in histogram:
        last += levels * value
        new_idx = int(round(last))
        if new_idx >= levels:
            new_idx = levels - 1 
        eq_histogram[new_idx] += value
    return eq_histogram 
       
    
def equalize(img, levels):
    ''' retorna la imagen ecualizada '''
    pass


################################################################################
# main
################################################################################

if __name__ == '__main__':
    ''' test run '''
    print("TEST RUN")
    
    img = [
        [2, 2, 2, 2, 2, 2, 2, 2, 2, 2],
        [2, 15, 2, 2, 12, 2, 2, 2, 10, 2],
        [2, 15, 2, 12, 15, 12, 2, 9, 2, 2],
        [2, 2, 12, 15, 15, 15, 12, 2, 2, 2],
        [2, 12, 15, 15, 15, 15, 15, 12, 2, 2],
        [2, 12, 15, 15, 15, 15, 15, 12, 2, 2],
        [2, 12, 15, 15, 15, 15, 15, 12, 2, 2],
        [2, 12, 12, 12, 12, 12, 12, 12, 2, 2],
        [2, 2, 2, 2, 2, 2, 2, 2, 10, 2],
        [2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
        ]
    
    levels = 16
    
    hist = get_histogram(img, levels)
    eq_hist = get_equalized_histogram(hist)
    
    i = 0
    for value in eq_hist:
        print("Intensity %i: %f" % (i, value))
        i += 1

