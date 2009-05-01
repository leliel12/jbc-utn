#!/usr/bin/env python

__author__ = 'Juan B Cabral 40.42 - 5k4'
__licence__ = 'GPL 3'

def _get_matrix_size(mtx):
    ''' _get_matrix_size(mtx=[[]]) -> (n,m)
    Retorna un par (n,n) donde n es la cantidad de filas de la matriz
    y n es la cantidad de columnas.'''
    rows = len(mtx)
    cols = None
    for row in mtx:
        if cols is None:
            cols = len(row)
        if cols != len(row):
            raise ValueError("la cantidad de columnas de la matriz " + 
            "debe ser igual en cada fila")            
    return (rows, cols)

    
def _is_aplicable_mask(img_size, mask_size):
    '''_is_aplicable_mask(img_size=(n,n), mask_size=(m,m)) -> bool
    Retorna True si n >= m.'''
    return (mask_size[0] <= img_size[0] and
            mask_size[1] <= img_size[1])
        
        
def _is_valid_mask(mask_size):
    '''_is_valid_mask((n,n)) -> bool
    Retorna True si n es impar.'''
    valid = ((mask_size[0] % 2) != 0)
    valid = (mask_size[0] == mask_size[1])
    return valid
 
 
def _split_img(img, img_size,split_size):
    splited_mtxs = []
    nRows, nCol = img_size
    i,j = split_size
    start_row = 0
    while(start_row + i) <= nRows:
        start_col = 0
        while(start_col + j) <= nCol:
            sub_mtx = []
            for idxi in range(start_row, start_row+i):
                row = []
                for idxj in range(start_col, start_col+j):
                    row.append(img[idxi][idxj])
                sub_mtx.append(row)
            splited_mtxs.append(sub_mtx)
            start_col += 1
        start_row += 1    
    return tuple(splited_mtxs)
    
    
def _conv(img_parts, mask):
    '''_conv(mask[[]], img_part[[]]) -> float
    Retorna el valor de convolucionar dos matrices nxn'''
    values = []
    for part in img_parts:
        value = 0.0
        rows = zip(mask, part)
        for mask_row, img_row in rows:
            cells = zip(mask_row, img_row)
            for i, j in cells:
                value += i * j
        values.append(value)
    return values   


def _aplicate_conv_values(img, img_size,conv_values, mask_size):
    '''_aplicate_conv(img = [[]], conv_values = [], center = (n,n)) -> [[]]
    Retorna una matriz con los valores de la convolucion aplicados'''
    new_img = [list(row) for row in img]
    center = (mask_size[0] / 2, mask_size[1] / 2)
    stop_row, stop_col = img_size
    start_col = center[1]
    idx_row = center[0]
    conv_values.reverse()
    while idx_row < stop_row -1:
        idx_col = center[1]
        while idx_col < stop_col -1:
            value = conv_values.pop()
            new_img[idx_row][idx_col] = value
            idx_col += 1
        idx_row +=1
    return new_img


def aplicate_mask(img, mask):
    '''aplicate_mask(img=[[]], mask=[[]]) -> [[]]
    aplica una mascara mxm a una imagen nxn y retorna la nueva matriz mxm,
    siempre y cuando m >= n'''
    #Tamano de la mascara en formato (i,j)
    img_size = _get_matrix_size(img)
    
    #Tamano de la imagen en formato (i,j)
    mask_size = _get_matrix_size(mask)
    
    #Validamos que todo este bien para empezar a procesar 
    if not _is_valid_mask(mask_size):
        raise ValueError('''Mascara invalida.
        Mascara debe ser nxn siendo n entero impar''')
    if not _is_aplicable_mask(img_size, mask_size):
        raise ValueError("Mascara debe ser mas pequena que la imagen")        
    
    #Separamos la imagen en pedazos del tamano de la mascara
    img_parts = _split_img(img, img_size,mask_size)
    #obtenemos los valores de la convolucion
    conv_values = _conv(img_parts, mask)
    #reemplazamos en la imagen original
    new_img = _aplicate_conv_values(img, img_size,conv_values, mask_size)
    return new_img
    
    
def mid_filter(img, mask_size):
    '''mid_filter(img=[[]], mask_size=n -> [[]]
    Retorna una imagen aplicando un filtro medio nxn'''
    i, j = mask_size
    mask = []
    for idxi in range(i):
        row = [(1.0 / (i*j)) for idxj in range(j)]
        mask.append(row)
    return aplicate_mask(img, mask)

def med_filter(img, mask_size):
    '''med_filter(img=[[]], mask_size=n -> [[]]
    Retorna una imagen aplicando un filtro de la mediana nxn'''
    img_size = _get_matrix_size(img)
    img_parts = _split_img(img, img_size, mask_size)
    conv_values = []
    for part in img_parts:
        extended_row = []
        for row in part:
            extended_row.extend(row)
        extended_row.sort()
        to_pick = int(round(len(extended_row)/2))
        value = extended_row[to_pick]
        conv_values.append(value)
    new_img = _aplicate_conv_values(img, img_size,conv_values, mask_size)
    return new_img
        

################################################################################
# main
################################################################################

if __name__ == '__main__':
    
    def print_matrix(matrix, tabs=0):
        ttp = [ "\t" for c in range(tabs)]
        ttp = "".join(ttp)
        for row in matrix:
            print(ttp + str(row))
        
        
    print('TEST CASE')
    img = [
            [1, 2, 3,4],
            [5, 6, 7,8],
            [9, 10, 11,12],
            [13, 14, 15,16]
         ]
    print("\t img matrix:")
    print_matrix(img, 2)
    
    mask = [
            [2, 1, 3],
            [4, 5, 6],
            [7, 8, 9]
           ]
               
    print("\n\t mask:")
    print_matrix(mask, 2)
    
    print("\nAplicando Completo...")
    new_img = aplicate_mask(img,mask)
    print_matrix(new_img, 2)
    
    print("\nAplicando Filtro de la media...")
    new_img = mid_filter(img, (3,3))
    print_matrix(new_img, 2)
    
    print("\nAplicando Filtro de la mediana...")
    new_img = med_filter(img, (3,3))
    print_matrix(new_img, 2)
     
    
            
    
    
    
    
